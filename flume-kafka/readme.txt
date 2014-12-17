###flume作为kafka source 软件环境
flume 1.5.0.1
kafka 2.9.2-0.8.1
zookeeper 3.3.5


###flume 中lib依赖
kafka-sources.jar(自己编写)
kafka_2.8.2-0.8.1.jar
zkclient-0.3.jar
zookeeper-3.3.5.jar
metrics-core-2.2.0.jar
scala-library-2.8.1.jar



###kafka 作为sink 参数说明

partitions：自己改写的，应该和topic的partitions一致
其他      : 参见http://archive.cloudera.com/cdh5/cdh/5/flume-ng/FlumeUserGuide.html（kafka sink）




