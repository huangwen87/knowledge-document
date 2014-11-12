package com.gw.ps.listener;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.gw.ps.model.NicCategory;
import com.gw.ps.pbbean.PbNewsClassify;
import com.gw.ps.service.INicService;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

/**
 * 读取情感分析相关队列信息
 * 
 * @author JohnmyWork
 * 
 */
public class MultipleNicListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "nicService")
	private INicService nicService;

	public void classifyPredict(Map<String, byte[]> map) {

		NicCategory nc = new NicCategory();
		try {

			PbNewsClassify.NewsClassifyResult classifyResult = PbNewsClassify.NewsClassifyResult.parseFrom(map.get("key"));

			nc.setNewsCode(ByteUtil.long2HL(classifyResult.getNewsCode()));
			nc.setLogTime(DateUtil.getCurrentDateStr());
			nc.setResult(classifyResult.getResult());
			nc.setTitle(classifyResult.getTitle());
			nc.setContent(classifyResult.getContent());
			for (String s : classifyResult.getClassNameListList()) {
				nc.setCategoryName(s);
				logger.debug(nc.toString());
				nicService.addNewsClass(nc);
			}

		} catch (Exception e) {
			logger.error(nc.toString());
			e.printStackTrace();
		}

	}

}
