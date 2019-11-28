<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>单点认证系统注册成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">

<c:forEach items="${clist}" var="url">
<script type="text/javascript" src="${url}/sso/createssosession?key=${key}"></script>
</c:forEach>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/main.css" rel="stylesheet">



</head>

<body class="login-body">

	<div class="panel login-panel">
		<h1>
		恭喜，注册成功！
		</h1><br>
		<a href="ssologin?from=url">如果浏览器没有自动跳转，点击这里返回上一页面</a>
		<script type="text/javascript" > window.setTimeout("this.location.href= 'ssologin?from=url'",5000) </script>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="bootstrap/js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>
