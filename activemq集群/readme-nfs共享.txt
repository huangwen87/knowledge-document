ע�����ĵ�������ȫ��NFS�ͻ�����ι�����Ҫ�о�


###NFS����װ

1�� �鿴ϵͳ�Ƿ��Ѱ�װNFS(һ��ϵͳĬ�ϻᰲװ)
    
    rpm -qa |grep nfs
    rpm -qa |grep portmap

    ��δ��װ�����ֶ����а�װ     

2�� ����/etc/exports ����Ŀ¼��������2̨����Ϊ����

    /home/hadoop2/activemq/sharedir 10.15.43.1(rw,sync,no_root_squash)
    /home/hadoop2/activemq/sharedir 10.15.43.6(rw,sync,no_root_squash)


    ����"/home/hadoop2/activemq/sharedir" �� ip���Ը���ʵ������޸�

3�� NFS������������portmap��������nfs ��

    service portmap start
    service nfs start

4�� NFS������״̬

    service portmap status
    service nfs status


###NFS�ͻ��˹���

mount(�顣����)



###activemq��Ⱥ����

1�� ����ͳһKahaDB�ļ�Ŀ¼

    �޸�$ACTIVEMQ_HOME/conf/activemq.xml �ļ�
     
    <persistenceAdapter>
 	<kahaDB directory="������Ŀ¼��"/>
    </persistenceAdapter>


2�� activemq�ڵ����Ƽ�·��

     <broker xmlns="http://activemq.apache.org/schema/core" 
          brokerName="����Ⱥbroker����" dataDirectory="������Ŀ¼��һ��Ŀ¼��">


3�� activemq ����

    $ACTIVEMQ_HOME/bin/activemq start (��û��ִ��Ȩ�� chmod +x)


4�� java ����
   
    failover:(tcp://broker1:61616,tcp://broker2:61616,tcp://broker3:61616)














    