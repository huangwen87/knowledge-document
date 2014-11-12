#!/usr/bin/env bash

#
#帮助信息输出函数
#
usage(){
  echo "用法：./bin/test.sh [options]
  其中的选项包括：
      start 启动app
      stop  停止app"
}

JVM_OPTIONS="-server -Xms20m -Xmx20m -XX:MaxPermSize=20M
             -XX:+HeapDumpOnOutOfMemoryError"


start(){
        path=`dirname "$0"`
        path=`cd "$path/.."; pwd`
          
        # add libs to CLASSPATH
        for f in $path/lib/*.jar; do
           CLASSPATH=${CLASSPATH}:$f;
        done
        mainclass="com.hskj.demo.MainEnter"
        command=`cd "$path"; java -classpath $CLASSPATH $mainclass`  
}

#根据实际情况kill掉进程
stop(){
  echo "stop...."
}


error_info(){
  echo $1
}


casage(){
  case $1 in
        --help)
            usage
        ;;
        start)
          start
        ;;
        stop)
          stop
        ;;
        *)
        error_info "错误，找不到该选项，请使用--help察看可用选项"
        ;;
    esac
}

if [ $# == 0  ] || [ $# -gt 1  ]
 then 
    usage
else
    command=$1
    casage $command
fi

