<%@ page language="java" import="java.util.*,cn.edu.nju.model.BbsUser,cn.edu.nju.utils.SplitPage"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="css/bbs2.css">
</head>
<body>

	<h1>用户信息管理</h1>
	<%-- <h3>${empty user?'您没有登陆!':'已经登陆'}</h3> --%>
	<Table
		style="border:1px solid;border-color:blue;border-collapse:collapse;width:80%">
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>用户名</th>
				<th>用户年龄</th>
				<th>生日</th>
				<th>兴趣</th>
				<th>邮箱</th>
				<th>修改</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list}" var="user" varStatus="vs">
				<tr align="center">
					<td>${vs.count}</td>
					<td>${user.userName }</td>
					<td>${user.userAge }</td>
					<td>${user.userBirth }</td>
					<td>
						<%-- <%
				String hobby = ((BbsUser)request.getAttribute("user")).getUserHobby();
				if(hobby!=null)
					out.println(Arrays.toString(hobby.split(",")));
			%> --%> ${user.userHobby}
					</td>
					<td>${user.userEmail }</td>
					<td><a href="userAction?cmd=toUpdThisUser&id=${user.userId }">修改</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
