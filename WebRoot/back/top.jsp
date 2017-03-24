<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="${basePath }">
</head>

<body style="margin:0px;width:100%;">
<!-- 方案1 -->
<%-- 	<table cellpadding="0" cellspacing="0" style="margin:0px;width:100%;">
		<tr style="height:11px;background:url('<%=basePath%>back_ground/img/top1.gif');">
			<td></td>
		</tr>
		<tr style="height:52px;background:url('<%=basePath%>back_ground/img/top2.gif');">
			<td>欢迎进入NJUBBS后台管理</td>
		</tr>
	</table> --%>
<!-- 方案2 -->
	<div style="height:11px;background:url('${basePath}img2/top1.gif');"></div>
	<!-- <div style="width:30000px;padding-left:30px;font-;height:52px;background:url('${basePath}img2/top2.gif');line-height:52px;display:table-cell;vertical-align:middle;"><a target="_parent" href="${basePath }/index.jsp">欢迎进入NJUBBS后台管理系统</a></div>-->
	<div style="width:30000px;padding-left:30px;font-;height:52px;background:url('${basePath}img2/top2.gif');line-height:52px;display:table-cell;vertical-align:middle;"><a target="_blank" href="#">欢迎进入NJUBBS后台管理系统</a></div>
</body>
</html>
