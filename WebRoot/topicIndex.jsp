<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
  <head>
    <base href="${basePath }">
    <title>主帖页</title>
	<link rel="stylesheet" type="text/css" href="${basePath}css/bbs.css">
	<link rel="stylesheet" type="text/css" href="${basePath}css/bbs2.css">
	<script>
	
var  highlightcolor='#d5f4fe';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
source.style.cursor='pointer';
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function changtoReplies(obj)
{
	location="repliesAction?cmd=frontFindAll&id="+obj;
}
	</script>
  </head>
  	
  <body>
		<c:import url="inc/head.jsp"></c:import>
  	  	<a href="topicAction?cmd=toAdd&id=${empty param.id?channel.channelId:param.id}">发贴</a>
  	  	<hr/>
  	  	<div>
  	  		导航：<a href="">首页</a>--><a href="topicAction?cmd=frontFindAll&id=${empty param.id?channel.channelId:param.id}">${channel.channelName }</a>
  	  	</div>
  	  	<div style="margin-bottom: 10px">
  	  	<table width="100%" onmouseover="changeto()" onmouseout="changeback()">
  	  		<tr>
  	  		    <th>序号</th>
  	  			<th>主题</th>
  	  			<th>作者</th>
  	  			<th>发贴时间</th>
  	  		</tr>
  	  		<c:forEach var="topic" items="${requestScope.list}" varStatus="vs">
  	  		<tr onclick="changtoReplies(${topic.topicId})" >
  	  		    <td>${vs.count}</td>
  	  			<td>${topic.topicTitle}</td>
  	  			<td>${topic.bbsUser.userName}</td>
  	  			<td>${topic.topicCreateTime}</td>
  	  		</tr>
  	  		</c:forEach>  		
  	  	</table>
  	  	</div>
  	  	<c:import url="inc/copyright.jsp"></c:import>
  </body>
</html>
