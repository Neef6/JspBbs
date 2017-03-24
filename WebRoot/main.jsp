<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<base href="${basePath }">
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs.css">
	<!-- <script>
	function changtoReplies(obj)
	{
		location="repliesAction?cmd=frontFindAll&id="+obj;
	}
	</script> -->
  </head>
  
  <body>
    <c:import url="inc/head.jsp"></c:import >
    <!-- 主体部分 -->
    <div class="main_nav_wrap">
	    <div class="main_nav_left" >
	    	<!-- 登陆的头 -->
	    	<div class="left_title" >
	    		<a>状态</a><span>&nbsp;</span>
	    	</div>
	    	<!-- 登陆的体 -->
	    	<div class="login_title">
	    		<c:import url="inc/login.jsp"></c:import>
	    	</div>
	    </div>
	    <div class="main_nav_center">
	    	<!-- 置顶帖 -->
	    	<div class="left_title" style="clear: both;">
	    		<a>置顶</a><span>&nbsp;</span>
	    	</div>
	    	<!-- 置顶帖-->
	    	<div class="login_title">
	    		<c:forEach var="topic" items="${listTop}">
	    		<a href="repliesAction?cmd=frontFindAll&id=${topic.topicId}">
				    <c:out value="${topic.topicTitle}"/><br>				   
				</a>
				</c:forEach>
	    	</div>
	    </div>
	    <div class="main_nav_right">
	    	<!-- 重要的帖子的头 -->
	    	<div class="left_title" style="clear: both;">
	    		<a>精华贴</a><span>&nbsp;</span>
	    	</div>
	    	<!-- 重要的帖子的体 -->
	    	<div class="login_title" >
	    		<c:forEach var="topic" items="${listEssence}">
	    		<a href="repliesAction?cmd=frontFindAll&id=${topic.topicId}">
				    <c:out value="${topic.topicTitle}"/><br>
				</a>
				</c:forEach> 
	    	</div>	   
	    </div>
	    <div style="clear: both;"></div>
    </div>
    <!-- 版块部分 -->
     <c:forEach items="${requestScope.channelList}" var="channel" varStatus="vs">
     <a href="topicAction?cmd=frontFindAll&id=${channel.channelId}">
     	<div class="channel_nav_wrap">
    			<div class="channel_name">${channel.channelName}</div>
    			<div class="channel_des">${channel.channelDes}</div>
    	</div>
    </a>
    </c:forEach>
   
    <!-- 友情链接部分 -->
    <div  class="link_nav_wrap">
    	<div class="left_title" >
    		友情链接<span>&nbsp;</span>
    	</div>
    	<div class="link_list">
		   <a href="http://www.baidu.com">百度</a> | <a href="http://bbs.nju.edu.cn">小百合</a> | <a href="http://www.tianya.cn">天涯</a> | <a href="http://www.sina.com.cn">新浪</a> |
     	</div>
    </div>
    <!-- 版权部分 -->
    <c:import url="inc/copyright.jsp"></c:import>
  </body>
</html>
