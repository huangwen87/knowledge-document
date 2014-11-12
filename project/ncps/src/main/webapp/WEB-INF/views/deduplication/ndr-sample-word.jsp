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
							<a href="#">词库添加</a>
						</li>
					</ul>
				</div>
				<div class="row-fluid sortable">
				
				   	<div class="box span9">
						<div class="box-header well">
							<h2>
								<i class="icon-list-alt"></i>词库记录</h2>
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
						<h2><i class="icon-edit"></i>添加词库</h2>
						<div class="box-icon">
							<a class="btn btn-setting btn-round" href="#"><i class="icon-cog"></i></a>
							<a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
							<a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content" >
						<form id="textarea" class="form-vertical" >
							<fieldset>
							  <div class="control-group">
								<label class="control-label">词样本</label>
								<div class="controls">
								  <textarea id="word" name="word" style="width: 96%; height: 200px;"></textarea>
								</div>
							  </div>
							  <div class="form-actions">
								<button id="start_btn" class="btn btn-large btn-primary btn-round" >添加</button>
								<button id="clear_btn" class="btn btn-large btn-info btn-round">清空</button>
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
						bindEvent();
						init();
				});
				
	            function init(){
						$("#gridTable").jqGrid({
							url:"ndr/sample/queryword",
							datatype : "json",
			                height: 'auto',
			                autowidth: true,
			                colNames:[ '词ID','词','时间'],
			                colModel:[
			                          {name:'wordId',index:'wordId'},             
			                          {name:'word',index:'word'},                       
			                          {name:'timestamp',index:'timestamp'}
			                ],
			                jsonReader : {root : "dataRows",repeatitems : false},
			                sortname:'timestamp',
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
				                    url:"ndr/sample/delwordlibbyid",
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
					    $("#start_btn").bind("click",function(e){
			        		e.preventDefault();
			        		add();
			        	});
			        	$("#clear_btn").bind("click",function(e){
			        		e.preventDefault();
			        		$('#textarea')[0].reset();
			        	});
				}
				
        	
        		function add(){
							$.ajax({
							url:"ndr/sample/addWord",
							data:$('#textarea').serialize(),
							type:"POST",
							async:false,
							success:function(msg){
								var jsondata = eval('(' + msg + ')');
								alert("操作结果：" +jsondata);
								
							}
						});
						$("#gridTable").jqGrid().trigger("reloadGrid");
				};
        	</script>
    </body>

</html>