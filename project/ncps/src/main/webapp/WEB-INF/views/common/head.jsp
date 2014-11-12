<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta name="description" content="大智慧,舆情后台,dzh,greate wisdom,social media monitor">
<meta name="author" content="Johnmy Jode,Muhammad Usman">
<title>新闻舆情后台系统</title>
<!-- The styles -->
<link id="bs-css" href="<c:url value="resources/css/bootstrap-cerulean.css"/>" rel="stylesheet">
<link href="<c:url value="resources/css/public.css"/>" rel="stylesheet">
<link href="<c:url value="resources/css/bootstrap-responsive.css"/>" rel="stylesheet">
<link href="<c:url value="resources/css/charisma-app.css"/>" rel="stylesheet">
<link href="<c:url value="resources/css/jquery-ui-1.8.21.custom.css"/>" rel="stylesheet">
<link href="<c:url value="resources/css/fullcalendar.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/fullcalendar.print.css"/>" rel='stylesheet' media='print'>
<link href="<c:url value="resources/css/chosen.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/uniform.default.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/colorbox.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/jquery.cleditor.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/jquery.noty.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/noty_theme_default.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/elfinder.min.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/elfinder.theme.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/jquery.iphone.toggle.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/opa-icons.css"/>" rel='stylesheet'>
<link href="<c:url value="resources/css/uploadify.css"/>" rel='stylesheet'>
<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!-- The fav icon -->
<link rel="shortcut icon" href="<c:url value="resources/img/favicon.ico"/>">