<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
			<!-- load general head content -->
        	<%@include file="/WEB-INF/views/common/head.jsp" %>
        	<link rel="stylesheet" href="<c:url value="resources/plugin/jqgrid/ui.jqgrid.css"/>" />
        	<!-- reset part of style for bootstrap input and select  -->
        	<style type="text/css">
	        	input.ui-pg-input {width: auto; padding: 0px; margin: 0px; line-height: normal}
				select.ui-pg-selbox {width: auto; padding: 0px; margin: 0px; line-height: normal}
        	</style>
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
							<a href="#">新闻去重量统计</a>
						</li>
					</ul>
				</div>
				<div class="row-fluid sortable">
					<div class="box span9">
						<div class="box-header well">
							<h2>
								<i class="icon-list-alt"></i>查询结果</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
								<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="padding:0px">
						 		<table id="gridTable"></table><div id="gridPager"></div>  
						</div>
					</div>
					<div class="box span3">
					<div data-original-title="" class="box-header well">
						<h2><i class="icon-edit"></i> 查询条件</h2>
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
								<label for="id" class="control-label">新闻ID</label>
								<div class="controls">
								  <input type="text" id="id" name="id"  class="input-medium" title="输入查询ID，以“,”分隔" data-rel="tooltip">
								</div>
							  </div>
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

							  <div class="form-actions">
								<button id="query_btn" class="btn btn-primary" >查询</button>
								<button id="reset_btn" class="btn btn-info">清除</button>
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
						$("#gridTable")[0].oncontextmenu=function(){return false;}//屏蔽表格右键菜单以便自定义 
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
							url:"ndr/match/queryDup?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '新闻ID','去重量','时间'],
			                colModel:[
			                          {name:'id',width:'50',index:'id'},                      
			                          {name:'count',width:'150',index:'count'},             
			                          {name:'timestamp',width:'100',index:'timestamp'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'timestamp',
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:30,
			                rowList:[30,50,100],
			                rownumbers: true, 
			                pager:"#gridPager"
			        });
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
		             
		        function query(){
		               if(($("#dateStr_Start").val() == "" || $("#dateStr_End").val() == "")&&($("#id").val() == "")){
				            alert("开始或者结束时间没有填写，请重试!");
				            $('#searchForm')[0].reset();
				            return;
				        }
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
			        	var data="ndr/match/queryDup?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			        } 
			        
			           
		             
		             
			</script>
    </body>

</html>