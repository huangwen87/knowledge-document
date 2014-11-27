脚本默认以2台Realserver机器+1台Director Server为例

###ipvsadm 安装（linux内核版本2.6.32）
ln -s /usr/src/kernels/2.6.32-220.el6.x86_64/ /usr/src/linux #不做此步骤，安装会报错 
tar zxvf ipvsadm-1.24.tar.gz
cd ipvsadm-1.24
make && make install
ipvsadm  #执行ipvsadm命令，把LVS添加到linux内核中



###配置说明(端口可以自行改之，且要全部一致，如这里默认是80)

1、lvs_dr.sh脚本的修改
SNS_VIP=【虚拟ip,设置为同一网段】
SNS_RIP1=【Realserver 1 的ip地址】
SNS_RIP2=【Realserver 2 的ip地址】

如果有多台Realserver，添加
SNS_RIP3=【Realserver 3 的ip地址】
SNS_RIP4=【Realserver 4 的ip地址】
....
SNS_RIPn=【Realserver n 的ip地址】
且在start中添加路由
sbin/ipvsadm -a -t $SNS_VIP:80 -r $SNS_RIP3:80 -g -w 1
...
/sbin/ipvsadm -a -t $SNS_VIP:80 -r $SNS_RIPn:80 -g -w 1


2、lvs_rs.sh脚本的修改
SNS_VIP=【虚拟ip, 跟上面一致】


###脚本执行
1） lvs_dr.sh（放入Director Server机器上） 
添加执行权限
chmod +x lvs_dr.sh
#方便启动
cp lvs_dr.sh /etc/rc.d/init.d/
#启动lvs服务
service lvs_dr.sh start

2）lvs_rs.sh  (放入Realserver每台机器上)
添加执行权限
chmod +x lvs_rs.sh
#方便启动
cp lvs_rs.sh /etc/rc.d/init.d/
#启动lvs服务
service lvs_rs.sh start



