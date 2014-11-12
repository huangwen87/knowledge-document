package com.gw.ncps.common.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.gw.ncps.common.util.LogUtil;
import com.gw.newsdup.NewsDup;

public class ContextInitListenerServlet implements ServletContextListener {

	private final static Logger log = LogUtil.getInstance(ContextInitListenerServlet.class);
	private ServletContext context = null;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("----------------destroying--------------");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("----------------initializing--------------");

		context = sce.getServletContext();
//		context.setAttribute("initnewsdup", new NewsDup());
	}

}
