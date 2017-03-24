<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'top.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body style="margin:0px;width:100%;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	      <tr>
	          <td height="23" background="<%=basePath %>img2/bottom2.gif">
	              <table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
	                      <td width="181" height="23" background="<%=basePath %>img2/bottom1.gif">&nbsp;</td>
	                      <td><div align="right" class="STYLE1">今天是：
	                      <%
	                      Calendar c = Calendar.getInstance();
	                      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E");
	                      out.println(sdf.format(c.getTime()));
	                       %>
	                      </div></td>
	                       <td width="25"><img src="<%=basePath %>img2/bottom3.gif" width="25" height="23"></td>
	                   </tr>
		           </table>
		      </td>
	      </tr>
	 </table>
</body>