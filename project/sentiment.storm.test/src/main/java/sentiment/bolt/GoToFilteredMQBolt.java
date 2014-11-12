package sentiment.bolt;

import java.util.Map;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import classify.protobuf.InnerUsing;

@SuppressWarnings("serial")
public class GoToFilteredMQBolt extends BaseRichBolt{
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageProducer producer;
	

	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		connectionFactory = new ActiveMQConnectionFactory("tcp://10.15.107.75:61616");
		try{
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("news.sentiment.filtered.queue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	public void execute(Tuple input) {
		InnerUsing.InnerNews newsData = (InnerUsing.InnerNews)input.getValue(0);
		if(newsData.getIsFiltered()){
			try{
				MapMessage message = session.createMapMessage();
				message.setBytes("key", newsData.toByteArray());
				producer.send(message);
			}catch(JMSException e){
				e.printStackTrace();
			}
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}


}
