<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>登陆界面</title>
    <head>
	    <base href="${basePath }">
		<link rel="stylesheet" type="text/css" href="${basePath }css/bbs.css">
	  </head>
  </head>
  <body>
    <c:import url="inc/head.jsp"></c:import >
	<!-- <s:actionerror/> -->
	${error}
	<form action="userAction?cmd=login" method="post">
		用户名:<input type="text" name="userName" /><br/>
		用户密:<input type="password" name="userPassword" /><br/>
		<input type="submit" value="登陆" />
	</form>
  </body>
</html>
