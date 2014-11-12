package com.gw.ps.monitor.init;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import com.gw.ps.monitor.yt.BinaryWriteStream4;

/**
 * @author mafei E-mail: mafei@gw.com.cn
 * @version 创建时间：2013-4-18 上午9:51:49 类说明
 */
public class MonitorAgentReadHandler extends SimpleChannelUpstreamHandler {
	private static Logger logger = Logger.getLogger(MonitorAgentReadHandler.class);
	public static final ChannelGroup channelGroup = new DefaultChannelGroup();

	public int id;

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		logger.info(e.getChannel().getRemoteAddress() + " connected by agent socket! ");
		MonitorClientChannelFactory.add(ctx.getChannel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		logger.info("exception<" + e.getChannel().getRemoteAddress() + ">:" + e.getCause(), e.getCause());
		e.getChannel().close();
		MonitorClientChannelFactory.remove(ctx.getChannel());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		String value = (e.getMessage() == null ? null : (String) e.getMessage());
		if (value == null) {
			return;
		}

		if (value.equals("info")) {
			// logger.info("receive message");
		} else if (value.equals("port")) {
			JSONObject obj = new JSONObject();
			obj.put("listen", 6200);
			String port = obj.toString();

			BinaryWriteStream4 writeStream = new BinaryWriteStream4();
			writeStream.Write((short) 13);
			writeStream.Write(0);
			writeStream.WriteNoCompress(port, port.length());
			writeStream.Flush();
			byte[] data = writeStream.getData();
			ChannelBuffer cb = ChannelBuffers.buffer(data.length);
			cb.writeBytes(data);
			ctx.getChannel().write(cb);

			logger.info("send message!");
		}
		return;

	}
}
