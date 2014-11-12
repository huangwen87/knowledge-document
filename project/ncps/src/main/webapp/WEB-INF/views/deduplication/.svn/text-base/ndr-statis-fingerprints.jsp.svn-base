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
								<a href="#">指纹数量统计</a>
							</li>
						</ul>
					</div>
					<div class="row-fluid sortable">
						<div class="box">
							<div class="box-header well">
								<h2>
									<i class="icon-list-alt"></i>指纹量统计</h2>
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
							name: "signal",
							color: "#2F7ED8",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	   type: "POST",
										   url: "log/fingerprints",
										   async: false,
										   success: function(msg){
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
										   for (var i = 0; i <len1; i++) {
											        var strArr = jsonList1[i].log;
											        arr = strArr.split(" ");
											        if(arr[0]=="signal"){
								                    data.push({
								                    x:jsonList1[i].timestamp, 
								                    y:parseFloat(arr[3])
								                    });}
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
							name: "shingle",
							color: "#0D233A",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	   type: "POST",
										   url: "log/fingerprints",
										   async: false,
										   success: function(msg){
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
										   for (var i = 0; i <len1; i++) {
											        var strArr = jsonList1[i].log;
											        arr = strArr.split(" ");
											        if(arr[0]=="shingle"){
								                    data.push({
								                    x:jsonList1[i].timestamp, 
								                    y:parseFloat(arr[3])
								                    });}
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
							name: "shingling",
							color: "#8BBC21",
							data : (function() {
				                var data = [];
							    $.ajax({
								    	   type: "POST",
										   url: "log/fingerprints",
										   async: false,
										   success: function(msg){
										   var jsonList1 = eval('(' + msg + ')');
										   var len1 = jsonList1.length;
										   for (var i = 0; i <len1; i++) {
											        var strArr = jsonList1[i].log;
											        arr = strArr.split(" ");
											        if(arr[0]=="shingling"){
								                    data.push({
								                    x:jsonList1[i].timestamp, 
								                    y:parseFloat(arr[3])
								                    });}
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
							text : '指纹实时统计'
						},
						tooltip: {
							        shared: true,
						            formatter: function() {
                                        var s = '在'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x); 
                                        s += '<br/><span style="color:'+this.points[0].series.color+'">'+this.points[0].series.name+'指纹数为'+'： </span>'+ 
                                        '<span style="font-weight:bold">'+Highcharts.numberFormat(this.points[0].y,2)+'</span>';
                                        return s;
				            }
			            }, 
						series : seriesOptions
					});
					 //获取数据
			    function getData() {
			              var series = this.series[0];
			              setInterval(function(){
			    			 $.ajax({
								    	type: "POST",
										   url: "log/fingerprints/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (var i = 0; i <len2; i++) {
											        var strArr = jsonList2[i].log;
											        arr = strArr.split(" ");
											        if(arr[0]=="signal"){
								                    series.addPoint([jsonList2[i].timestamp, parseFloat(arr[3])], true, true);}
								             }
								            if(len2>0)maxTimestamp = jsonList2[len2-1].timestamp;
								    	}
							    	});	
			              }, 60000);
			              
			              var series1 = this.series[1];
			              setInterval(function(){
			    			 $.ajax({
								    	type: "POST",
										   url: "log/fingerprints/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (var i = 0; i <len2; i++) {
											        var strArr = jsonList2[i].log;
											        arr = strArr.split(" ");
											        if(arr[0]=="shingle"){
								                    series1.addPoint([jsonList2[i].timestamp, parseFloat(arr[3])], true, true);}
								             }
								            if(len2>0)maxTimestamp = jsonList2[len2-1].timestamp;
								    	}
							    	});	
			              }, 60000);
			              
			              var series2 = this.series[2];
			              setInterval(function(){
			    			 $.ajax({
								    	type: "POST",
										   url: "log/fingerprints/"+maxTimestamp,
										   async: false,
										   success: function(msg){
										   var jsonList2 = eval('(' + msg + ')');
										   var len2=jsonList2.length;
											 for (var i = 0; i <len2; i++) {
											        var strArr = jsonList2[i].log;
											        arr = strArr.split(" ");
											        if(arr[0]=="shingling"){
								                    series2.addPoint([jsonList2[i].timestamp, parseFloat(arr[3])], true, true);}
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
