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
				   	<div class="box span9">
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
						 		<table id="gridTable"></table><div id="gridPager"></div> 
						</div>
					</div>
					
					<div class="box span3">
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
							      
								  <div class="form-actions">
									<button id="query_btn" class="btn btn-large btn-primary btn-round" >查询</button>
									<button id="reset_btn" class="btn btn-large btn-info btn-round">清除</button>
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
							url:"ndr/match/queryInfoSource?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '新闻ID', '原始ID','标题','重复?','含垃圾?','要去重?','定点抓取?','GroupName','SiteName','channel','流入时间','流出时间'],
			                colModel:[
			                          {name:'filterId',width:'80',index:'filterId'},             
			                          {name:'originId',width:'80',index:'originId'},            
			                          {name:'title',width:'150',index:'title'},            
			                          {name:'isdup',width:'30',index:'isdup',formatter:aliasname},             
			                          {name:'istrash',width:'30',index:'istrash',formatter:aliasname},            
			                          {name:'iscd',width:'30',index:'iscd',formatter:aliasname}, 
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
			                pager:"#gridPager",
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
				}
				
			    function queryContentById(rowId){
						var rowObj =  $("#gridTable").getRowData(rowId);
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
			        	$("#gridTable").clearGridData();
			        	var data="ndr/match/queryInfoSource?"+ $('#searchForm').serialize();
			        	
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			     }
				
				
			</script>
    </body>

</html>