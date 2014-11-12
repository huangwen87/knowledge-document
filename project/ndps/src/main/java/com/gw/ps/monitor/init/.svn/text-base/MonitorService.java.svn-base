package com.gw.ps.monitor.init;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * @author mafei E-mail: mafei@gw.com.cn
 * @version 创建时间：2013-4-18 上午10:38:45 类说明
 */
public class MonitorService implements Runnable {

	private static final Logger logger = Logger.getLogger(MonitorService.class);
	private String listenPort="6308";

	public void run()  {

		if (!StringUtils.isBlank(listenPort) && StringUtils.isNumeric(listenPort)) {
			int port = Integer.parseInt(listenPort);
			if (port > 0 && port < 65536) {
				bindMonitorReportService(port);
				MonitorReportService.getInstance().start();
				MonitorReportServiceTotal.getInstance().start();  //统计流入、流出量
			}
		}
	}

	private void bindMonitorReportService(int port) {
		ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), Runtime.getRuntime().availableProcessors() + 1);

		ServerBootstrap bootstrap = new ServerBootstrap(factory);
		bootstrap.setPipelineFactory(new MonitorAgentSocketServerFactory());
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setOption("reuseAddress", true);
		bootstrap.bind(new InetSocketAddress(port));
		logger.info("monitor agent read listening on:" + port);
	}

}
