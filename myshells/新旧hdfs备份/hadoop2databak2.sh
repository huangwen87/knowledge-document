#!/bin/sh

##################################################################
####
#### 本脚本为copy原hadoop集群原始数据到新的hadoop集群-上传到新hdfs
####
#### 1、数据拷贝以天为单位
#### 2、  
#### 样例执行命令: 
####           ./hadoop2databak2.sh pc
#### 拷贝刚刚hadoop2databak1.sh 执行的数据到hdfs
##################################################################

localPath="/tmp/mydata"

usage(){
   echo "用法：./hadoop2databak2.sh dataType
   其中 dateType 可为:
        pc 
        mobile
        weixin
   "
   exit 0
}


StartCopy(){
     hdfsPath=""
     case $1 in
          "pc")
              hdfsPath="/BA/log/input"
          ;;
          "mobile")
              hdfsPath="/BA/data/crm_log/raw_log"
          ;;
          "weixin")
              hdfsPath="/activeMQtest/adsQueue"
          ;;
          *)
              usage
          ;; 
     esac
    
     echo "hdfsPath: $hdfsPath"
 
     echo "=============copy "$1" data start============"
     for name in "`ls $localPath`"
     do
          upload=`hadoop fs -copyFromLocal $localPath/* $hdfsPath`   
     done 
     rmLocalPath=`rm -rf $localPath`
     echo "=============copy "$1" data end==-==========="

}



if [ $# == 0 ] || [ $# -ne 1 ]; then 
     usage
else
     if [ -d "$localPath" ]; then 
          StartCopy $1
     else
          echo "====目录$localPath不存在, 无法copy====="
          exit 0
     fi  


fi
