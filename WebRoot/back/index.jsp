<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>欢迎使用NJUBBS后台管理页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<frameset rows="63,*,23" frameborder="no"  border="0">
	<frame name="top" src="top.jsp" scrolling="no" noresize="noresize" />
	<frameset id="main" cols="173,9,*,4">
	   <frame name="left" src="left.jsp" scrolling="no" noresize="noresize"/>
	   <frame name="center" src="center.jsp" scrolling="no" noresize="noresize"/>
	   <frame name="right" src="content.jsp" scrolling="no" noresize="noresize"/>
	   <frame name="right2" src="right2.jsp" scrolling="no" noresize="noresize"/>
	</frameset>
	<frame name="bottom" src="today.jsp" scrolling="no" noresize="noresize"/>
</frameset>

<noframes>
<body>
	This is my JSP page.
	<br>
</body>
</noframes>
</html>
