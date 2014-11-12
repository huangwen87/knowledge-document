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
							<a href="#">行业样本添加</a>
						</li>
					</ul>
				</div>
				
				<div class="row-fluid sortable">
				   	<div class="box span9">
						<div class="box-header well">
							<h2>
								<i class="icon-list-alt"></i>行业样本</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
								<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content" style="padding:0px">
						 		<table id="gridTable"></table><div id="gridPager"></div>  
								<div class="contextMenu" id="myMenu1" style="display:none">
								    <ul style="width: 200px">
								        <li id="del">
								            <span class="ui-icon ui-icon-trash" style="float:left"></span>
								            <span style="font-size:11px;">删除</span>
								        </li>
								    </ul>
								</div>
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
								<label for="filterId" class="control-label">ID</label>
								<div class="controls">
								  <input type="text" id="filterId" name="id"  class="input-medium" title="输入查询ID，以“,”分隔" data-rel="tooltip">
								</div>
							  </div>
							  <div class="control-group">
								<label for="industry" class="control-label">行业</label>
								<div class="controls">
								  <input type="text" id="industry" name="industry"  class="input-medium" title="输入行业名称" data-rel="tooltip">
								</div>
							  </div>
							  <div class="control-group">
								<label for="title" class="control-label">标题</label>
								<div class="controls">
								  <input type="text" id="title" name="title"  class="input-medium" title="输入新闻标题" data-rel="tooltip">
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
        	<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/jquery.contextmenu.js" />"></script>
			<script type="text/javascript" src="<c:url value="resources/plugin/jqgrid/grid.locale-cn.js" />"></script>
        	<script type="text/javascript">
        	   $(function(){
						init();
						bindEvent();
				});
				
				function init(){
						$("#gridTable").jqGrid({
							url:"nic/industry/queryclassifysample?"+ $('#searchForm').serialize(),
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '样本ID','行业', '内容', '标题'],
			                colModel:[
			                          {name:'id',width:'50',index:'id'},             
			                          {name:'industry',width:'50',index:'industry'},                       
			                          {name:'news',width:'500',index:'news',formatter:cutString},
			                          {name:'title',width:'50',index:'title'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortorder:'desc',
			                viewrecords:true,
			                rowNum:15,
			                rowList:[15,50,100],
			                rownumbers: true,
			                pager:"#gridPager",
							loadComplete: function() {
								var delSettings = {
				                    afterShowForm: function ($form) {
				                        var form = $form.parent()[0], dialog;
				                        // delete button: "#dData", cancel button: "#eData"
				                        $("#dData", form).attr("tabindex", "1000");
				                        $("#eData", form).attr("tabindex", "1001");
				                        setTimeout(function () {
				                            $("#dData", form).focus();
				                        }, 50);
				                        dialog = $form.parent().parent();
				                        dialog.position({
				                            of: $(".ui-state-highlight")
				                        });
				                    },
				                    url:"nic/industry/delclassifysamplebyid",
				                    reloadAfterSubmit:true
				                  }
					                  
							    $("tr.jqgrow", this).contextMenu('myMenu1', {
							        bindings: {
							            'del': function(trigger) {
							                  $("#gridTable").delGridRow(trigger.id, delSettings);
							            }
							        },
							        onContextMenu: function(event/*, menu*/) {
							            var rowId = $(event.target).closest("tr.jqgrow").attr("id");
							            $("#gridTable"). jqGrid('setSelection',rowId);
							            return true;
							        }
							    });
							}
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
			        	$("#gridTable").clearGridData();
			        	var data="nic/industry/queryclassifysample?"+ $('#searchForm').serialize();
			        	$("#gridTable").jqGrid("setGridParam", { url : data, datatype : "json" }).trigger("reloadGrid");
			        }
				
				function cutString(cellvalue, options, rowObject){
					return cellvalue.substr(0,100);
				}
			</script>
    </body>

</html>