<%@ page language="java" import="java.util.*,cn.edu.nju.model.BbsUser" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'reg.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function validate(){
			var userName = document.getElementsByName("userName");
			var userPassword = document.getElementsByName("userPassword");
			var confirmPassword = document.getElementsByName("confirmPassword");
			var userAge = document.getElementsByName("userAge");
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
			}else if(userAge[0].value == ""){
				alert("年龄不能为空！");
				userBirth[0].focus();
			}else if(userBirth[0].value == ""){
				alert("生日不能为空！");
				userBirth[0].focus();
			}else if(!emailReg.test(userEmail[0].value)){
				alert("邮箱格式不正确！");
				userEmail[0].value="";
				userEmail[0].focus();
			}else{
				document.getElementsByName("userUpd")[0].submit();
			}
		}
	</script>
  </head>
  <body>
  	${error }
    <form name="userUpd" action="userAction?cmd=upd" method="post">
    	<input type="hidden" name="${data.userId }"/>
    	<table>
    		<tr>
    			<td>用户名:</td><td><input readonly="true" name="userName" value="${data.userName}"/></td><td>*用户名输入1-10个字符</td>
    		</tr>
    		<tr>
    			<td>用户密:</td><td><input name="userPassword" value="${data.userPassword}"/></td><td>*用户密码输入1-10个字符</td>
    		</tr>
    		<tr>
    			<td>确认:</td><td><input name="confirmPassword" value="${data.confirmPassword}"/></td><td>*用户密码输入1-10个字符</td>
    		</tr> 
    		<tr>
    			<td>年龄:</td><td><input name="userAge"  value="${data.userAge}"/></td><td></td>
    		</tr>     		   		
    		<tr>
    			<td>出生:</td><td><input name="userBirth"   value="${data.userBirth}"/></td><td></td>
    		</tr>     		
    		<tr>
    			<td>兴趣:</td>
    			<td>
    				<c:forEach items="${hobbies }"  var="hobby" varStatus="status">
    				    <c:set var="find" value="false"></c:set>
    					<c:forEach items="${data.hobbies}" var="hobby2" varStatus="status2">
    						<c:if test="${hobby2==hobby }">
	    						<c:set var="find" value="true"></c:set>
	    					</c:if>					
	  					</c:forEach>	
	  					<c:if test="${find }">
	  						<input type="checkbox" name="hobbies" value="${hobby}"  checked/>${hobby}
  						</c:if>
   						<c:if test="${find==false }">
    						<input type="checkbox" name="hobbies" value="${hobby }" />${hobby }
  						</c:if>		
    				</c:forEach>
    			</td><td></td>
    		</tr>
    		<tr>
    			<td>头像:</td>
    			<td width="40%">
    				<c:forEach items="${heads}" var="head" varStatus="status">
   						<c:if test="${data.userImgFace==head }">
    						<input type="radio" name="userImgFace" value="${head }" checked/><img width="48px" src=" ${basePath }headImg/${head}"/>
  						</c:if>
   						<c:if test="${data.userImgFace!=head }">
    						<input type="radio" name="userImgFace" value="${head }"/><img width="48px" src=" ${basePath }headImg/${head}"/>
  						</c:if>    				
    				</c:forEach>
    			</td><td></td>
    		</tr>
    		<tr>
    			<td>email:</td><td><input type="text" name="userEmail" value="${data.userEmail}"/></td><td></td>
    		</tr>    		    		
    		<tr>
    			<td colspan="3"><input type="button" onClick="validate()" value="更新"/><input type="reset" value="重置"/></td>
    		</tr>    		    		
    	</table>
    </form>
  </body>
</html>
