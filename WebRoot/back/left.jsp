<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath }">
    <title>左框架</title>
	<link rel="stylesheet" type="text/css" href="${basePath }css/bbs2.css">
  </head>
  
  <body style="height:100%;margin:0px;font-size:12px;">
        <div style="float:left;width:4px; height:100%; background: url('${basePath }img2/border1.gif');">&nbsp;</div>
        <div style="float:left;width:169px;height:20px;background: url('${basePath }img2/sys.gif');">&nbsp;</div>
        <dl>
        	<dt>&nbsp;</dt>
	    	<dd style="padding:6px;"><img style="width:16px;height:16px;margin-bottom:-4px;padding-right:4px;" alt="" src="${basePath }img/bbs_channel.gif"/><a href="${basePath }channelAction?cmd=findAll" target="right">版块管理</a></dd>
			<dd style="padding:6px;"><img style="width:16px;height:16px;margin-bottom:-4px;padding-right:4px;" alt="" src="${basePath }img/bbs_user.gif"/><a href="${basePath }userAction?cmd=findAll" target="right">用户管理</a></dd>
	    	<dd style="padding:6px;"><img style="width:16px;height:16px;margin-bottom:-4px;padding-right:4px;" alt="" src="${basePath }img/bbs_role.gif"/><a href="${basePath }topicAction?cmd=backFindAll" target="right">帖子管理</a></dd>   
	    	<dd style="padding:6px;"><img style="width:16px;height:16px;margin-bottom:-4px;padding-right:4px;" alt="" src="${basePath }img/archive.png"/><a href="${basePath }index.jsp" target="_parent">回到首页</a></dd>
    	</dl>  	 
 </body>
</html>
