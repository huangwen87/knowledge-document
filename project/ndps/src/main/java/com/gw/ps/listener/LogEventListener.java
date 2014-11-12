package com.gw.ps.listener;

import java.util.Properties;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.BaseNews;
import com.gw.ps.model.NewsLog;
import com.gw.ps.monitor.init.MonitorReportService;
import com.gw.ps.monitor.yt.TempMessage;
import com.gw.ps.pbbean.PbLogMessage;
import com.gw.ps.utils.LogUtil;

/**
 * 启动时连接activemq，接收Topic消息
 * 
 * @author JohnmyWork
 * 
 */

@Component
public class LogEventListener implements MessageListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	private static Properties pro = null;

	static {
		try {
			pro = new Properties();
			pro.load(LogEventListener.class.getClassLoader().getResourceAsStream("filter.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage map = (MapMessage) message;
			if (map != null) {
				try {
					// 读取pb消息，转换成对象
					PbLogMessage.LogMessage lm = PbLogMessage.LogMessage.parseFrom(map.getBytes("key"));
					String type = lm.getText().getType();
					Object bn = getNewsClass(type);
					if (bn != null) {
						bn = ((BaseNews) bn).manipulate(lm);
						ndrDao.addNewsClass(bn);
					}

					if ("log".equals(type))
						sendMsg(bn);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Object getNewsClass(String type) {
		Object obj = null;
		try {
			if (pro.containsKey(type) && pro.get(type) != null) {
				obj = Class.forName("com.gw.ps.model." + pro.get(type)).newInstance();
			} else {
				obj = new NewsLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 发送消息到日志监控
	 * 
	 * @param bn void
	 * @author JohnmyWork
	 * @date 2013-8-16
	 */
	public void sendMsg(Object bn) {
		NewsLog nl = (NewsLog) bn;
		TempMessage tm = new TempMessage();
		String level = nl.getLevel();
		tm.setDesc(nl.getLog());
		if (level.equalsIgnoreCase("info")) {
			tm.setLevel("4");
		} else if (level.equalsIgnoreCase("warn")) {
			tm.setLevel("2");
		} else if (level.equalsIgnoreCase("error")) {
			tm.setLevel("1");
		} else {
			tm.setLevel("4");
			tm.setDesc(level + "----" + nl.getLog());
		}

		MonitorReportService.add(tm);

	}

	// 发送消息
	// @Resource(name = "jmsTemplate")
	// private JmsTemplate jmsTemplate;
	// protected void sendMessage(final LogMessage lm) {
	// jmsTemplate.setDefaultDestination(topicDestination2);
	// jmsTemplate.send(new MessageCreator() {
	// @Override
	// public Message createMessage(Session session) throws JMSException {
	// return session.createTextMessage(lm.getText().getClassName());
	// }
	// });
	// }

}
