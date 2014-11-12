package com.gw.ncps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 日志统计
 * 
 * @author JohnmyWork
 * 
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public String main() {
		return "deduplication/main";
	}

	@RequestMapping(value="{pagename}", method = RequestMethod.GET)
	public String specify(@PathVariable String pagename) {
		return "deduplication/" + pagename;
	}

	@RequestMapping(value = "/ndr-monitor", method = RequestMethod.POST)
	public String ndrMonitorPost() {
		return "deduplication/ndr-monitor";
	}


}
