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
							<a href="#">实时日志</a>
						</li>
					</ul>
				</div>
				<div class="row-fluid sortable">		
					<div class="box span12">
						<div class="box-header well">
							<h2>
								<i class="icon-list-alt"></i>实时日志</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
								<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
							</div>
						</div>
						<div class="box-content">
						<div class="row-fluid">
						<div class="span4">
							<a id="receivebtn" href="#" class="btn btn-success"><i class="icon-play icon-white"></i>开始接收</a>
							<a id="stopbtn" href="#" class="btn btn-info"><i class="icon-pause icon-white"></i>暂停接收</a>
							<a id="cleanbtn" href="#" class="btn btn-danger"><i class="icon-trash icon-white"></i>清空日志</a>
						</div>
						<div class="span2">
						 <select id="select">
						 	  <option value="ALL" >日志类型</option>
						 	  <option value="ALL" >ALL</option>
						 	  <option value="INFO">INFO</option>
						 	  <option value="DEBUG">DEBUG</option>
						 	  <option value="ERROR">ERROR</option>
						 </select>
						 </div>
						 </div>
					 	<table class="table table-bordered table-striped table-condensed"><thead></thead><tbody></tbody></table>  
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
        	<script type="text/javascript" src="<c:url value="resources/plugin/amq/amq_jquery_adapter.js" />"></script>
			<script type="text/javascript" src="<c:url value="resources/plugin/amq/amq.js" />"></script>
			<script type="text/javascript">
				//行号ID，避免样式应用出错
				var rowid = 1;
				//最大行号
				var maxRowNum = 100;
				var myDestination = "topic://myTopic.develop";
				var amq = org.activemq.Amq;
				$(function(){
						init();
						bindEvent();
				});
				function init(){
						amq.init({
							uri : 'amq',
							logging : true,
							timeout : 20,
							clientId : (new Date()).getTime().toString()
						});
					}
					
			        function bindEvent(){
			        	$("#sendbtn").bind("click",function(e){
			        		e.preventDefault();
			        		send();
			        	});
			        	$("#receivebtn").bind("click",function(e){
			        		e.preventDefault();
			        		 receive();
			        	});
			        	$("#cleanbtn").bind("click",function(e){
			        		e.preventDefault();
			        		clean();
			        	});
			        	$("#stopbtn").bind("click",function(e){
			        		e.preventDefault();
			        		stop();
			        	});
			        	$("#select").bind("change",function(){
			        		clean();
			        	});
			        	$("#jframe").bind("change",function(){
			        		clean();
			        	});
			        }
			        
				function send(){
				 	amq.sendMessage(myDestination,$("#send").val());
				}
				
				function receive() {
					var myHandler = {
						rcvMessage : function(message) {
							var testJson = eval('(' + message.textContent + ')');
							var select = $("#select").val();
							var value = testJson.text;
							var rank = testJson.text.rank;
							var th="<tr><th>Logs</th><th>Class</th><th>JFrame</th><th>Rank</th><th>TimeStamp</th></tr>";
							var tr="<tr id='table-"+rowid+"'>"+
											"<td>"+value.log+"</td>"+                                   
											"<td>"+value.className+"</td>"+                                   
											"<td>"+value.jframe+"</td>";				
							if ("ALL" == select || select  == rank ) {
									if(rowid==1){
										$("thead").append(th);
									}
									if(rowid > maxRowNum){
										$("#table-"+(rowid-maxRowNum)).remove();
									}
									 if("ERROR" == rank){
											tr+="<td><span class='label label-important'>"+value.rank+"</span></td>";
									}else if("DEBUG" == rank){
											tr+="<td><span class='label'>"+value.rank+"</span></td>";                                 
									}else{
											tr+="<td><span class='label label-success'>"+value.rank+"</span></td>";                                 
									}
									tr+="<td>"+new Date().toLocaleString()+"</td></tr>";
									$("tbody").prepend(tr);
									rowid++;
							} 
						}
					};
					amq.addListener("mytest", myDestination, myHandler.rcvMessage);
				}
			
				function stop() {
					amq.removeListener("mytest", myDestination);
				}
			
				function clean() {
					$("thead").empty();
					$("tbody").empty();
					rowid = 1;
				}
				
			</script>
    </body>

</html>