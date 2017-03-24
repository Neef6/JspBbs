<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath }">
    <title>版块管理</title>
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs2.css">
	<script type="text/javascript">
		function del(){
			if (!confirm("确认要删除？")) { 
            	window.event.returnValue = false; 
        	} 
		}
	</script>
  </head>
  <body style="width:100%;height:100%;margin:0px;">
 	 <div style="float:left;width:99%;">
	     <a href="${basePath }back/channelAdd.jsp">添加版块</a>
	     <Table style="border:1px solid;border-color:blue;border-collapse:collapse;width:100%;">
	     	<caption>版块管理</caption>
	     	<tr>
	     		<th>序号</th>
	     		<th>版块</th>
	     		<th>描述</th>
	     		<th>修改</th>
	     		<th>删除</th>
	     	</tr>
	     	<c:forEach items="${requestScope.list}" var="channel" varStatus="vs">
	     	<tr>
	     		<td>${vs.count}</td>
	     		<td>${channel.channelName}</td>
	     		<td>${channel.channelDes}</td>
	     		<td>
	     			<a href="channelAction?cmd=toUpd&id=${channel.channelId }">修改</a>
	     		</td>
	     		<td>
	     			<a href="channelAction?cmd=del&id=${channel.channelId}" onclick="del()">删除</a>
	     		</td>
	     	</tr>
	     	</c:forEach>
	     </Table>
     </div>
  </body>
</html>
