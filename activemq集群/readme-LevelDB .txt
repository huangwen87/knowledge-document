###activemq集群配置
版本： activemq 5.9+  (这里以5.10为例)


1、 修改$ACTIVEMQ_HOME/conf/activemq.xml 文件
     
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

    注： 其中hostname每台机器不同

2、 broker-name的统一

     <broker xmlns="http://activemq.apache.org/schema/core" 
          brokerName="【集群broker名】" dataDirectory="${activemq.data}">


3、版本5.10.0存在的依赖冲突

   移除lib目录中的pax-url-aether-1.5.2.jar包；
   注释掉配置文件中的日志配置；
   <bean id="logQuery" class="org.fusesource.insight.log.log4j.Log4jLogQuery"
    	lazy-init="false" scope="singleton"
    	init-method="start" destroy-method="stop">
   </bean>



4、 activemq 启动

    $ACTIVEMQ_HOME/bin/activemq start (如没有执行权限 chmod +x)


5、 java 调用
   
    failover:(tcp://broker1:61616,tcp://broker2:61616,tcp://broker3:61616)














    