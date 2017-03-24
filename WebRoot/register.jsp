<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}">
    
    <title>用户注册界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs.css">
	<script type="text/javascript">
		function validate(){
			var userName = document.getElementsByName("userName");
			var userPassword = document.getElementsByName("userPassword");
			var confirmPassword = document.getElementsByName("confirmPassword");
			var userBirth = document.getElementsByName("userBirth");
			var userEmail = document.getElementsByName("userEmail");
			//正则表达式
			var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/; 
			
			if(userName[0].value == ""){
				alert("用户名不能为空！");
				userName[0].focus();
			}else if(userPassword[0].value != confirmPassword[0].value){
				alert("两次输入的密码不一致！");
				userPassword[0].focus();
			}else if(userBirth[0].value == ""){
				alert("生日不能为空！");
				userBirth[0].focus();
			}else if(!emailReg.test(userEmail[0].value)){
				alert("邮箱格式不正确！");
				userEmail[0].value="";
				userEmail[0].focus();
			}else{
				document.getElementsByName("regForm")[0].submit();
			}
		}
	</script>

  </head>
  
  <body>
    <c:import url="inc/head.jsp"></c:import>
    ${error }
    <form name="regForm" action="userAction?cmd=add" method="post">
    	<table>
    		<tr>
    			<td>用户名：</td>
    			<td><input type="text" name="userName"/></td>
    			<td>*用户名输入1-10个字符</td>
    		</tr>
    		<tr>
    			<td>用户密码：</td>
    			<td><input type="password" name="userPassword"/></td>
    			<td>*用户密码输入1-10个字符</td>
    		</tr>
    		<tr>
    			<td>确认密码：</td>
    			<td><input type="password" name="confirmPassword"/></td>
    			<td>*用户密码输入1-10个字符</td>
    		</tr>
    		<tr>
    			<td>年龄：</td>
    			<td><input type="text" name="userAge"/></td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>出生：</td>
    			<td><input type="text" name="userBirth" value="1998-8-8"/></td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>兴趣：</td>
    			<td>
    				<c:forEach items="${hobbies}" var="hobby" varStatus="status">
    					<input type="checkbox" name="hobbies" value="${hobby }"/>${hobby}
    				</c:forEach>
    			</td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>头像：</td>
    			<td width="50%">
    				<c:forEach items="${heads}" var="head" varStatus="status">
    					<input type="radio" name="userImgFace" value="${head }"/>
    					<img width="48px" src="${basePath }headImg/${head}"/>
    				</c:forEach>
    			</td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>email:</td>
    			<td><input name="userEmail"/></td>
    			<td></td>
    		</tr>
    		<tr>
    			<td colspan="3">
    				<input type="button" onClick="validate()" value="注册"/>
    				<input type="reset" value="重置"/></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
