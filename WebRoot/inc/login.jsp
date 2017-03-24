<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>
    <base href="${basePath }">
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs.css">
  </head>
  
  <body>
  	  <c:choose>
	  	<c:when test="${sessionScope.user==null}">
	  		${error}
			<form action="userAction?cmd=login" method="post">
				用户名:<input type="text" name="userName" /><br/>
				用户密:<input type="password" name="userPassword" /><br/>
				<input type="submit" value="登陆" />
			</form>
	  	</c:when>
	  	<c:otherwise>
	  		${sessionScope.user.userName},欢迎您!
	  	</c:otherwise>
	  </c:choose>
</body>
</html>