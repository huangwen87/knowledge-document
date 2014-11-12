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
							<a href="#">去重测试</a>
						</li>
					</ul>
				</div>
				<div class="row-fluid sortable">
					<div class="box span8">
					<div data-original-title="" class="box-header well">
						<h2><i class="icon-edit"></i> 文本</h2>
						<div class="box-icon">
							<a class="btn btn-setting btn-round" href="#"><i class="icon-cog"></i></a>
							<a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
							<a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content" >
						<form id="textarea" class="form-vertical" >
							<div class="control-group">
								<label class="control-label">对比ID</label>
								<div class="controls">
								  <input type="text" id="filterIdF" name="idF"  class="input-medium" title="输入对比ID" data-rel="tooltip">
								  <input type="text" id="filterIdL" name="idL"  class="input-medium" title="输入对比ID" data-rel="tooltip">
								</div>
							  </div>
							<label style="padding-right: 10px;">文本1
							  <textarea id="textF" name= "textF" style="width:100%;height: 120px;"></textarea>
							</label>
							<label style="padding-right: 10px;">文本2
							  <textarea id="textL" name="textL" style="width:100%;height: 120px;"></textarea>
							</label>
						</form>
					</div>
				</div>
				<div class="box span4">
					<div data-original-title="" class="box-header well">
						<h2><i class="icon-edit"></i> 操作结果</h2>
						<div class="box-icon">
							<a class="btn btn-setting btn-round" href="#"><i class="icon-cog"></i></a>
							<a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
							<a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<p class="center">
							<button class="btn btn-large btn-primary btn-round" id="start">开始测试</button>
						</p>
						<hr>
						<dl id="show"></dl>
					</div>
					<div id="loading"><img class="center" src="<c:url value="resources/img/ajax-loaders/ajax-loader-7.gif"/>"></div>
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
        					//设置弹窗效果
				        $( "#loading" ).dialog({
								autoOpen: false,
								modal:true,
								resizable: false
							});
						$(".ui-dialog-titlebar").hide();
						$("#start").bind("click",function(){
							$('#loading').dialog('open');
							$.ajax({
							url:"test/ndr-duplicate-test",
							data:$('#textarea').serialize(),
							type:"POST",
							async:false,
							success:function(msg){
								var jsondata = eval('(' + msg + ')');
								var str = '';
								if(jsondata == null||jsondata == ''){
									str = '测试失败';
								}else{
								var isdup = jsondata.isdup?"重复":"不重复";
								var myflag = "";
					        	switch(jsondata.flag2){
					        	case "true1":
					        		myflag = "simhash dup";
					        		break;
					        	case "true2":
					        		myflag = "shingle dup";
					        		break;
					        	case "true3":
					        		myflag = "simhash dup(same)";
					        		break;
					        	case "true4":
					        		myflag = "dust";
					        		break;
					        	case "true5":
					        		myflag = "shingling dup";
					        		break;
					        	default:
					        		break;
					        	}
								str =
								"<dt>去重结果</dt>"+
								"<dd  style='color:red;'>"+isdup+"</dd>"+
								"<dt>详细信息</dt>"+
								"<dd><ol>"+
								"<li>文本1的指纹:"+jsondata.signal1+"</li>"+
								//"<li>文本1的执行时间:"+jsondata.TimeDust1+"</li>"+
								"<li> 文本2的指纹:"+jsondata.signal2+"</li>"+
								//"<li>文本2执行时间:"+jsondata.TimeDust2+"</li>"+
								"<li> 指纹相似距离:"+jsondata.dis_signal+"</li>"+
								"<li> 重复类型:"+myflag+"</li>"+
								"<li>文本1中存在的垃圾内容:"+jsondata.dusts1+"</li>"+
								"<li>文本2中存在的垃圾内容:"+jsondata.dusts2+"</li>"+
								//" <li>经过第一层去重算法判断:"+(jsondata.isdup?"重复":"不重复")+"</li>"+
								//" <li>经过第二层去重算法判断:"+(jsondata.isdup?"重复":"不重复")+"</li>"+
								"</ol></dd>";
								}
								$('#loading').dialog('close');
								$( "#show" ).html(str);
							}
						});
					});
				});
        	</script>
    </body>

</html>