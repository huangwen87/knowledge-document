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
							<a href="#">代码匹配查询</a>
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
								<label for="matchId" class="control-label">新闻ID</label>
								<div class="controls">
								  <input type="text" id="matchId" name="id"  class="input-medium" title="输入查询ID，以“,”分隔" data-rel="tooltip">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">查询日期</label>
								<div class="controls">
								  <input type="text" id="dateStr" name="dateStr"  class="input-medium datepicker" >
								</div>
							  </div>
							  <div class="control-group">
								<label for="organCode" class="control-label">机构代号</label>
								<div class="controls">
								  <input type="text" id="organCode" name="organCode"  class="input-medium" title="输入机构代号" data-rel="tooltip">
								</div>
							  </div>
							  <div class="control-group">
								<label for="content" class="control-label">机构名称</label>
								<div class="controls">
								  <input type="text" id="content" name="content"  class="input-medium" title="输入机构名称" data-rel="tooltip">
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
        	<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/jquery.jqGrid.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/grid.locale-cn.js" />"></script>
        	<script type="text/javascript">
				$(function(){
						initForm();//初始化表格
						init();
						bindEvent();
				});
				
				function init(){
						$("#gridTable").jqGrid({
							url:"ndr/match/query?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '新闻ID', '机构名','机构代号','流入时间','流出时间'],
			                colModel:[
			                          {name:'matchId',index:'matchId'},             
			                          {name:'content',index:'content'},            
			                          {name:'organCode',index:'organCode'},            
			                          {name:'timeIn',index:'timeIn'},
			                          {name:'timeOut',index:'timeOut'}
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
			        		initForm();
			        	});
			        }
			        
			        function initForm(){
			        	var now = new Date();
			        	$("#dateStr").val(now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
			        }
			        
			        function query(){
			        	$("#gridTable").clearGridData();
			        	var data="ndr/match/query?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			        }
			        
					$("input").keypress(function(event) {
					    if (event.which == 13) {
					        event.preventDefault();
					        query();
					    }
					});
			</script>
    </body>

</html>