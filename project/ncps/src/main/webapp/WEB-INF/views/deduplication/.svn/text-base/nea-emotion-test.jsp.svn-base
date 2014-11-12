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
							<a href="#">情感分析</a>
						</li>
					</ul>
				</div>
				<div class="row-fluid sortable">
					<div class="box span8">
					<div data-original-title="" class="box-header well">
						<h2><i class="icon-edit"></i> 内容</h2>
						<div class="box-icon">
							<a class="btn btn-setting btn-round" href="#"><i class="icon-cog"></i></a>
							<a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
							<a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content" >
						<form id="textarea" class="form-vertical" >
							<label style="padding-right: 10px;">文本
							  <textarea id="textF" name= "textF" style="width:100%;height: 120px;"></textarea>
							</label>
						</form>
					</div>
				</div>
				<div class="box span4">
					<div data-original-title="" class="box-header well">
						<h2><i class="icon-edit"></i> 操作与结果</h2>
						<div class="box-icon">
							<a class="btn btn-setting btn-round" href="#"><i class="icon-cog"></i></a>
							<a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
							<a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<p class="center">
							<button class="btn btn-large btn-primary btn-round" id="start">开始分析</button>
						</p>
						<hr>
						<span id="show"></span>
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
        	<script type="text/javascript">
        		$(function(){
						$("#start").bind("click",function(){
							$.ajax({
							url:"test/nea-emotion-test",
							data:$('#textarea').serialize(),
							type:"POST",
							async:false,
							success:function(msg){
								var jsondata = eval('(' + msg + ')');
								var result = jsondata.result;
								var rs = "";
								switch(result){
									case 0: rs = "不确定"; break;
									case 1: rs = "正面"; break;
									case -1: rs = "负面"; break;
									default:break;
								}
						  		var show = "<ol><li style='color:red;''> 判断结果："+rs+"</li><li> 正面概率："
						  		+jsondata.pos+"</li><li> 负面概率："+jsondata.neg+"</li></ol>";
								$( "#show" ).html(show);
							}
						});
						});
				});
        	</script>
    </body>

</html>