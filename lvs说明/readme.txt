�ű�Ĭ����2̨Realserver����+1̨Director ServerΪ��

###ipvsadm ��װ��linux�ں˰汾2.6.32��
ln -s /usr/src/kernels/2.6.32-220.el6.x86_64/ /usr/src/linux #�����˲��裬��װ�ᱨ�� 
tar zxvf ipvsadm-1.24.tar.gz
cd ipvsadm-1.24
make && make install
ipvsadm  #ִ��ipvsadm�����LVS��ӵ�linux�ں���



###����˵��(�˿ڿ������и�֮����Ҫȫ��һ�£�������Ĭ����80)

1��lvs_dr.sh�ű����޸�
SNS_VIP=������ip,����Ϊͬһ���Ρ�
SNS_RIP1=��Realserver 1 ��ip��ַ��
SNS_RIP2=��Realserver 2 ��ip��ַ��

����ж�̨Realserver�����
SNS_RIP3=��Realserver 3 ��ip��ַ��
SNS_RIP4=��Realserver 4 ��ip��ַ��
....
SNS_RIPn=��Realserver n ��ip��ַ��
����start�����·��
sbin/ipvsadm -a -t $SNS_VIP:80 -r $SNS_RIP3:80 -g -w 1
...
/sbin/ipvsadm -a -t $SNS_VIP:80 -r $SNS_RIPn:80 -g -w 1


2��lvs_rs.sh�ű����޸�
SNS_VIP=������ip, ������һ�¡�


###�ű�ִ��
1�� lvs_dr.sh������Director Server�����ϣ� 
���ִ��Ȩ��
chmod +x lvs_dr.sh
#��������
cp lvs_dr.sh /etc/rc.d/init.d/
#����lvs����
service lvs_dr.sh start

2��lvs_rs.sh  (����Realserverÿ̨������)
���ִ��Ȩ��
chmod +x lvs_rs.sh
#��������
cp lvs_rs.sh /etc/rc.d/init.d/
#����lvs����
service lvs_rs.sh start



