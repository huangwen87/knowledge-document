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
        <link rel="stylesheet" href="<c:url value="resources/plugin/jqgrid/ui.jqgrid.css"/>" />
        <style type="text/css">
	        	input.ui-pg-input {width: auto; padding: 0px; margin: 0px; line-height: normal}
				select.ui-pg-selbox {width: auto; padding: 0px; margin: 0px; line-height: normal}
        	</style>
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
							<a href="#">信息源统计</a>
						</li>
					</ul>
				</div>
				
				<div class="row-fluid sortable">
				   	<div class="box span9" id="showId">
						<div class="box-header well">
							<h2>
								<i class="icon-list-alt"></i>记录统计</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
								<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="padding:0px">
								<div id="dialog">
									<p id="pleft" style="float:left;"></p>
									<p id="pright" style="color:green;float:right;border-left:1px solid black; padding-left:2px;"></p>
								</div>
								<div id="table">
								    <table id="gridTable"></table><div id="gridPager"></div> 
								</div>
							 	
							 	<table id="gridTable2"></table><div id="gridPager2"></div> 
						</div>
					</div>
					
					<div class="box span3" id="searchDiv">
						<div data-original-title="" class="box-header well">
							<h2><i class="icon-edit"></i>查询时间</h2>
							<div class="box-icon">
								<a class="btn btn-setting btn-round" href="#"><i class="icon-cog"></i></a>
								<a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
								<a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content">
							<form class="form-vertical" id="searchForm" >
								<fieldset>	
								  <div class="control-group">
										<label class="control-label" for="dateStr_Start"> 开始时间</label>
										<div class="controls">
										  <input type="text" id="dateStr_Start" name="dateStr_Start"  class="ui_timepicker" >
										</div>
								  </div>
								  <div class="control-group">
										<label class="control-label" for="dateStr_End">结束时间</label>
										<div class="controls">
										  <input type="text" id="dateStr_End" name="dateStr_End"  class="ui_timepicker" >
										</div>
								  </div>  							  
								  <div class="control-group">
									   <label for="title" class="control-label">GroupName</label>
									   <div class="controls">
									      <input type="text" id="groupName" name="groupName"  class="input-medium" title="输入查 GroupName" data-rel="tooltip">
									   </div>
							      </div>
							      
								  <div class="control-group">
									   <label for="title" class="control-label">SiteName</label>
									   <div class="controls">
									      <input type="text" id="siteName" name="siteName"  class="input-medium" title="输入查 SiteName" data-rel="tooltip">
									   </div>
							      </div>
							      
							      <div class="control-group">
									   <label for="title" class="control-label">channel</label>
									   <div class="controls">
									      <input type="text" id="channel" name="channel"  class="input-medium" title="输入查 channel" data-rel="tooltip">
									   </div>
							      </div>
							      
							      <div class="control-group">
									   <label for="title" class="control-label">trssource</label>
									   <div class="controls">
									      <input type="text" id="trssource" name="trssource"  class="input-medium" title="输入查 trssource" data-rel="tooltip">
									   </div>
							      </div>
							      
								  <div class="form-actions">
									<button id="query_btn" class="btn btn-large btn-primary btn-round" >查询</button>
									<button id="reset_btn" class="btn btn-large btn-info btn-round">清除</button>
									<button id="update_btn" class="btn btn-large btn-danger btn-round">刷新</button>
									<hr>
									<dl id="show"></dl>
								  </div>
								</fieldset>
							  </form>
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
            <link rel="stylesheet" href="<c:url value="resources/plugin/timepicker/jquery-ui-timepicker-addon.css"/>" />   
		    <script type="text/javascript" src="<c:url value="resources/plugin/timepicker/jquery-ui-timepicker-addon.js" />"></script>
		    <script type="text/javascript" src="<c:url value="resources/plugin/timepicker/jquery-ui-timepicker-zh-CN.js" />"></script>
        	<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/jquery.jqGrid.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/grid.locale-cn.js" />"></script>

        	<script type="text/javascript">
        	   $(function(){
        	            initPicker();
						init();
						bindEvent();
				});
				
				function initPicker(){
			        $(".ui_timepicker").datetimepicker({
			            showSecond: true,
			            timeFormat: 'hh:mm:ss',
			            stepHour: 1,
			            stepMinute: 1,
			            stepSecond: 1
			        });
			        var now = new Date();
			        $("#dateStr_Start").val(now.getFullYear()+"-"+(now.getMonth()+1)+"-"+
			            now.getDate()+" "+"00:00:00");
			        $("#dateStr_End").val(now.getFullYear()+"-"+(now.getMonth()+1)+"-"+
			            now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds());
                }
				
				function init(){
						$("#gridTable").jqGrid({
							url:"ndr/match/queryInfoSourceTotal?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ 'GroupName','SiteName','channel','trssource','去重量','保留量','去重比例','垃圾数量','纯垃圾数量','纯垃圾比列','正文空量','正文空量比例'],
			                colModel:[
			                          {name:'groupName',width:'80',index:'groupName'},
			                          {name:'siteName',width:'80',index:'siteName'},
			                          {name:'channel',width:'80',index:'channel'},
			                          {name:'trssource',width:'60',index:'trssource'},
			                          {name:'dupCount',width:'60',index:'dupCount'},
			                          {name:'resCount',width:'60',index:'resCount'},
			                          
			                          {name:'dupRatio',width:'60',index:'dupRatio'},
			                          {name:'allDustCount',width:'60',index:'allDustCount'},
			                          {name:'pureDustCount',width:'70',index:'pureDustCount'},
			                          {name:'pureRatio',width:'70',index:'pureRatio'},
			                          {name:'ctxnCount',width:'60',index:'ctxnCount'},
			                          {name:'ctxnRatio',width:'80',index:'ctxnRatio'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'trssource',
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:30,
			                rowList:[30,50,100],
			                rownumbers: true,
			                shrinkToFit:false,
			                autoScroll: true,
			                pager:"#gridPager",
			                ondblClickRow:function(rowid) {
			                	queryDetailByKey(rowid);
			                 }
			       });  
				}
				
	
				
			    function aliasname(cellvalue, options, rowObject){
						var result = cellvalue;
						switch(cellvalue){
							case 1: result = '是'; break;
							case 0: result = '否'; break;
							default: break;
						}
						return result;
			    }

				function bindEvent(){
					$("#query_btn").bind("click",function(e){
			        		e.preventDefault();
			        		query();
			        	});
			        $("#reset_btn").bind("click",function(e){
			        		e.preventDefault();
			        		$('#searchForm')[0].reset();
			        	});
			        $("#update_btn").bind("click",function(e){
			        		e.preventDefault();
			        		update();
			        });
			        	
				}
				
			    function queryDetailByKey(rowid){
			            var rowObj =  $("#gridTable").getRowData(rowid);
			            var div = document.getElementById("table");
			            div.parentNode.removeChild(div);
			            var startTime   = $("#dateStr_Start").val();
			            var endTime     = $("#dateStr_End").val();
			            var searchDiv = document.getElementById("searchDiv");
			            searchDiv.parentNode.removeChild(searchDiv);
			            $("#showId").attr("class", "box span12");			            
			            $("#gridTable2").jqGrid({
							url:"ndr/match/queryInfoSource?groupName="+rowObj.groupName+
							      "&siteName="+rowObj.siteName+"&channel="+rowObj.channel+
							      "&trssource="+rowObj.trssource+"&dateStr_Start="+startTime+
							      "&dateStr_End="+endTime,
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '新闻ID', '原始ID','标题','重复?','含垃圾?','要去重?','定点抓取?','GroupName','SiteName','channel','流入时间','流出时间'],
			                colModel:[
			                          {name:'filterId',width:'80',index:'filterId'},             
			                          {name:'originId',width:'80',index:'originId'},            
			                          {name:'title',width:'150',index:'title'},            
			                          {name:'isdup',width:'45',index:'isdup',formatter:aliasname},             
			                          {name:'istrash',width:'45',index:'istrash',formatter:aliasname},            
			                          {name:'iscd',width:'50',index:'iscd',formatter:aliasname}, 
			                          {name:'webgrab',width:'50',index:'webgrab'},  
			                          {name:'groupName',width:'60',index:'groupName'},
			                          {name:'siteName',width:'60',index:'siteName'},
			                          {name:'channel',width:'60',index:'channel'},
			                          {name:'timeIn',width:'120',index:'timeIn'},
			                          {name:'timeOut',width:'120',index:'timeOut'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'timestamp',
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:30,
			                rowList:[30,50,100],
			                rownumbers: true,
			                shrinkToFit:false,
			                autoScroll: true,
			                pager:"#gridPager2",
			                ondblClickRow:function (rowid) {
			                	queryContentById(rowid);
			                 }
			    });
			    //设置弹窗效果
			        $( "#dialog" ).dialog({
							autoOpen: false,
							show: {effect: "blind",duration: 300},
							hide: {effect: "blind",duration: 300},
							modal:true,
							width: 720,
							height:480
						});
			    }

				
				function query(){
				        var startTime=$("#dateStr_Start").val();  
				        var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
				        var endTime=$("#dateStr_End").val();  
				        var end=new Date(endTime.replace("-", "/").replace("-", "/")); 
				        if(end<start){
				            alert("结束时间小于开始时间，请重试！");
				            $('#searchForm')[0].reset();
				            return;
				        }
				        if((endTime=="" && startTime!="") || (startTime=="" && endTime!="")){
				            alert("开始或者结束时间为空，请输入！");
				            return;
				        }
			        	$("#gridTable").clearGridData();
			        	var data="ndr/match/queryInfoSourceTotal?"+ $('#searchForm').serialize() + "&param=query";
			        	
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			     }
			     
			     
			    function update(){
			         $.ajax({
						     url:"ndr/match/update",
							 type:"GET",
							 async:false,
							 success:function(msg){
							    $("#gridTable").clearGridData();
			        	        var data="ndr/match/queryInfoSourceTotal?"+ $('#searchForm').serialize() + "&param=query";
 			        	        $("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
								alert("更新  "+msg+" 完成");
							 }
					 });    
			                   
			    }
			     
			     
			     
			     
			     function queryContentById(rowId){
						var rowObj =  $("#gridTable2").getRowData(rowId);
						var filterId = rowObj.filterId;
						var originId = rowObj.originId;
						var isdup = rowObj.isdup;
						
						$( "#dialog p" ).empty();
						$.ajax({
							url:"ndr/filter/queryContentById?id="+filterId,
							type:"GET",
							async:false,
							success:function(msg){
								$( "#dialog p:first" ).text(msg);
							}
						});
						if(isdup == '是'){
							$.ajax({
								url:"ndr/filter/queryContentById?id="+originId,
								type:"GET",
								async:false,
								success:function(msg){
									$( "#dialog p:last" ).text(msg);
								}
							});
							$( "#dialog" ).dialog("option", { title: "新闻ID:"+filterId+" 原始ID:"+originId+"对应新闻" });
						}else{
							$( "#dialog" ).dialog("option", { title: "新闻ID:"+filterId+"对应的新闻内容" });
						}
						$( "#dialog" ).dialog( "open" );
						
						if(isdup == '是'){
							var mywidth = ($( "#dialog p" ).parent().width()-20)/2;
							$( "#dialog p" ).css("width",mywidth);
						}
						if(isdup == '否'){
							$( "#dialog p:first" ).css("width","auto");
						}
					}
			     
			     
			     
			</script>
    </body>

</html>