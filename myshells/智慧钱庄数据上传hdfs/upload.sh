#!/bin/bash

###########################
####
#### 本脚本请用root用户执行
####
#### 样例执行命令 "./upload.sh /home/ftp"
###########################


#logdir="/home/ftp"
logdir=$1

#不考虑隐藏目录和文件
FileList=`ls ${logdir} 2>/dev/null`

while [ "" != "$FileList" ]
do
     now=`date +%Y%m%d`
     for pFile in $FileList
     do
        if [ ! -d ${logdir}"/"${pFile} ]
        then 
	#日期，140912 格式
	date=${pFile:0:6}
	fileName=${date}"access.log.tar.gz"

	#解压前目录
	gzdir=${logdir}"/"${fileName}

	#解压命令
	command=`tar zxf ${gzdir}`


	#上传到hdfs的目录
	year="20"${pFile:0:2}
	month=${pFile:2:2}
	day=${pFile:4:2}
	hdfsDir="/wisdomMoney/logs/${year}/${month}/${day}/"

        #目录创建
        isExistHdfsDir=`hadoop fs -ls $hdfsDir >/dev/null 2>/dev/null`
        if [ ! 0 -eq $? ];then
            echo "${now}==hdfs目录$hdfsDir不存在，创建！==" >>./server.log
            mkHdfsDir=`hadoop fs -mkdir -p $hdfsDir`
            echo "${now}==hdfs目录$hdfsDir创建完毕！==">>./server.log
        fi


	#上传文件夹是否存在
	tmp=${logdir}"/tools/mnt/webnews/"  #解压后的目录
	if [ ! -d "$tmp" ];then
   		echo "${now}==文件不存在，请查看${tmp}目录==" >>./server.log
	else
   		uploadCommand=`hadoop fs -copyFromLocal ${tmp}"*" $hdfsDir`
   		if [ 0 -eq $? ];then
      			echo "${now}==已经上传至hdfs ${hdfsDir}==" >>./server.log
                        #原始文件mv
                        mvcommand=`mv ${logdir}"/"${fileName} ${logdir}"/bak/"`
   		else
     		        echo "${now}==上传出问题，请查看!==" >>./server.log
   		fi
	fi

	#解压后文件
        #rmtmpcommand=`rm -rf ${logdir}"/tools/mnt"`
    fi
     done
     echo "${now}==全部执行完成==" >>./server.log
     exit 1
done
