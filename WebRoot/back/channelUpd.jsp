<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath }">
    <title>版块修改</title>
    <script type="text/javascript">
    	function validate(){
    		var channelName = document.getElementsByName("channelName");
    		var channelDes = document.getElementsByName("channelDes");
    		
    		if(channelName[0].value == ""){
    			alert("版块名不能为空");
    			channelName[0].focus();
    		}else if(channelDes[0].value == ""){
    			alert("版块内容不能为空");
    			channelName[0].focus();
    		}else{
    			document.getElementsByName("channelUpd")[0].submit();
    		}
    	}
    </script>
  </head>
  
  <body>
    <form name="channelUpd" action="channelAction?cmd=upd" method="post">
    	<input type="hidden" name="channelId" value="${data.channelId}">
    	<table>
    		<caption>修改版块</caption>
    		<tr>
    			<td>版块名:</td><td><input name="channelName" value="${data.channelName}"></td><td>*请输入1-20个字符</td>
    		</tr>
    		<tr>
    			<td>版块描述:</td><td><textarea name="channelDes"  cols=30 rows=5>${data.channelDes}</textarea></td><td>*请输入1-200个字符</td>
    		</tr>
    		<tr>	
    			<td colspan="3"><input type="button" onclick="validate()" value="更新"/><input type="reset" value="重置"/></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
