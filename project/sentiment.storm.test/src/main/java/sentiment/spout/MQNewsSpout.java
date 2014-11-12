package sentiment.spout;

import java.util.Map;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import sentiment.configure.Configure;

import classify.protobuf.PbNewsSentimentUntreated;

import com.google.protobuf.InvalidProtocolBufferException;
//import com.gw.protobuf.NewsFilter.NewsFilterDust;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class MQNewsSpout extends BaseRichSpout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6416124835888617249L;
	
	private SpoutOutputCollector collector;
	
	private ActiveMQConnectionFactory factory;
	private Connection connection;
	private Session session;
	private Queue queue;
	private MessageConsumer consumer;
	private static Configure configure;
	
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
		Configure.getConfigure().init((String)conf.get("ConfPath"));
		configure = Configure.getConfigure();
		factory = new ActiveMQConnectionFactory(configure.mqSourceUrl);
		//factory = new ActiveMQConnectionFactory("failover:(tcp://10.15.89.177:61616,tcp://10.15.89.176:61616)?randomize=false");
		try{
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = session.createQueue("news.sentiment.untreated.queue"); 
			consumer = session.createConsumer(queue);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	public void nextTuple() {
		try{
			consumer.setMessageListener(new MessageListener() {
					public void onMessage(Message message) {
						
						MapMessage mm = (MapMessage) message;
						try {
							byte[] str = mm.getBytes("key");
							try {
								// old version
								//NewsFilterDust news = NewsFilterDust.parseFrom(str);
								PbNewsSentimentUntreated.NewsSentimentUntreated news = PbNewsSentimentUntreated.NewsSentimentUntreated.parseFrom(str);
								collector.emit(new Values(news));
		            		}
		            		catch (InvalidProtocolBufferException e) {
		            			System.out.println(e.toString());
		            			e.printStackTrace();
		            		}
						} 
						catch (JMSException e) {
							System.out.println(e.toString());
							e.printStackTrace();
						}
					}
				});
			connection.start();
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("news"));
	}

}
