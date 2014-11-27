#!/bin/bash
# website director vip.
SNS_VIP=10.15.43.20
SNS_RIP1=10.15.43.1
SNS_RIP2=10.15.43.3
case "$1" in 
start)  
  # set squid vip
  /sbin/ifconfig eth0:0 $SNS_VIP broadcast $SNS_VIP netmask 255.255.255.255 up
  /sbin/route add -host $SNS_VIP dev eth0:0
  /sbin/ipvsadm -A -t $SNS_VIP:80 -s rr
  /sbin/ipvsadm -a -t $SNS_VIP:80 -r $SNS_RIP1:80 -g -w 1
  /sbin/ipvsadm -a -t $SNS_VIP:80 -r $SNS_RIP2:80 -g -w 1  
  touch /var/lock/subsys/ipvsadm >/dev/null 2>&1  
  ;;  
stop)  
  /sbin/ipvsadm -C
  /sbin/ipvsadm -Z
  ifconfig eth0:0 down
  route del $SNS_VIP
  rm -rf /var/lock/subsys/ipvsadm >/dev/null 2>&1
  echo "ipvsadm stoped" 
  ;;  
status)  
  if [ ! -e /var/lock/subsys/ipvsadm ];then
  echo "ipvsadm stoped"
  exit 1  
  else
  echo "ipvsadm OK" 
  fi 
  ;;  
*)  
 echo "Usage: $0 {start|stop|status}" 
 exit 1  
esac 
 exit 0
