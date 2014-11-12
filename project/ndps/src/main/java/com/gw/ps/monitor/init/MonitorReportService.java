package com.gw.ps.monitor.init;

import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.gw.ps.monitor.yt.BinaryWriteStream4;
import com.gw.ps.monitor.yt.TempMessage;

/**
 * @author mafei E-mail: mafei@gw.com.cn
 * @version 创建时间：2013-4-12 下午2:16:43 类说明
 */
public class MonitorReportService implements Runnable {
	static Logger logger = Logger.getLogger("LogReportSender");
	static LinkedBlockingQueue<Object> messageQueue = new LinkedBlockingQueue<Object>();

	private MonitorReportService() {
	}

	public static MonitorReportService getInstance() {
		return InnerHolder.INSTANCE;
	}

	private static class InnerHolder {
		static final MonitorReportService INSTANCE = new MonitorReportService();
	}

	public synchronized void start() {
		for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
			new Thread(this, "LogReportSender_" + i).start();
		}
	}

	public  static void add(TempMessage message) {
		if (messageQueue.size() == 10000)
			messageQueue.poll();
		messageQueue.add(message);
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (MonitorClientChannelFactory.size() == 0 || messageQueue.isEmpty()) {
					Thread.sleep(2000L);
					continue;
				}
				TempMessage message = (TempMessage) messageQueue.poll();
				if (message!=null) {
					
					JSONObject jsonObject = new JSONObject();
					short sendCmd = 6;
					
					jsonObject.put("level", message.getLevel());
					jsonObject.put("desc", message.getDesc());

					String sendData = jsonObject.toString();
					logger.debug(sendData);
					BinaryWriteStream4 writeStream = new BinaryWriteStream4();
					writeStream.Write(sendCmd);
					writeStream.Write(0);
					writeStream.WriteNoCompress(sendData, sendData.length());
					writeStream.Flush();
					MonitorClientChannelFactory.send(writeStream.getData());
				}
				if (messageQueue.isEmpty())
					Thread.sleep(50L);

			} catch (Exception ie) {
				logger.error(ie.getMessage(), ie);
			}
		}
	}

}
