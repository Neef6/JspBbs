<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="${basePath}">
    <title>精华帖阅读</title>
	<link rel="stylesheet" type="text/css" href="${basePath}css/bbs.css">
	<link rel="stylesheet" type="text/css" href="${basePath}css/bbs2.css">
  </head>
  <body>
	<c:import url="inc/head.jsp"></c:import>
	<div style="padding:7px 0 0px 7px;border:1px solid #BDCFE3;height:23px;background: url('${basePath}img/channel_name.gif');">
  	  	导航：<a href="">首页</a>--><a href="topicAction?cmd=frontFindAll&id=${topic.bbsChannel.channelId}">${topic.bbsChannel.channelName}</a>--><a href="repliesAction?cmd=frontFindAll&id=${topic.topicId}">${topic.topicTitle}</a>
	</div>
	<div>
  		<table width="100%" style="margin-bottom: 10px">
  		<tr>
  			<td width="180px">
  			头像：<c:if test="${! empty replies.bbsUser.userImgFace}" >
  			         <img width="48px" src="${basePath}headImg/${replies.bbsUser.userImgFace}" />
  			   </c:if>
  			   <c:if test="${empty replies.bbsUser.userImgFace}" >
  			         <img width="48px" src="${basePath}headImg/c.jpg" />
  			   </c:if><br/>
  			姓名：${requestScope.topic.bbsUser.userName}<br/>
  			状态：${requestScope.topic.bbsUser.bbsState.stateDes}<br/>
  			等级：${requestScope.topic.bbsUser.bbsRole.roleDes}<br/>
  			</td>
  			<td  style="text-align:left;padding-left:7px;vertical-align: top;">
  				<h2>楼主：${requestScope.topic.topicTitle}</h2>
  				<span>
  					${requestScope.topic.topicComment}
  				</span>
  			</td>
  		</tr>
 		<c:forEach var="replies" items="${requestScope.list}" varStatus="vs">
		  	<tr>
				<td width="180px">
		  			头像：<c:if test="${! empty replies.bbsUser.userImgFace}" >
		  			<img width="48px" src="${basePath}headImg/${replies.bbsUser.userImgFace}" />
		  			</c:if><c:if test="${empty replies.bbsUser.userImgFace}" >
		  			<img width="48px" src="${basePath}headImg/c.jpg" />
		  			</c:if><br/>
		  			姓名：${replies.bbsUser.userName}<br/>
		  			状态：${replies.bbsUser.bbsState.stateDes}<br/>
		  			等级：${replies.bbsUser.bbsRole.roleDes}<br/>
	  			</td>
	  			<td  style="text-align:left;padding-left:7px;vertical-align: top;">
	  				<h2>${replies.repliesTitle}</h2>
	  				<span>
	  					${replies.repliesComment}
	  				</span>
	  			</td>
	  		</tr>
  		</c:forEach> 
  		</table>
  		<div style="clear:both;"></div>
	</div>	
    <div  style="margin-bottom: 10px">
    	<form action="repliesAction?cmd=add" method="Post" >
    		<input type="hidden" value="${topic.topicId }" name="id" />
    		<table style="margin:0 auto;" width="100%">
    			<caption>回贴</caption>
    			<tr><td>主题：</td><td><input type="text"  name="repliesTitle" value="re：${requestScope.topic.topicTitle}"/></td><td>*请输入1-100个字符</td></tr>
    			<tr><td>内容：</td><td><textarea name="repliesComment"></textarea></td><td>*请输入5000个字符</td></tr>
    			<tr><td colspan='3'><input type="submit" value="提交"/><input type="reset" value="重置"/></td></tr>
    		</table>
    	</form>
    </div>
  	<c:import url="inc/copyright.jsp"></c:import>
  </body>
</html>
