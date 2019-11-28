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

<title>单点认证系统用户列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- Bootstrap -->
<link href="bootstrap/css/main.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="js/ajax.js"></script>

</head>

<body class="login-body">

	<div class="panel login-panel">
		<form id="form1" role="form" method="post" action="ssoregist">
			<h2 class="text-center">所有的注册人员</h2>
			<c:if test="${not empty ERROR_MSG}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="close">
						<span aria-hidden="true">&times;</span>
					</button>
					${ERROR_MSG}
				</div>
			</c:if>
			<div class="form-group login-text" style="text-align: left;">
				<table class="table table-striped">
					<tr><th>编号</th><th>头像</th><th>姓名</th></tr>
					<c:forEach items="${list }" var="user">
						<tr><td>${user.userid }</td><td><img class="img-rounded" alt="暂无头像" width="100px" src="upload/${user.userheadimage }"></td><td>${user.username }</td></tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="form-group login-text" style="text-align:center;">
				<h3 onclick="javascript:history.go(-1);">返回</h3>
			</div>
		</form>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="bootstrap/js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>
