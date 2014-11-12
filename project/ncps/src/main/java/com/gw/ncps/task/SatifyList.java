package com.gw.ncps.task;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gw.ncps.common.util.DateUtil;
import com.gw.ncps.dto.NewsInfoSourceDetailDTO;
import com.gw.ncps.model.NewsFilterSimplify;
import com.gw.ncps.model.NewsTotal;
import com.gw.ncps.task.impl.SyncInit;

/**
 * @function：返回满足条件的List<?>
 * @author Darwen
 * @data:2013-11-9上午9:58:13
 *
 */
public class SatifyList {
	
	
	/**
	 * Description：查询 并删选满足条件的list  显示到页面
	 * @param list
	 * @param newsInfoSourceDetailDTO
	 * @return List<NewsFilterSimplify>    
	 * @author Darwen
	 * @date: 2013-11-20下午4:46:46
	 */
	public List<NewsFilterSimplify> meet(List<NewsFilterSimplify> list, NewsInfoSourceDetailDTO newsInfoSourceDetailDTO){
		if(list.isEmpty())
			return null;
		try{
				List<NewsFilterSimplify> rtnList = new ArrayList<NewsFilterSimplify>();
				for(NewsFilterSimplify nfs:list){
					if(((newsInfoSourceDetailDTO.getGroupName().equals(nfs.getGroupName()) ||
							newsInfoSourceDetailDTO.getGroupName().equals(""))) &&
							(newsInfoSourceDetailDTO.getSiteName().equals(nfs.getSiteName()) ||
							newsInfoSourceDetailDTO.getSiteName().equals("")) &&
							(newsInfoSourceDetailDTO.getChannel().equals(nfs.getChannel()) || 
							newsInfoSourceDetailDTO.getChannel().equals("")) && 
							(newsInfoSourceDetailDTO.getTrssource().equals(nfs.getTrssource())||
							newsInfoSourceDetailDTO.getTrssource().equals(""))){
						//满足时间条件    1、都为空,即没有时间条件  2、所属新闻在查询时间范围内
					    if(((newsInfoSourceDetailDTO.getDateStr_Start().equals("")) && 
					    		newsInfoSourceDetailDTO.getDateStr_End().equals("")) ||
					    		((DateUtil.compare_date(newsInfoSourceDetailDTO.getDateStr_Start(), nfs.getTime())) == -1) &&
					    		(DateUtil.compare_date(newsInfoSourceDetailDTO.getDateStr_End(), nfs.getTime())) == 1){
					    			rtnList.add(nfs);
					    		}
					}
				}
				return rtnList;
			}catch(Exception e){
				e.printStackTrace();
				return null;
		}
	
	}
	
	
	/**
	 * Description：统计存储到内存中表相关  参数
	 * @param list     
	 * @author Darwen
	 * @date: 2013-11-20下午4:46:15
	 */
	public void meetSyncInitList(List<NewsFilterSimplify> list, HttpServletRequest request){
		if(list.isEmpty())
			return;
		Map<String,NewsTotal> mymap = (HashMap<String,NewsTotal>)request.getSession().getAttribute("map");
		for(NewsFilterSimplify nfs : list){
			
			//因之前的数据trssource、dustflag没有   暂时做特殊处理，等之后七天数据正常再删除
			if(nfs.getDustflag().equals("") && nfs.getTrssource() == null)
				continue;
			
			//四个字段连接起来成为 一个key  中间用“|”隔开
			String key = "";
			key = key.concat(nfs.getGroupName()).concat("|").
					  concat(nfs.getSiteName()).concat("|").
					  concat(nfs.getChannel()).concat("|").
					  concat(nfs.getTrssource());
			NewsTotal nt = new NewsTotal();
			nt.setChannel(nfs.getChannel());
			nt.setGroupName(nfs.getGroupName());
			nt.setSiteName(nfs.getSiteName());
			nt.setTrssource(nfs.getTrssource());
			if(mymap.containsKey(key)){
				nt = mymap.get(key);
				if(nfs.getIsdup().equals("1"))
					nt.setDupCount(nt.getDupCount()+1);
				else
                    nt.setResCount(nt.getResCount()+1);
				if(nfs.getDustflag().equals("0"))
				{
					nt.setCtxnCount(nt.getCtxnCount()+1);   //正文空
				}else if(nfs.getDustflag().equals("1")){
					nt.setPureDustCount(nt.getPureDustCount()+1);   //纯垃圾
					nt.setAllDustCount(nt.getAllDustCount()+1);     //所有垃圾
				}else if(nfs.getDustflag().equals("2")){
					nt.setAllDustCount(nt.getAllDustCount()+1);   //所有垃圾
				}
				nt.setCount(nt.getCount()+1);
			}else{				
				if(nfs.getIsdup().equals("1"))
					nt.setDupCount(1);
				else
                    nt.setResCount(1);
				if(nfs.getDustflag().equals("0"))
				{
					nt.setCtxnCount(1);   //正文空
				}else if(nfs.getDustflag().equals("1")){
					nt.setPureDustCount(1);   //纯垃圾
					nt.setAllDustCount(1);     //所有垃圾
				}else if(nfs.getDustflag().equals("2")){
					nt.setAllDustCount(1);   //所有垃圾
				}
				nt.setCount(1);
				mymap.put(key, nt);
			}
		}
		request.getSession().setAttribute("map", mymap);
	}
	
