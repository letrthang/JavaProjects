<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<a href="menu.jsp"><font size="4">Go to Menu</font></a>
<font size="4">
	<head>
	</head> <%@page import="java.sql.Connection"%> <%@page
		import="java.util.List"%> <%@page
		import="java.util.ArrayList"%> <%@page
		import="java.util.Iterator"%> <%@page
		import="sample.UserInfor"%> <%
 	List<UserInfor> ViewUserList = (ArrayList<UserInfor>) session.getAttribute("UserInfor");
 %>
	<%--moi dau vo load form, ViewUserList la null  --%>

	<body>

		<%-- onload = "document.form2.submit();"  tu dong chay cai nay khi vua vao trang list.jsp --%>


		<form name="form2" method="post" action="ListUser">

			<h1 align="center">
				<font size="5">Listing Users:</font>
			</h1>
			<p>
				<font size="5">&nbsp; <input type="submit" value="Display"
					name="CammandButton">&nbsp;&nbsp; <input type="submit"
					value="Delete All" name="CammandButton"></font>
			</p>
			<table border="1" cellspacing="0" width="806"
				bordercolorlight="#000080" bordercolordark="#0000FF"
				bordercolor="#008080" height="54">
				<tr>
					<th height="22" width="64"><font size="4">No</font></th>
					<th height="22"><font size="4">Title</font></th>
					<th height="22" width="294"><font size="4">Description</font></th>
					<th width="108" height="22">Start Day</th>
					<th width="118" height="22"><font size="4">End Day</font></th>
				</tr>

				<%
					if (ViewUserList != null) {
						System.out.println("list.jsp - ViewUserList khac null");
						//session.setAttribute("UserInforForward", "Forward_None");
						Iterator<UserInfor> itr = ViewUserList.iterator();
						while (itr.hasNext()) {
				%>
				<%
					UserInfor UserDisplay = itr.next();
				%>
				<tr>
					<td width="64">
						<p align="center">
							<font size="4"><%=UserDisplay.No%></font>
					</td>
					<td>
						<p align="center">
							<font size="4"><%=UserDisplay.title%></font>
					</td>
					<td width="294">
						<p align="center">
							<font size="4"><%=UserDisplay.description%></font>
					</td>
					<td width="108">
						<p align="center">
							<font size="4"><%=UserDisplay.startDay%></font>
					</td>
					<td width="118">
						<p align="center">
							<font size="4"><%=UserDisplay.endDay%></font>
					</td>
				</tr>
				<%
					}
					}
				%>
				<%
					if (ViewUserList != null) {
						ViewUserList.clear();
					}
				%>
			</table>
			<p align="center"></p>
		</form>
	</body>
</html>