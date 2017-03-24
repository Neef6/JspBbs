<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="${basePath }">
    <title>主帖页</title>
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs.css">
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs2.css">
	<script type="text/javascript">
		function validate(){
			var topicTitle = document.getElementsByName("topicTitle");
			var topicComment = document.getElementsByName("topicComment");
			
			if(topicTitle[0].value == ""){
				alert("主题不能为空！");
				topicTitle[0].focus();
			}else if(topicComment[0].value == ""){
				alert("内容不能为空！");
				topicComment[0].focus();
			}else{
				document.getElementsByName("topicAdd")[0].submit();
			}
		}
	</script>
  </head>
  <body>
    <c:import url="inc/head.jsp"></c:import>
    <div>
    	<form name="topicAdd" action="topicAction?cmd=add" method="Post" style="text-align: center;">
    		<input type="hidden" value="${param.id }" name="id"/>
    		<table style="margin:0 auto;">
    			<caption>发贴</caption>
    			<tr><td>主题：</td><td><input type="text"  name="topicTitle" size="39" maxlength="100"/></td><td>*请输入1-100个字符</td></tr>
    			<tr><td>内容：</td><td><textarea name="topicComment" cols="30" rows='5'  maxlength="50"></textarea></td><td>*请输入5000个字符</td></tr>
    			<tr><td colspan='3'><input type="button" onClick="validate()" value="提交"/><input type="reset" value="重置"/></td></tr>
    		</table>
    	</form>
    </div>
    <jsp:include page="inc/copyright.jsp"></jsp:include>
  </body>
</html>
