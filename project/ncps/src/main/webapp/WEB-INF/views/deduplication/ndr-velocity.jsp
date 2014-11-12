<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
			<!-- load general head content -->
        	<%@include file="/WEB-INF/views/common/head.jsp" %>
    </head>
    
    <body>
    	<!-- load general top content -->
        <%@include file="/WEB-INF/views/common/top.jsp" %>
        <div class="container-fluid">
            <div class="row-fluid">
            	<!-- load general leftmenu content -->
                <%@include file="/WEB-INF/views/common/left.jsp" %>
                <div id="content" class="span10">
                	<div>
						<ul class="breadcrumb">
							<li>
								<a href="#">首页</a>
								<span class="divider">/</span>
							</li>
							<li>
								<a href="#">新闻去重速率</a>
							</li>
						</ul>
					</div>
					<div class="row-fluid sortable">
						<div class="box">
							<div class="box-header well">
								<h2>
									<i class="icon-list-alt"></i>新闻去重速率</h2>
								<div class="box-icon">
									<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
									<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
									<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
								</div>
							</div>
							<div class="box-content">
								<div id="container" style="height: 500px"></div>
							</div>
						</div>
					</div>
                </div>
        	</div>
        	<hr>
        	<!-- load general footer content -->
        	<%@include file="/WEB-INF/views/common/footer.jsp" %>
        	</div>
        	<!-- load general bottom js -->
        	<%@include file="/WEB-INF/views/common/bottom.jsp" %>
        	<script  src="<c:url value="resources/plugin/highcharts/highstock.js" />"></script>
			<script  src="<c:url value="resources/plugin/highcharts/modules/exporting.js" />"></script>
			<script type="text/javascript">
				$(function() {
					var maxTimestamp=0;
					seriesOptions = [];
				    seriesOptions[0] = {
							name: "垃圾识别",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	type: "POST",
										   url: "log/dust",
										   async: false,
										   success: function(msg){
										   if(msg=="null") return;
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
											 for (i = 0; i <len1; i++) {
								                    data.push({
								                    x:parseInt((jsonList1[i].timestamp)/1000)*1000, 
								                    y:jsonList1[i].timedust
								                    });
								             }
								          if(len1>0)maxTimestamp = jsonList1[len1-1].timestamp;
								    	}
							    	});
				                return data;
				            })(),
				            turboThreshold:1000000,//最大数据条数
							marker : {
								enabled : true,
								radius : 3
							},
							shadow : true,
							tooltip : {
								valueDecimals : 2
							}
					};
				    seriesOptions[1] = {
							name: "小文本",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	type: "POST",
										   url: "log/shingle",
										   async: false,
										   success: function(msg){
										   if(msg=="null") return;
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
											 for (i = 0; i <len1; i++) {
								                    data.push({
								                    x:parseInt((jsonList1[i].timestamp)/1000)*1000, 
								                    y:jsonList1[i].timeshingle
								                    });
								             }
								          if(len1>0)maxTimestamp = jsonList1[len1-1].timestamp;
								    	}
							    	});
				                return data;
				            })(),
				            turboThreshold:1000000,//最大数据条数
							marker : {
								enabled : true,
								radius : 3
							},
							shadow : true,
							tooltip : {
								valueDecimals : 2
							}
					};
					
				    seriesOptions[2] = {
							name: "大文本",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	type: "POST",
										   url: "log/shingling",
										   async: false,
										   success: function(msg){
										   if(msg=="null") return;
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
											 for (i = 0; i <len1; i++) {
								                    data.push({
								                    x:parseInt((jsonList1[i].timestamp)/1000)*1000, 
								                    y:jsonList1[i].timeshingling
								                    });
								             }
								          if(len1>0)maxTimestamp = jsonList1[len1-1].timestamp;
								    	}
							    	});
				                return data;
				            })(),
				            turboThreshold:1000000,//最大数据条数
							marker : {
								enabled : true,
								radius : 3
							},
							shadow : true,
							tooltip : {
								valueDecimals : 2
							}
					};
				    
				    seriesOptions[3] = {
							name: "相似度",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	type: "POST",
										   url: "log/simhash",
										   async: false,
										   success: function(msg){
										   if(msg=="null") return;
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
											 for (i = 0; i <len1; i++) {
								                    data.push({
								                    x:parseInt((jsonList1[i].timestamp)/1000)*1000, 
								                    y:jsonList1[i].timesimhash
								                    });
								             }
								          if(len1>0)maxTimestamp = jsonList1[len1-1].timestamp;
								    	}
							    	});
				                return data;
				            })(),
				            turboThreshold:1000000,//最大数据条数
							marker : {
								enabled : true,
								radius : 3
							},
							shadow : true,
							tooltip : {
								valueDecimals : 2
							}
					};
				    
					
					Highcharts.setOptions({
						global: {
							useUTC: false
						}
					});

					// Create the chart
					$('#container').highcharts('StockChart', {
						chart : {
							events: {
				                load: getData
				            }
						},
						rangeSelector : {
							inputEnabled:false,
							selected : 1,
							buttons: [ {
								type: 'minute',
								count: 10,
								text: '分'
							},{
								type: 'minute',
								count: 60,
								text: '时'
							}, {
								type: 'day',
								count: 1,
								text: '天'
							}, {
								type: 'week',
								count: 1,
								text: '周'
							}, {
								type: 'month',
								count:1,
								text: '月'
							}, {
								type: 'all',
								text: 'All'
							}]
						},
						title : {
							text : '新闻去重速率'
						},
						tooltip: {
							shared: true,
				            formatter: function() {
				            	    var s = '在'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x);
				            	    $.each(this.points, function(i, point) {
				            	    	if(i == 0)
				    					  s += '<br/><span style="color:#33aaff">垃圾识别：</span>'+ Highcharts.numberFormat(point.y,2);
				    					else if( i == 1)
				    				      s += '<br/><span style="color:#000011">小文本： </span>'+ Highcharts.numberFormat(point.y,2);
				    				    else if ( i == 2)
				    				      s += '<br/><span style="color:#337711">大文本： </span>'+ Highcharts.numberFormat(point.y,2);  
				    				    else if ( i == 3)
					    				      s += '<br/><span style="color:#990000">相似度： </span>'+ Highcharts.numberFormat(point.y,2);
				    				});
				    				return s;
				                   // '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>';
				            },
				           // pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
						    valueDecimals: 2
				            
				        }, 
				        series: seriesOptions
					});
					 //获取数据
				function getData() {
				          var series = this.series[0];
				          setInterval(function(){
							 $.ajax({
								    	type: "POST",
										   url: "log/dust/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (var i = 0; i <len2; i++) {
								                    series.addPoint([jsonList2[i].timestamp, jsonList2[i].timedust], true, true);
								             }
								            if(len2>0)maxTimestamp = jsonList2[len2-1].timestamp;
								    	}
							    	});	
				          }, 60000);
				          var series1 = this.series[1];
				          setInterval(function(){
							 $.ajax({
								    	type: "POST",
										   url: "log/shingle/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (var i = 0; i <len2; i++) {
								                    series1.addPoint([jsonList2[i].timestamp, jsonList2[i].timeshingle], true, true);
								             }
								            if(len2>0)maxTimestamp = jsonList2[len2-1].timestamp;
								    	}
							    	});	
				          }, 60000);
				          
				          var series2 = this.series[2];
				          setInterval(function(){
							 $.ajax({
								    	type: "POST",
										   url: "log/shingling/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (i = 0; i <len2; i++) {
								                    series2.addPoint([jsonList2[i].timestamp, jsonList2[i].timeshingling], true, true);
								             }
								            if(len2>0)maxTimestamp = jsonList2[len2-1].timestamp;
								    	}
							    	});	
				          }, 60000);
				          
				          
				          var series3 = this.series[3];
				          setInterval(function(){
							 $.ajax({
								    	type: "POST",
										   url: "log/simhash/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (i = 0; i <len2; i++) {
								                    series3.addPoint([jsonList2[i].timestamp, jsonList2[i].timesimhash], true, true);
								             }
								            if(len2>0)maxTimestamp = jsonList2[len2-1].timestamp;
								    	}
							    	});	
				          }, 60000);
				          

				}
				})
				</script>
    </body>

</html>