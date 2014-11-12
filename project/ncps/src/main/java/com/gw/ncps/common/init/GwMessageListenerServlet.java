package com.gw.ncps.common.init;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.activemq.web.MessageListenerServlet;
import org.springframework.stereotype.Service;

import com.gw.ncps.common.util.JsonUtil;
import com.gw.ncps.model.LogMessage;
import com.gw.ncps.model.NewsLogMessage;
import com.gw.ncps.model.Text;

@SuppressWarnings("serial")
@Service
public class GwMessageListenerServlet extends MessageListenerServlet {

	@Override
	protected void writeMessageResponse(PrintWriter writer, Message message, String id, String destinationName) throws JMSException, IOException {
		writer.print("<response id='");
		writer.print(id);
		writer.print("'");
		if (destinationName != null) {
			writer.print(" destination='" + destinationName + "' ");
		}
		writer.print(">");
		//监听去重系统日志信息
		if (message instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) message;
			String txt = textMsg.getText();
			if (txt != null) {

				try {
					// 读取pb消息，转换成对象
					NewsLogMessage.LogMessage lm = NewsLogMessage.LogMessage.parseFrom(txt.getBytes());
					lm.getMessageID();

					NewsLogMessage.Text t = lm.getText();
					Text mt = new Text();

					mt.setClassName(t.getClassName());
					mt.setJframe(t.getType());
					mt.setLog(t.getLogList());
					mt.setRank(t.getLevel());
					LogMessage mlm = new LogMessage();
					mlm.setMessageID(lm.getMessageID());
					mlm.setText(mt);
					writer.print(JsonUtil.object2Json(mlm));

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				

			}
		} else if (message instanceof ObjectMessage) {
			ObjectMessage objectMsg = (ObjectMessage) message;
			Object object = objectMsg.getObject();
			if (object != null) {
				writer.print(object.toString());
			}
		}
		writer.println("</response>");
	}

}
