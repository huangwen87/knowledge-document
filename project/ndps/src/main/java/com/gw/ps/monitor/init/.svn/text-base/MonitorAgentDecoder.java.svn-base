package com.gw.ps.monitor.init;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * @author mafei E-mail: mafei@gw.com.cn
 * @version 创建时间：2013-4-18 下午5:20:18 类说明
 */
public class MonitorAgentDecoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (buffer.readableBytes() < 5)
			return null;
		byte[] subarray = new byte[5];
		buffer.readBytes(subarray);

		return new String(subarray).trim();
	}
}
