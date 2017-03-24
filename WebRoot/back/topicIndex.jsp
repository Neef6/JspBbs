<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath }">
    <title>主题贴管理</title>
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs2.css">
	<script type="text/javascript">
		function del(){
			if (!confirm("确认要删除？")) { 
            	window.event.returnValue = false; 
        	} 
		}
	</script>
  </head>
  <body>
  	  	<table width="80%">
  	  		<caption>主题贴管理</caption>
  	  		<tr>
  	  		    <th>序号</th>
  	  			<th>主题</th>
  	  			<th>作者</th>
  	  			<th>发贴时间</th>
  	  			<th>置顶</th>
  	  			<th>精华</th>
  	  			<th>删除</th>
  	  		</tr>
  	  		<c:forEach var="topic" items="${requestScope.list}" varStatus="vs">
  	  		<tr>
  	  		    <td>${vs.count}</td>
  	  			<td>${topic.topicTitle}</td>
  	  			<td>${topic.bbsUser.userName}</td>
  	  			<td>${topic.topicCreateTime}</td>
  	  			<td>
	  	  			<c:choose>
		  	  			<c:when test="${!topic.topicTop}">
		  	  				<a href="topicAction?cmd=upd&topicId=${topic.topicId }&topicTop=true">置顶</a>
					  	</c:when>
					  	<c:otherwise>
					  		<a href="topicAction?cmd=upd&topicId=${topic.topicId }&topicTop=false">取消</a>
					  	</c:otherwise>
					</c:choose>
  	  			</td>
  	  			<td>
		  	  		<a href="topicAction?cmd=upd&topicId=${topic.topicId }&topicEssence=${!topic.topicEssence}">${empty  topic.topicEssence||! topic.topicEssence?'精华':'取消'}</a>
  	  			</td>
  	  			<td><a href="topicAction?cmd=del&topicId=${topic.topicId }" onclick="del()">删除</a></td>
  	  		</tr>
  	  		</c:forEach>
  	  	</table>
  </body>
</html>
