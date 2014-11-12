package com.gw.ncps.controller;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gw.ncps.common.util.DateUtil;
import com.gw.ncps.common.util.JsonUtil;
import com.gw.ncps.common.util.LogUtil;
import com.gw.ncps.dto.NewsDuplicateInfoSourceDTO;
import com.gw.ncps.dto.NewsDuplicateTotalDTO;
import com.gw.ncps.dto.NewsInfoSourceDetailDTO;
import com.gw.ncps.dto.NewsMatchDTO;
import com.gw.ncps.dto.NewsVolumeDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.NewsFilterSimplify;
import com.gw.ncps.model.NewsInfoSourceDuplicateRatio;
import com.gw.ncps.model.NewsTotal;
import com.gw.ncps.model.NewsVolume;
import com.gw.ncps.model.Page;
import com.gw.ncps.service.INewsDuplicateRemovalService;
import com.gw.ncps.task.SatifyList;
import com.gw.ncps.task.impl.SyncInit;

/**
 * 代码匹配结果
 * 
 * @author JohnmyWork
 * 
 */
@Controller
@RequestMapping(value = "/ndr/match")
public class NdrMatchController{
	
	private final Logger logger = LogUtil.getInstance(this.getClass());
	
	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;

	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String query(NewsMatchDTO newsMatchDTO, Page page) {
		String[] ids = null;
		Date sqlDate = null;
		String date = newsMatchDTO.getDateStr();
		String id = newsMatchDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			newsMatchDTO.setDate(sqlDate);
		}

