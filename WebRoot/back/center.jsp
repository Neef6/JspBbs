<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function switchSysBar() {
		if (window.top.document.getElementById("main").cols == "173,9,*") {
			window.top.document.getElementById("main").cols = "0,9,*";
			document.all("img1").src = "img2/switch_mark_2.gif";
		} else {
			window.top.document.getElementById("main").cols = "173,9,*";
			document.all("img1").src = "img2/switch_mark.gif";
		}
	}
</script>
</head>
<body style="margin:0px;height:100%;">
	<div style="background: url('<%=basePath %>img2/switch_back.gif') repeat-y;height:100%"
		onclick="switchSysBar()">
		<img style="margin-top:260px;" src="<%=basePath %>img2/switch_mark.gif"
			name="img1" width="8" height="52" id="img1">
	</div>
</body>
</html>