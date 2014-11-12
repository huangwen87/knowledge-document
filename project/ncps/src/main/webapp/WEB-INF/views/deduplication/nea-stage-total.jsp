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
							<a href="#">数据一致性统计</a>
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
								<div id="dialog">
									<p id="pleft" style="float:left;"></p>
								</div>
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
								<label for="startID" class="control-label">开始-源自增ID</label>
								<div class="controls">
								  <input type="text" id="startID" name="startID"  class="input-medium" title="请输入开始ID" data-rel="tooltip">
								</div>
							 </div>
							 <div class="control-group">
								<label for="endID" class="control-label">结束-源自增ID</label>
								<div class="controls">
								  <input type="text" id="endID" name="endID"  class="input-medium" title="请输入结束ID" data-rel="tooltip">
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
        	<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/jquery.jqGrid.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/grid.locale-cn.js" />"></script>
        	<script type="text/javascript">
				$(function(){
						$("#gridTable")[0].oncontextmenu=function(){return false;} //屏蔽表格右键菜单以便自定义 
						init();
						bindEvent();
				});
				
				function init(){
						$("#gridTable").jqGrid({
							url:"nea/posORneg/queryVolume?startID=0&endID=1",   //初始不搜索
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                autoencode:true,
			                colNames:[ '流入量','流出量'],
			                colModel:[
			                          {name:'inflow',width:'100',index:'inflow'},                       
			                          {name:'outflow',width:'100',index:'outflow'},
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'inflow',
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:30,
			                rowList:[30,50,100],
			                rownumbers: true, 
			                pager:"#gridPager"
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
			            if($("#startID").val() == "" || $("#endID").val() == ""){
				            alert("开始或者结束ID没有填写，请重试!");
				            $('#searchForm')[0].reset();
				            return;
				        }
				        var startID = $("#startID").val();  
				        var endID = $("#endID").val();  
				        if(endID<startID){
				            alert("结束ID小于开始ID，请重试！");
				            $('#searchForm')[0].reset();
				            return;
				        }
			        	$("#gridTable").clearGridData();
			        	var data="nea/posORneg/queryVolume?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			        }
			</script>
    </body>

</html>