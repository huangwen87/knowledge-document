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
							<a href="#">阶段性新闻统计</a>
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
							url:"ndr/match/queryVolume?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '流入量','流出量','时间段'],
			                colModel:[
			                          {name:'inflow',width:'100',index:'inflow'},             
			                          {name:'outflow',width:'100',index:'outflow'},                       
			                          {name:'timestamp',width:'150',index:'timestamp'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'timestamp',
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:15,
			                rowList:[15,50,100],
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
				        if($("#dateStr_Start").val() == "" || $("#dateStr_End").val() == ""){
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
			        	var data="ndr/match/queryVolume?"+ $('#searchForm').serialize();
			        	
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			     }
				
				
			</script>
    </body>

</html>