<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}">
    
    <title>BBS系统</title>

  </head>
  
  <body>
    <h1>欢迎进入njubbs论坛</h1>
    <a href="${basePath}main.jsp">首页</a>|<a href="${basePath}back/index.jsp">管理</a>|<a href="${basePath}back/userInfo.jsp">个人信息</a>|<a href="${basePath}login.jsp">登录</a>|<a href="userAction?cmd=toAdd&${basePath}register.jsp">注册</a>|<a href="userAction?cmd=exit">退出</a>
    <hr style="height:3px;background: red;">
  </body>
</html>
