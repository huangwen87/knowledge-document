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

import com.gw.protobuf.NewsSentiResult.NewsSentiment;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;


@SuppressWarnings("serial")
public class GoToCandidateMQBolt extends BaseRichBolt{
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
			destination = session.createQueue("news.sentiment.candidate.queue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	public void execute(Tuple input) {
		NewsSentiment newsData = (NewsSentiment)input.getValue(0);
		if(newsData.getIsFiltered() == false){
			double posRate = newsData.getPosRate();
			double negRate = newsData.getNegRate();
			if((posRate - 51.0) > 0.000000000000001 || (negRate - 54.5) > 0.000000000001){
				try{
					MapMessage message = session.createMapMessage();
					message.setBytes("key", newsData.toByteArray());
					producer.send(message);
				}catch(JMSException e){
					e.printStackTrace();
				}
			}
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}


}
