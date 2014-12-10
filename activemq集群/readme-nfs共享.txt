注：本文档还不够全，NFS客户端如何挂载需要研究


###NFS服务安装

1、 查看系统是否已安装NFS(一般系统默认会安装)
    
    rpm -qa |grep nfs
    rpm -qa |grep portmap

    如未安装，请手动进行安装     

2、 配置/etc/exports 共享目录（这里以2台机器为例）

    /home/hadoop2/activemq/sharedir 10.15.43.1(rw,sync,no_root_squash)
    /home/hadoop2/activemq/sharedir 10.15.43.6(rw,sync,no_root_squash)


    其中"/home/hadoop2/activemq/sharedir" 及 ip可以根据实际情况修改

3、 NFS服务器启动（portmap启动先于nfs ）

    service portmap start
    service nfs start

4、 NFS服务器状态

    service portmap status
    service nfs status


###NFS客户端挂载

mount(查。。。)



###activemq集群配置

1、 配置统一KahaDB文件目录

    修改$ACTIVEMQ_HOME/conf/activemq.xml 文件
     
    <persistenceAdapter>
 	<kahaDB directory="【共享目录】"/>
    </persistenceAdapter>


2、 activemq节点名称及路径

     <broker xmlns="http://activemq.apache.org/schema/core" 
          brokerName="【集群broker名】" dataDirectory="【共享目录上一级目录】">


3、 activemq 启动

    $ACTIVEMQ_HOME/bin/activemq start (如没有执行权限 chmod +x)


4、 java 调用
   
    failover:(tcp://broker1:61616,tcp://broker2:61616,tcp://broker3:61616)














    