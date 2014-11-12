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
							<a href="#">标题过滤查询</a>
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
								<label for="id" class="control-label">新闻ID</label>
								<div class="controls">
								  <input type="text" id="id" name="id"  class="input-medium" title="输入查询ID，以“,”分隔" data-rel="tooltip">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">处理日期</label>
								<div class="controls">
								  <input type="text" id="dateStr" name="dateStr"  class="input-medium datepicker" >
								</div>
							  </div>
						      <div class="control-group">
								<label class="control-label">发布日期</label>
								<div class="controls">
								  <input type="text" id="publishdateStr" name="publishdateStr"  class="input-medium datepicker" >
								</div>
							  </div>
						      <div class="control-group">
									<label class="control-label">是否过滤</label>
									<div class="controls">
										  <label class="radio" for="isfilter-o"> 忽略 
										   <input type="radio"  id="isfilter-o" value=""  name="isfilter"  style="opacity: 0;">
										  </label>
										  <label class="radio" for="isfilter-y"> 是 
										   <input type="radio" id="isfilter-y"  value="1"  name="isfilter" style="opacity: 0;">
										  </label>
										  <label class="radio" for="isfilter-n"> 否 
										   <input type="radio"  id="isfilter-n"  value="0" name="isfilter" style="opacity: 0;">
										  </label>
									 </div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="classify">行业选择</label>
								<div class="controls">
								  <select id="classify" name="classify" class="input-medium"> 
								       <option value=''>==请选择==</option>
                              	  </select>  
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="source">来源选择</label>
								<div class="controls">
								  <select id="source" name="source" class="input-medium"> 
								       <option value=''>==请选择==</option>
                              	  </select>  
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
        	<font face="华文中宋"><script type="text/javascript">
				$(function(){
						$("#gridTable")[0].oncontextmenu=function(){return false;}//屏蔽表格右键菜单以便自定义 
						initForm();//初始化表格
						getSelectData();
						init();
						bindEvent();
				});
					
			   function getSelectData(){
			           $.ajax({                           
								   url: "nea/posORneg/queryposornegclassifyname?id=1",
								   type: "POST",
								   async: false,
								   success: function(msg){
								   var jsonList2 = eval('(' + msg + ')');
								   var len2=jsonList2.length;
								   for (var i = 0; i <len2; i++) {
									  $("#classify").append("<option value='"+jsonList2[i].name+"'>"+jsonList2[i].name+"</option>");     
							       }     							            
								 }
			           });
			            $("#classify").chosen();
			           //获得正面选项数据
			           $.ajax({                           
								   url: "nea/posORneg/queryposornegclassifyname?id=4",
								   type: "POST",
								   async: false,
								   success: function(msg){
								   var jsonList2 = eval('(' + msg + ')');
								   var len2=jsonList2.length;
								   for (var i = 0; i <len2; i++) {						      				       
									   $("#source").append("<option value='"+jsonList2[i].name+"'>"+jsonList2[i].name+"</option>");     
							       }     							            
								 }
			           });
			           $("#classify").chosen();
			           $("#source").chosen();
			    } 
			    
			    
				function init(){
						$("#gridTable").jqGrid({
							url:"nea/posORneg/querytitlefilternews?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                autoencode:true,
			                colNames:[ '新闻ID','标题','所属行业','公司名','是否过滤','来源','发布时间','处理时间'],
			                colModel:[
			                          {name:'id',width:'100',index:'id'},                      
			                          {name:'title',width:'150',index:'title'},                               
			                          {name:'classify',width:'100',index:'classify'},          
			                          {name:'organization',width:'100',index:'organization'}, 
			                          {name:'isfilter',width:'60',index:'isfilter',formatter:aliasname}, 
			                          {name:'source',width:'50',index:'source'},
			                          {name:'publishdate',width:'150',index:'publishdate'},
			                          {name:'timestamp',width:'150',index:'timestamp'}
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
						if(cellvalue == '1'){
						   result = '是';
						}
						else
						  result ='否';
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
			        		initForm();
			        	});
			        }
			        
			        function initForm(){
			        	var now = new Date();
			        	$("#dateStr").val(now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
			        }
			        
			        function query(){
			        	$("#gridTable").clearGridData();
			        	var data="nea/posORneg/querytitlefilternews?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			        }
			        function queryContentById(rowId){
						var rowObj =  $("#gridTable").getRowData(rowId);
						var filterId = rowObj.id;
						
						$( "#dialog p" ).empty();
						$.ajax({
							url:"nea/posORneg/queryFilterNewsById?id="+filterId,
							type:"GET",
							async:false,
							success:function(msg){
								$( "#dialog p:first" ).text(msg);
							}
						});
					    $( "#dialog" ).dialog("option", { title: "新闻ID:"+filterId+"对应新闻" });
						$( "#dialog" ).dialog( "open" );
					}
			</script></font>
    </body>

</html>