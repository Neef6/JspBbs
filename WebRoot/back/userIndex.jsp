<%@ page language="java" import="java.util.*,cn.edu.nju.model.BbsUser,cn.edu.nju.utils.SplitPage"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="css/bbs2.css">
<script type="text/javascript">
		function del() 
		{
		 if(!confirm("确认要删除？") )
		 	{
			window.event.returnValue = false;
			}
		}
	//这段代码实现在分页中
	//切换到分页范围内的任何一个页面
	function go() 
		{
		var goPage = document.all.selectpage.value;
		//alert("我们将去页面:userList.jsp?flag=" + goPage);
		document.open("userList.jsp?flag=" + goPage, "_self", "");
		}
</script>


</head>
<%--
  	spage 是分页对象,保存分页的详细信息,
  	此对象存放在session中每次查询或显示分页数据时只要设置此对象的当前页就可.
  --%>
<jsp:useBean id="spage" class="cn.edu.nju.utils.SplitPage"
	scope="session"></jsp:useBean>
<jsp:useBean id="dao" class="cn.edu.nju.dao.impl.BbsUserDao"
	scope="session"></jsp:useBean>
<%
	//翻页时的方向值,即SplitPage中请求标识参数
	String flag = request.getParameter("flag");
	//每次刷新页面时都应当重新获得表中的记录数,
	//因为翻页过程中表的记录可能随时都会更新
	int totalRows = dao.getRows();//总的记录数
	spage.setTotalRows(totalRows);
	//重新计算确定当前要显示的页面值,这是一次必要的调用,
	//实现了翻页
	int currentPage = spage.confirmPage(flag);
	spage.setPageRows(3);//修改每页显示的记录数,默认3
	spage.setTotalRows(totalRows);//设定总页数
%>
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
				<th>删除</th>
			</tr>
		</thead>
		<tbody>
			<%List<BbsUser> list= dao.findAll(spage); %>
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
					<td><a href="userAction?cmd=toUpd&id=${user.userId }">修改</a></td>
					<td><a href="userAction?cmd=del&id=${user.userId}"
						onclick="del()">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr align="center">
				<%--下面页面分页操作,其它数据分页页面复用时拷贝这段
                                       代码就可以了.
                         --%>
				<a href="userIndex.jsp?flag=<%=SplitPage.FIRSTPAGE%>">首页</a>
				<a href="userIndex.jsp?flag=<%=SplitPage.PREVIOUSEPAGE%>">上一页</a>
				<a href="userIndex.jsp?flag=<%=SplitPage.NEXTPAGE%>">下一页</a>
				<a href="userIndex.jsp?flag=<%=SplitPage.LASTPAGE%>">尾页</a>
				<select id="selectpage" name="goPage" onchange="javascript:go();">
					<%
						for (int i = 1; i <= spage.getTotalPages(); i++) {
					%>
					<option value="<%=i%>"
						<%=(spage.getCurrentPage() == +i) ? "selected='selected'"
						: ""%>><%=i%>/<%=spage.getTotalPages()%>
						<%
							}
						%>
				</select>
			</tr>
		</tfoot>
	</table>
</body>
</html>
