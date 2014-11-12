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
							<a href="#">正负面查询</a>
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
								<label class="control-label">查询日期</label>
								<div class="controls">
								  <input type="text" id="dateStr" name="dateStr"  class="input-medium datepicker" >
								</div>
							  </div>
					          <div class="control-group">
									<label class="control-label" for="posORneg">正负面</label>
									<div class="controls">
										  <select id="posORneg" name="posORneg" class="input-medium"> 
										       <option value=''>==请选择==</option>
		                              	  </select>  
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
			           //获得行业数据			          
			           $.ajax({                           
								   url: "nea/posORneg/queryposornegclassifyname?id=2",
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
								   url: "nea/posORneg/queryposornegclassifyname?id=3",
								   type: "POST",
								   async: false,
								   success: function(msg){
								   var jsonList2 = eval('(' + msg + ')');
								   var len2=jsonList2.length;
								   for (var i = 0; i <len2; i++) {						      				       
									   $("#posORneg").append("<option value='"+jsonList2[i].id+"'>"+jsonList2[i].name+"</option>");     
							       }     							            
								 }
			           });
			           $("#posORneg").chosen();
			    }			   
				
				function init(){
						$("#gridTable").jqGrid({
							url:"nea/posORneg/queryIndustry?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                autoencode:true,
			                colNames:[ '新闻ID','标题','测试值','所属行业','公司名','正负面','时间'],
			                colModel:[
			                          {name:'id',width:'40',index:'id'},                      
			                          {name:'title',width:'80',index:'title'},                       
			                          {name:'result',width:'60',index:'result'},          
			                          {name:'classify',width:'40',index:'classify'},          
			                          {name:'organization',width:'40',index:'organization'}, 
			                          {name:'posORneg',width:'20',index:'posORneg',formatter:aliasname},   
			                          {name:'timestamp',width:'60',index:'timestamp'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'timestamp',
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:30,
			                rowList:[30,50,100],
			                rownumbers: true, 
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
							case '-2': result = '疑似负面'; break;
							case '-1': result = '负面'; break;
							case '0': result = '中性'; break;
							case '1': result = '正面'; break;
							case '2': result = '疑似正面'; break;
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
			        		initForm();
			        	});
			        }
			        
			        function initForm(){
			        	var now = new Date();
			        	$("#dateStr").val(now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
			        }
			        
			        function query(){
			        	$("#gridTable").clearGridData();
			        	var data="nea/posORneg/queryIndustry?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			        }
			        function queryContentById(rowId){
						var rowObj =  $("#gridTable").getRowData(rowId);
						var filterId = rowObj.id;
						
						$( "#dialog p" ).empty();
						$.ajax({
							url:"nea/posORneg/queryContentById?id="+filterId,
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