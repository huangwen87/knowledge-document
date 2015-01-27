#!/bin/sh

##################################################################
####
#### 本脚本为copy原hadoop集群原始数据到新的hadoop集群-下载上传
####
#### 1、数据拷贝以天为单位
#### 2、  
#### 样例执行命令: 
####           ./hadoop2databak1.sh mobile 20141028 3
#### 会拷贝20141028 20141029 20141030 20141031总共四天的数据
#### 
##################################################################

localPath="/tmp/mydata"
newClusterIP="10.15.144.56"
newClusterName="hadoop"

usage(){
   echo "用法：./hadoop2databak1.sh dataType startDate longDay
   其中 dateType 可为:
        pc 
        mobile
        weixin 
   startDate 为开始时间,比如20150126
   longDay 为以开始时间(不含)的总共天数,如 2"
            
}

casage(){

   case $1 in
         --help)
           usage
         ;; 
   esac
   
}


CopyFromHdfsToLocal(){

     hdfsPath=""
     year=${2:0:4}
     month=${2:4:2}
     day=${2:6:2}
     case $1 in
          "pc")
              hdfsPath="/BA/log/input/$year/$month/$day"
          ;;
          "mobile")
              hdfsPath="/BA/data/crm_log/raw_log/$year/$month/$day"
          ;;
          "weixin")
              hdfsPath="/activeMQtest/adsQueue/$year/$month/$day"
     esac

     echo "hdfsPath: $hdfsPath"

     isExistHdfsPath=`hadoop fs -ls $hdfsPath >/dev/null 2>/dev/null`
     if [ ! 0 -eq $? ];then
            echo "====hdfs目录$hdfsPath不存在, 无法拷贝===="
            exit 0
     fi

     if [ ! -d "$localPath/$year/$month" ];then
         mkHdfsDir=`mkdir -p $localPath/$year/$month`
     fi
 
     copyTolocal=`hadoop fs -get $hdfsPath"*" "$localPath/$year/$month"`
}

CopyFromLocalToNewCluster(){
     echo "=============copy to newCluster start==========="
     command=`scp -r $localPath $newClusterName@$newClusterIP:$localPath`
     rmLocalData=`rm -rf $localPath`
     echo "=============copy to newCluster end============="
}


StartCopy(){
     echo "=============copy "$1" data start============"
     startDay=$2
     endDay=`/bin/date -d $2+$3day "+%Y%m%d"`
     
     while [ $startDay -le $endDay ]; do
           CopyFromHdfsToLocal $1 $startDay
           startDay=`/bin/date -d $startDay+1day "+%Y%m%d"`
     done
     echo "=============copy "$1" data end============="

     if [ -d "$localPath" ]; then
          CopyFromLocalToNewCluster
     else
          echo "====目录$day不存在, 无法copy"
          exit 0
     fi
}



if [ $# == 0 ] || [ $# -ne 3 ]; then 
     usage
else
     dataType=$1
     if [ $dataType != "pc" ] && [ $dataType != "mobile" ] && [ $dataType != "weixin" ]; then
         usage
     else
        startDate=$2
        dateRegex="^[0-9]{8}$" 
        if echo "$startDate" | egrep -q "$dateRegex"; then
           longDay=$3
           longRegx="^[0-9]+$"
           if echo "$longDay" | egrep -q "$longRegx"; then
              StartCopy $dataType $startDate $longDay 
           else
              usage
           fi
        else
           usage    
        fi
     fi    
fi
