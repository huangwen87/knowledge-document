package com.gw.ps.monitor.init;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor;

/**
 * @author mafei E-mail: mafei@gw.com.cn
 * @version 创建时间：2013-4-18 上午9:50:49 类说明
 */
public class MonitorAgentSocketServerFactory implements ChannelPipelineFactory {
	private final ExecutionHandler exeHandler = new ExecutionHandler(new MemoryAwareThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 0, 0, 10, TimeUnit.SECONDS, Executors.defaultThreadFactory()));

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();

		pipeline.addLast("frameDecoder", new MonitorAgentDecoder());
		pipeline.addLast("execution", exeHandler);
		pipeline.addLast("handler", new MonitorAgentReadHandler());

		return pipeline;
	}
}