		if (!id.isEmpty()) {
			ids = id.split(",");
			newsMatchDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsMatchDTO);
		Page rpsPage = ndrService.queryMatchAll(dto);
		return JsonUtil.object2Json(rpsPage);
	}
	
	
	/**
	 * 阶段性新闻流入、流出量记录查询
	 * 
	 * */
	@RequestMapping(value = "/queryVolume", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryVolume(NewsVolumeDTO NewsVolumeDTO, Page page) {	
		SearchDTO dto = new SearchDTO(page, NewsVolumeDTO);
		Page rpsPage = ndrService.queryVolumeIn(dto);  //入流量
		Page outPage = ndrService.queryVolumeOut(dto);   //出流量
		
		List listIn  = rpsPage.getDataRows();
		List listOut = outPage.getDataRows();
		NewsVolume in = (NewsVolume) listIn.get(0);    //list可能为空，考虑
		NewsVolume out = (NewsVolume) listOut.get(0);    //list可能为空，考虑
		in.setOutflow(out.getOutflow());
		in.setTimestamp(NewsVolumeDTO.getDateStr_Start()+"--"+NewsVolumeDTO.getDateStr_End());
		listIn.set(0, in);
		rpsPage.setDataRows(listIn);
		String response = JsonUtil.object2Json(rpsPage);
		return response;
	}
	
	
	/**
	 * 阶段性新闻去重量查询 ---单条新闻去重了多少条
	 * 
	 * */
	@RequestMapping(value = "/queryDup", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryDup(NewsDuplicateTotalDTO newsDuplicateTotalDTO, Page page) {
		String[] ids = null;
		String id = newsDuplicateTotalDTO.getId();

		if (!id.isEmpty()) {
			ids = id.split(",");
			newsDuplicateTotalDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsDuplicateTotalDTO);
		Page rpsPage = ndrService.queryDup(dto);		  
        return JsonUtil.toJson(rpsPage);
	}
	
	
	
	/**
	 * 阶段性含垃圾数据总量、纯垃圾数据总量 查询   
	 * 
	 * */
	@RequestMapping(value = "/queryDust", method = RequestMethod.GET)
	public @ResponseBody
	String queryDust(NewsVolumeDTO newsVolumeDTO) {	
		  Object list = ndrService.queryDust(newsVolumeDTO);		  
          return JsonUtil.toJson(list);
	}
	
	
	/**
	 * 查看单个信息源的数据量、信息源汇总的数据量。
	 * 
	 * */
	@RequestMapping(value = "/queryInfoSource", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryInfoSource(NewsDuplicateInfoSourceDTO newsDuplicateInfoSourceDTO, Page page) {	
		
		//String str = (String)servletContext.getAttribute("map");
		
		SearchDTO dto = new SearchDTO(page, newsDuplicateInfoSourceDTO);
		Page rpsPage = ndrService.queryFilterInfoSource(dto);
		String response = JsonUtil.object2Json(rpsPage);
		logger.debug(response);
		return response;
		
		
	}
	
	
	/**
	 * 信息源--比例统计（去重量、保留量）
	 * 
	 * */
	@RequestMapping(value = "/queryInfoSourceDuplicateRatio", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryInfoSourceDuplicateRatio(NewsDuplicateInfoSourceDTO newsDuplicateInfoSourceDTO) {
		
		NewsInfoSourceDuplicateRatio newsInfoSourceDuplicateRatio = new NewsInfoSourceDuplicateRatio();
		newsInfoSourceDuplicateRatio.setRadio(Integer.parseInt(newsDuplicateInfoSourceDTO.getCount()));
		int subCount  = 0;
		String ratio = "0.0%";
		switch(newsInfoSourceDuplicateRatio.getRadio()){
		       case 0:  subCount = Integer.parseInt(ndrService.queryFilterInfoSourceDuplicateRatio(newsDuplicateInfoSourceDTO).toString());break;  //去重量
		       case 1:  subCount = Integer.parseInt(ndrService.queryFilterInfoSourceUNDuplicateRatio(newsDuplicateInfoSourceDTO).toString());break;   //保留量 
		       case 2:  subCount = Integer.parseInt(ndrService.queryFilterInfoSourceDustRatio(newsDuplicateInfoSourceDTO).toString());   break;   //纯垃圾量   
		       case 3:  subCount = Integer.parseInt(ndrService.queryFilterInfoSourceContentNULLRatio(newsDuplicateInfoSourceDTO).toString());break;   //正文空量 
		}
		
		int sumCount = 0;
		if(newsInfoSourceDuplicateRatio.getRadio() == 2){
			int totalDupCount =  Integer.parseInt(ndrService.queryFilterInfoSourceTotalDustRatio(newsDuplicateInfoSourceDTO).toString());  //部分垃圾量
			sumCount = subCount + totalDupCount;
			newsInfoSourceDuplicateRatio.setTotalDupCount(sumCount); //总垃圾数
		}else{
			sumCount = Integer.parseInt(ndrService.queryFilterInfoSourceTotal(newsDuplicateInfoSourceDTO).toString());  //总量
		}
		
         if(sumCount!=0){
			DecimalFormat df = new DecimalFormat("0.##%");  //保留两个小数
			ratio = df.format(((double)subCount/(double)(sumCount)));
		}
    	newsInfoSourceDuplicateRatio.setDupCount(subCount);
    	newsInfoSourceDuplicateRatio.setDupRatioStr(ratio);
    	String response = JsonUtil.toJson(newsInfoSourceDuplicateRatio);
		return response;
	}
	
	
	
//	/**
//	 * 信息源统计结果。
//	 * 
//	 * */
//	@RequestMapping(value = "/queryInfoSourceTotal", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
//	public @ResponseBody
//	String queryInfoSourceTotal(NewsInfoSourceDetailDTO newsInfoSourceDetailDTO,Page page) {	
//		
//		//及时查询
//		List<NewsFilterSimplify> temp = (List<NewsFilterSimplify>)ndrService.queryContentFilterAll(SyncInit.getSum());
//		SatifyList sl = new SatifyList();
//		sl.meetSyncInitList(temp);	
//
//		//计算相关比例
//		List<NewsTotal> rtnList = new ArrayList<NewsTotal>();
//		for(Object o: SyncInit.getMap().keySet()){
//			NewsTotal nt = SyncInit.getMap().get(o.toString());
//			DecimalFormat df = new DecimalFormat("0.##%");  //保留两个小数
//			if(nt.getCount()==0){
//				nt.setDupRatio("0%");
//				nt.setCtxnRatio("0%");
//			}else{
//			    nt.setDupRatio(df.format((double)nt.getDupCount()/(double)nt.getCount()));
//			    nt.setCtxnRatio(df.format((double)nt.getCtxnCount()/(double)nt.getCount()));
//			}
//			if(nt.getAllDustCount()==0){
//				nt.setPureRatio("0%");
//			}else{
//				nt.setPureRatio(df.format((double)nt.getPureDustCount()/(double)nt.getAllDustCount()));
//			}
//			rtnList.add(nt);
//		}
//		
//		//获得满足搜索条件的List
//		List<NewsTotal> list = sl.meet(rtnList, newsInfoSourceDetailDTO);
//		int size = list.size();
//		
//		
//		//页面格式化
//		Page rpsPage = new Page();
//		int endIndex = page.getPage()*page.getRows();
//		if(endIndex > size){
//			endIndex = size;
//		    rpsPage.setDataRows(list.subList((page.getPage()-1)*page.getRows(), endIndex));  //尾页特殊处理
//		}else{
//			rpsPage.setDataRows(list.subList(endIndex-page.getRows(), endIndex));
//		}
//		rpsPage.setEndRowNum(page.getEndRowNum());
//		rpsPage.setPage(page.getPage());
//		rpsPage.setRecords(size);
//		rpsPage.setRows(page.getRows());
//		rpsPage.setTotal(size/page.getRows()+1);
//		String response = JsonUtil.object2Json(rpsPage);
//		logger.debug(response);
//		return response;
//		
//		
//	}
	

	
	/**
	 * 信息源详情结果
	 * 
	 * */
	@RequestMapping(value = "/queryInfoSourceTotal", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryInfoSourceTotal(NewsInfoSourceDetailDTO newsInfoSourceDetailDTO, Page page, HttpServletRequest request) {	
		
		SatifyList sl = new SatifyList();
		//刷新或者第一次查询的时候  会计算相关比例  (以后待修改， 下一页回到第一页也会出现计算，实际是不需要的)
		if((newsInfoSourceDetailDTO.getParam() == null && page.getPage() == 1 && 
				page.getSidx().equals("trssource") && page.getSord().equals("desc")) || 
				(newsInfoSourceDetailDTO.getParam()!=null && newsInfoSourceDetailDTO.getParam().equals("query") &&
				page.getPage() == 1) ){
			//清空上次查询   以便影响这次查询
			request.getSession().invalidate();
			Map<String,NewsTotal> mymap = new HashMap<String,NewsTotal>();
			request.getSession().setAttribute("map", mymap); 
			//根据条件，设置满足的map     待验证  更新能及时？？？？？
			List<NewsFilterSimplify> mylist = new ArrayList<NewsFilterSimplify>();
			synchronized(SyncInit.class){
			   mylist = SyncInit.getList();
			   sl.meetSyncInitList(sl.meet(mylist, newsInfoSourceDetailDTO), request);
			}			
		}
		//计算相关比例
		List<NewsTotal> rtnList = sl.ratioCalculate(request);
		sl.sortList(rtnList, page.getSidx(), page.getSord());
		int size = rtnList.size();
		
		//页面格式化
		Page rpsPage = new Page();
		int endIndex = page.getPage()*page.getRows();
		if(endIndex > size){
			endIndex = size;
		    rpsPage.setDataRows(rtnList.subList((page.getPage()-1)*page.getRows(), endIndex));  //尾页特殊处理
		}else{
			rpsPage.setDataRows(rtnList.subList(endIndex-page.getRows(), endIndex));
		}
		rpsPage.setEndRowNum(page.getEndRowNum());
		rpsPage.setPage(page.getPage());
		rpsPage.setRecords(size);
		rpsPage.setRows(page.getRows());
		rpsPage.setTotal(size/page.getRows()+1);
		rpsPage.setSord(page.getSord());
		rpsPage.setSidx(page.getSidx());
		String response = JsonUtil.object2Json(rpsPage);
		logger.debug(response);
		return response;
	}
	
	
	
	/**
	 * Description：及时更新
	 * @return     
	 * @author Darwen
	 * @date: 2013-11-21上午9:11:20
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String update() {
		 List<NewsFilterSimplify>  temp  = new ArrayList<NewsFilterSimplify>();
		 synchronized(SyncInit.class){
			 temp = (List<NewsFilterSimplify>)ndrService.queryContentFilterAll(SyncInit.getSum());
			 SatifyList sl = new SatifyList();
			 //更新到总的   SyncInit.list 中去
			 sl.addList(temp);
		 }
		return temp.size()+"";
	}
}
