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

import sentiment.configure.Configure;

import com.gw.protobuf.NewsSentiResult.NewsSentiment;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

@SuppressWarnings("serial")
public class GoToMQBolt extends BaseRichBolt{
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageProducer producer;
	private static Configure configure;

	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		
		Configure.getConfigure().init((String)stormConf.get("ConfPath"));
		configure = Configure.getConfigure();
		
		connectionFactory = new ActiveMQConnectionFactory(configure.mqDestinationUrl);
		try{
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("news.sentiment.predict.wangSourcequeue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	public void execute(Tuple input) {
		NewsSentiment newsData = (NewsSentiment)input.getValue(0);
		try{
			MapMessage message = session.createMapMessage();
			message.setBytes("key", newsData.toByteArray());
			producer.send(message);
			
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}


}
