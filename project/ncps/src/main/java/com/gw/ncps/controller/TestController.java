package com.gw.ncps.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzh.show.EmotionResult;
import com.dzh.show.InterpretAsString;
import com.gw.ncps.common.util.JsonUtil;
import com.gw.ncps.common.util.LogUtil;
import com.gw.ncps.common.util.Utils;
import com.gw.ncps.dto.NdrDuplicateTestDTO;
import com.gw.ncps.dto.NeaEmotionTestDTO;
import com.gw.ncps.model.NewsDupCoverter;
import com.gw.ncps.service.INewsDuplicateRemovalService;
import com.gw.news.NewsDupRes;
import com.gw.newsdup.NewsDup;

/**
 * 在线测试
 * 
 * @author JohnmyWork
 * 
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;

	@RequestMapping(value = "/ndr-duplicate-test", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String testNdr(NdrDuplicateTestDTO ndrDuplicateTestDTO) {
		String comp1 = null, comp2 = null;
		NewsDupRes newsDupRes = null;
		NewsDupCoverter ndc = null;

		String idF = ndrDuplicateTestDTO.getIdF();
		String idL = ndrDuplicateTestDTO.getIdL();
		String textF = ndrDuplicateTestDTO.getTextF();
		String textL = ndrDuplicateTestDTO.getTextL();

		if (Utils.isNotNull(idL) && Utils.isNotNull(idL)) {
			comp1 = (String) ndrService.queryContentById(idF);
			comp2 = (String) ndrService.queryContentById(idL);
			newsDupRes = new NewsDup().isdup(Long.valueOf(idF), Long.valueOf(idL), comp1, comp2);
		} else if (Utils.isNotNull(textF) && Utils.isNotNull(textL)) {
			newsDupRes = new NewsDup().isdup(0L, 1L, textF, textL);
		}
		if (newsDupRes != null) {
			ndc = new NewsDupCoverter(newsDupRes);
		}
		logger.info(JsonUtil.object2Json(ndc));
		return JsonUtil.toJson(ndc);
	}

	@RequestMapping(value = "/nea-emotion-test", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String testNea(NeaEmotionTestDTO neaEmotionTestDTO) {
		String news = neaEmotionTestDTO.getTextF();
		if (news.isEmpty()) {
			return JsonUtil.toJson(new EmotionResult());
		}
		String title = "";
		EmotionResult exp = new InterpretAsString().explain(title, news);// 1:正面，-1负面，0不确定
		String ss = JsonUtil.toJson(exp);
		return ss;
	}
}