	/**
	 * Description：实时的把content表内容添加到内存中去
	 * @param temp
	 * @return List<NewsFilterSimplify>  
	 * @author Darwen
	 * @date: 2013-11-20下午4:44:56
	 */
	public List<NewsFilterSimplify> addList(List<NewsFilterSimplify> temp){
		if(temp.isEmpty())
			return SyncInit.getList();
		List<NewsFilterSimplify> rtn = SyncInit.getList();
		for(NewsFilterSimplify nfs : temp){
			//最大maxID
			if(nfs.getAutoid() > SyncInit.getSum())
				SyncInit.setSum(nfs.getAutoid());
			rtn.add(nfs);
		}
		return rtn;
	}


    /**
     * Description：根据排序字段   对列表排序
     * @param lnt
     * @param sordName
     * @return 链表    List<NewsTotal>
     * @author Darwen
     * @date: 2013-11-20下午4:41:17
     */
    public void sortList(List<NewsTotal> lnt, String sordName, String sordType) {
    	if(lnt.isEmpty())
    		return;
    	for(int k = 0; k < lnt.size()-1; k++){
    		for(int m = 0; m < lnt.size() - k -1; m++){
    			double preData = 0;
    			double posData = 0;  			
    			if(sordName.equals("dupCount")){
    				preData = lnt.get(m).getDupCount();
    				posData = lnt.get(m+1).getDupCount();
    			}else if(sordName.equals("resCount")){
    				preData = lnt.get(m).getResCount();
    				posData = lnt.get(m+1).getResCount();
    			}else if(sordName.equals("allDustCount")){
    				preData = lnt.get(m).getAllDustCount();
    				posData = lnt.get(m+1).getAllDustCount();
    			}else if(sordName.equals("pureDustCount")){
    				preData = lnt.get(m).getPureDustCount();
    				posData = lnt.get(m+1).getPureDustCount();
    			}else if(sordName.equals("ctxnCount")){
    				preData = lnt.get(m).getCtxnCount();
    				posData = lnt.get(m+1).getCtxnCount();
    			}else if(sordName.equals("dupRatio")){
    				preData = Double.parseDouble(lnt.get(m).getDupRatio().substring(0, lnt.get(m).getDupRatio().indexOf("%")));
    				posData = Double.parseDouble(lnt.get(m+1).getDupRatio().substring(0, lnt.get(m+1).getDupRatio().indexOf("%")));
    			}else if(sordName.equals("pureRatio")){
    				preData = Double.parseDouble(lnt.get(m).getPureRatio().substring(0, lnt.get(m).getPureRatio().indexOf("%")));
    				posData = Double.parseDouble(lnt.get(m+1).getPureRatio().substring(0, lnt.get(m+1).getPureRatio().indexOf("%")));
    			}else if(sordName.equals("ctxnRatio")){
    				preData = Double.parseDouble(lnt.get(m).getCtxnRatio().substring(0, lnt.get(m).getCtxnRatio().indexOf("%")));
    				posData = Double.parseDouble(lnt.get(m+1).getCtxnRatio().substring(0, lnt.get(m+1).getCtxnRatio().indexOf("%")));
    			}
    			if(sordType.equals("desc")){
    				if(preData > posData){
        				NewsTotal temp = lnt.get(m);
        				lnt.set(m, lnt.get(m+1));
        				lnt.set(m+1, temp);
        			}
    			}else{
    				if(preData < posData){
        				NewsTotal temp = lnt.get(m);
        				lnt.set(m, lnt.get(m+1));
        				lnt.set(m+1, temp);
        			}
    			}
    		}
    	}
    }
	
	
    
    /**
     * Description：计算内存中的map 相关比例
     * @return List<NewsTotal>   
     * @author Darwen
     * @date: 2013-11-21上午9:59:13
     */
    public List<NewsTotal> ratioCalculate(HttpServletRequest request){
    	List<NewsTotal> rtnList = new ArrayList<NewsTotal>();
    	Map<String,NewsTotal> mymap = (HashMap<String,NewsTotal>)request.getSession().getAttribute("map");
    	for(Object o: mymap.keySet()){
			NewsTotal nt = mymap.get(o.toString());
			DecimalFormat df = new DecimalFormat("0.##%");  //保留两个小数
			if(nt.getCount()==0){
				nt.setDupRatio("0%");
				nt.setCtxnRatio("0%");
			}else{
			    nt.setDupRatio(df.format((double)nt.getDupCount()/(double)nt.getCount()));
			    nt.setCtxnRatio(df.format((double)nt.getCtxnCount()/(double)nt.getCount()));
			}
			if(nt.getAllDustCount()==0){
				nt.setPureRatio("0%");
			}else{
				nt.setPureRatio(df.format((double)nt.getPureDustCount()/(double)nt.getAllDustCount()));
			}
			rtnList.add(nt);
		}
    	return rtnList;
    }
	
}
