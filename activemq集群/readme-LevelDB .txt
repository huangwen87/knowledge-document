###activemq��Ⱥ����
�汾�� activemq 5.9+  (������5.10Ϊ��)


1�� �޸�$ACTIVEMQ_HOME/conf/activemq.xml �ļ�
     
    <!--
     	<persistenceAdapter>
    		<kahaDB directory="${activemq.data}/kahadb"/>		
	</persistenceAdapter>
     -->
     <persistenceAdapter>
    	<replicatedLevelDB
      		directory="${activemq.data}/leveldb"
      		replicas="3"
      		bind="tcp://0.0.0.0:0"
      		zkAddress="192.168.2.161:2181,192.168.2.145:2181,192.168.2.146:2181"
      		hostname="192.168.2.161"
      		sync="local_disk"
      		zkPath="/activemq/leveldb-stores"
        />
      </persistenceAdapter>

    ע�� ����hostnameÿ̨������ͬ

2�� broker-name��ͳһ

     <broker xmlns="http://activemq.apache.org/schema/core" 
          brokerName="����Ⱥbroker����" dataDirectory="${activemq.data}">


3���汾5.10.0���ڵ�������ͻ

   �Ƴ�libĿ¼�е�pax-url-aether-1.5.2.jar����
   ע�͵������ļ��е���־���ã�
   <bean id="logQuery" class="org.fusesource.insight.log.log4j.Log4jLogQuery"
    	lazy-init="false" scope="singleton"
    	init-method="start" destroy-method="stop">
   </bean>



4�� activemq ����

    $ACTIVEMQ_HOME/bin/activemq start (��û��ִ��Ȩ�� chmod +x)


5�� java ����
   
    failover:(tcp://broker1:61616,tcp://broker2:61616,tcp://broker3:61616)














    