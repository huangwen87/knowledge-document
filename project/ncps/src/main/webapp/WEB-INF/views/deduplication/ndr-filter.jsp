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
							<a href="#">过滤结果查询</a>
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
									<p id="pright" style="color:green;float:right;border-left:1px solid black; padding-left:2px;"></p>
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
								<label for="filterId" class="control-label">新闻ID</label>
								<div class="controls">
								  <input type="text" id="filterId" name="id"  class="input-medium" title="输入查询ID，以“,”分隔" data-rel="tooltip">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">查询日期</label>
								<div class="controls">
								  <input type="text" id="dateStr" name="dateStr"  class="input-medium datepicker" >
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">是否有重复</label>
								<div class="controls">
								  <label></label>
								  <label class="radio" for="isdup-o"> 忽略 
								  <input type="radio"  id="isdup-o" value=""  name="isdup"  style="opacity: 0;">
								  </label>
								  <label class="radio" for="isdup-y"> 是 
								  <input type="radio" id="isdup-y"  value="1"  name="isdup" style="opacity: 0;">
								  </label>
								  <label class="radio" for="isdup-n"> 否 
								   <input type="radio"  id="isdup-n"  value="0" name="isdup" style="opacity: 0;">
								   </label>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">是否存在垃圾</label>
								<div class="controls">
								  <label></label>
								  <label class="radio" for="isdust-o"> 忽略 
								  <input type="radio"  id="isdust-o" value=""  name="isdust"  style="opacity: 0;">
								  </label>
								  <label class="radio" for="isdust-y"> 是 
								  <input type="radio" id="isdust-y"  value="1"  name="isdust" style="opacity: 0;">
								  </label>
								  <label class="radio" for="isdust-n"> 否 
								   <input type="radio"  id="isdust-n"  value="0" name="isdust" style="opacity: 0;">
								   </label>
								</div>
							  </div>
							  <div class="control-group">
								<label for="title" class="control-label">新闻标题</label>
								<div class="controls">
								  <input type="text" id="title" name="title"  class="input-medium" title="输入查新闻标题" data-rel="tooltip">
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
						$("#gridTable")[0].oncontextmenu=function(){return false;}//屏蔽表格右键菜单以便自定义 
						initForm();//初始化表格
						init();
						bindEvent();
				});
				
				function init(){
						$("#gridTable").jqGrid({
							url:"ndr/filter/query?"+ $('#searchForm').serialize(),
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
			                 },
			                onRightClickRow:function (rowid,iRow,iCol,e) {
			                	queryDustById(rowid);
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
			        	$("#searchForm input[type=radio]").parent("span.checked").removeClass("checked");
			        	var now = new Date();
			        	$("#dateStr").val(now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
		        		$("#isdust-o").parent().addClass("checked");
		        		$("#isdust-o").attr("checked","checked");
		        		$("#isdup-o").parent().addClass("checked");
		        		$("#isdup-o").attr("checked","checked");
			        }
			        
			        function query(){
			        	$("#gridTable").clearGridData();
			        	var data="ndr/filter/query?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
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
					
					function aliasname(cellvalue, options, rowObject){
						var result = cellvalue;
						switch(cellvalue){
							case 1: result = '是'; break;
							case 0: result = '否'; break;
							default: break;
						}
						return result;
					}
					
					function queryDustById(rowId){
						var rowObj =  $("#gridTable").getRowData(rowId);
						var filterId = rowObj.filterId;
						var istrash = rowObj.istrash;
						
						if(istrash=='否') return false;//不含垃圾则返回
						
						$( "#dialog p" ).empty();//重置内容
						$.ajax({
							url:"ndr/filter/queryDustById?id="+filterId,
							type:"GET",
							async:false,
							success:function(msg){
								$( "#dialog p:first" ).text(msg);
							}
						});
						
						$( "#dialog" ).dialog("option", { title: "新闻ID:"+filterId+"对应的垃圾内容" });//弹窗标题
						$( "#dialog" ).dialog( "open" );//弹窗显示
						$( "#dialog p:first" ).css("width","auto");//p自动填充
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