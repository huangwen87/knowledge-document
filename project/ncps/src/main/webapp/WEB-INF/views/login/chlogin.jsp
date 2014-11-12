<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta name="description"
	content="fully featured, responsive, HTML5, Bootstrap">
<meta name="author" content="Muhammad Usman,johnmy">
<title>新闻舆情后台系统</title>
<!-- The styles -->
<link id="bs-css"
	href="<c:url value="resources/css/bootstrap-cerulean.css"/>"
	rel="stylesheet">
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="<c:url value="resources/css/bootstrap-responsive.css"/>"
	rel="stylesheet">
<link href="<c:url value="resources/css/charisma-app.css"/>"
	rel="stylesheet">
<link href="<c:url value="resources/css/jquery-ui-1.8.21.custom.css"/>"
	rel="stylesheet">
<link href="<c:url value="resources/css/uniform.default.css"/>"
	rel='stylesheet'>
<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<!-- The fav icon -->
<link rel="shortcut icon" href="<c:url value="resources/img/favicon.ico"/>">
<!-- jQuery -->
<script type="text/javascript"
	src="<c:url value="resources/plugin/jquery/jquery-1.9.1.min.js" />"></script>
<!-- checkbox, radio, and file input styler -->
<script src="<c:url value="resources/js/jquery.uniform.min.js"/>"></script>
<!-- library for advanced tooltip -->
<script src="<c:url value="resources/js/bootstrap-tooltip.js"/>"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("input:checkbox, input:radio, input:file").not(
						'[data-no-uniform="true"],#uniform-is-ajax').uniform();
				$('[rel="tooltip"],[data-rel="tooltip"]').tooltip({
					"placement" : "bottom",
					delay : {
						show : 400,
						hide : 200
					}
				});
			});
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>新闻舆情后台系统</h2>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info">请输入您的用户名和密码</div>
					<form class="form-horizontal" action="main/ndr-monitor" method="post">
						<fieldset>
							<div class="input-prepend" title="用户名" data-rel="tooltip">
								<span class="add-on"> <i class="icon-user"></i> </span> <input
									autofocus class="input-large span10" name="username"
									id="username" type="text" value="admin" />
							</div>
							<div class="clearfix"></div>
							<div class="input-prepend" title="密码" data-rel="tooltip">
								<span class="add-on"> <i class="icon-lock"></i> </span> <input
									class="input-large span10" name="password" id="password"
									type="password" value="admin123456" />
							</div>
							<div class="clearfix"></div>
							<div class="input-prepend">
								<label class="remember" for="remember"> <input
									type="checkbox" id="remember" />记住我</label>
							</div>
							<div class="clearfix"></div>
							<p class="center span5">
								<button type="submit" class="btn btn-primary">登录</button>
							</p>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>