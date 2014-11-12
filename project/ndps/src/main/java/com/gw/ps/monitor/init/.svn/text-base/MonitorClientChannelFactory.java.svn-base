package com.gw.ps.monitor.init;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jetty.util.ConcurrentHashSet;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

/**
 * @author mafei E-mail: mafei@gw.com.cn
 * @version 创建时间：2013-4-24 下午6:10:23 类说明
 */
public class MonitorClientChannelFactory {
	static Set<Channel> channelSet = new ConcurrentHashSet<Channel>();

	public static int size() {
		return channelSet.size();
	}

	public static void add(Channel channel) {
		channelSet.add(channel);
	}

	public static void remove(Channel channel) {
		channelSet.remove(channel);
	}

	public static void send(byte[] data) {
		Iterator<Channel> iter = channelSet.iterator();
		while (iter.hasNext()) {
			Channel channel = iter.next();
			if (channel.isOpen() && channel.isWritable()) {
				ChannelBuffer cb = ChannelBuffers.buffer(data.length);
				cb.writeBytes(data);
				channel.write(cb);
				break;
			} else {
				iter.remove();
			}
		}
	}
}
