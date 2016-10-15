<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new</title>
<a href="menu.jsp">Go to Menu</a>
</head>

<script language="JavaScript1.3">
	
function check_input(){
alert("Kiem Tra chuc Nang Javascripts");}

</script>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="sample.UserInfor"%>

<%
	//boolean chkBirthday = true;
	String errMsg = "";
	String userLogin = "";
	String CommandView = "";
	//String username = request.getParameter("txtUsername");
	//if (chkBirthday == false){
	//errMsg = "The invalid format of the birthday.";
	//}
	//else{   
	//}

	errMsg = (String) session.getAttribute("ErrorMessage");
	userLogin = (String) session.getAttribute("UserLogin");
	CommandView = (String) session.getAttribute("CommandView");
	List<UserInfor> ViewUserAdding = (ArrayList<UserInfor>) session.getAttribute("UserAdding");
	//moi dau vo load form, ViewUserAdding la null 
	List<UserInfor> ViewUserSearch = (ArrayList<UserInfor>) session.getAttribute("UserSearch");

	if (errMsg == null) {
		errMsg = "";
	}
%>

<body>
	</p>
	<form name="form1" method="post" action="AddUser">
		<table width="600" cols="2" border="0" align="center" cellpadding="0"
			cellspacing="0">

			<tr>
				<td colspan="2" class="styleTitleBig" align="center" height="25"><b>
						<font color="#FF0000"><%=errMsg%></font>
				</b></td>
			</tr>
			<tr>
				<td colspan="2" class="styleTitleBig" height="42">
					<div align="left">
						<b><font color="#0000FF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								ADD &amp; SEARCH&nbsp; SCREEN</font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="styleText1" align="right">Hello: <%=userLogin%>
					<input type="hidden" name="AddCommand" value="<%=CommandView%>"></td>
			</tr>
			<tr>
				<td class="styleText"><b><font size="4">Title</font></b></td>
				<td class="styleText" width="479"><input name="txtTitle"
					type="text" id="txtTitle" size="29" style="font-size: 12pt"></td>
			</tr>
			<tr>
				<td class="styleText"><b><font size="4">Description</font></b></td>
				<td class="styleText" width="479"><textarea rows="2"
						name="txtDescription" cols="23" style="font-size: 12pt"></textarea></td>
			</tr>
			<tr>
				<td class="styleText" height="22"><b><font size="4">Start
							date</font></b></td>
				<td class="styleText" height="22" width="479"><input
					name="txtStartDay" type="text" id="txtStartDay" size="29"
					style="font-size: 12pt"></td>
			</tr>
			<tr>
				<td class="styleText" height="28"><b><font size="4">End
							date</font></b></td>
				<td class="styleText" height="28" width="479"><input
					name="txtEndDay" type="text" id="txtEndDay" size="29"
					style="font-size: 12pt; position: relative;"></td>
			</tr>
			<tr>
				<td width="121" class="styleText" height="64"></td>
				<td class="styleText" width="479" height="64"><input
					type="submit" name="commandButton" value="Save"
					onClick="check_input()" style="font-size: 12pt; margin-top: 10">&nbsp;&nbsp;&nbsp;
					<input type="submit" name="commandButton" value="Search"
					style="text-align: justify; margin-left: 0; margin-right: 0; font-size: 12pt; margin-top: 10">
				<p>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<p>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="styleText1"></td>
			</tr>
		</table>
		<p>
			<input type="submit" value="Delete" name="commandButton">
		</p>
		<table border="1" width="99%" id="table1" height="69">
			<tr>
				<td width="49" bgcolor="#C0C0C0">
					<p align="center">
						<b>CK</b>
				</td>
				<td width="54" bgcolor="#C0C0C0">
					<p align="center">
						<b>No</b>
				</td>
				<td width="160" bgcolor="#C0C0C0">
					<p align="center">
						<b>Title</b>
				</td>
				<td width="169" bgcolor="#C0C0C0">
					<p align="center">
						<b>Description</b>
				</td>
				<td width="146" bgcolor="#C0C0C0">
					<p align="center">
						<b>Start date</b>
				</td>
				<td bgcolor="#C0C0C0">
					<p align="center">
						<b>End date</b>
				</td>
			</tr>
			<%
				if ((ViewUserAdding != null) && (CommandView == "Add")) {
					System.out.println("list.jsp - ViewUserList khac null");
					//session.setAttribute("UserInforForward", "Forward_None");
					Iterator<UserInfor> itr = ViewUserAdding.iterator();
					while (itr.hasNext()) {
			%>
			<%
				UserInfor UserDisplay = itr.next();
			%>
			<tr>
				<td width="32" align="center" bgcolor="#E9E9E9"><input
					type="checkbox" name="Cbox<%=UserDisplay.No%>" value="ON"></input></td>
				<td width="54" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.No%>
				</td>
				<td width="160" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.title%>
				</td>
				<td width="169" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.description%>
				</td>
				<td width="146" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.startDay%>
				</td>
				<td bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.endDay%>
				</td>
			</tr>
			<%
				}
				}
			%>
			<%
				if (ViewUserAdding != null) {
					ViewUserAdding.clear();
				}
			%>
			<%--moi lan vao trang thi empty cai list --%>

			<%
				if ((ViewUserSearch != null) && (CommandView == "Search")) {
					System.out.println("list.jsp - ViewUserSearch khac null");
					//session.setAttribute("UserInforForward", "Forward_None");
					Iterator<UserInfor> itr = ViewUserSearch.iterator();
					while (itr.hasNext()) {
			%>
			<%
				UserInfor UserDisplay = itr.next();
			%>
			<tr>
				<td width="32" align="center" bgcolor="#E9E9E9"><input
					type="checkbox" name="Cbox<%=UserDisplay.No%>" value="ON"></input></td>
				<td width="54" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.No%>
				</td>
				<td width="160" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.title%>
				</td>
				<td width="169" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.description%>
				</td>
				<td width="146" bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.startDay%>
				</td>
				<td bgcolor="#E9E9E9">
					<p align="center"><%=UserDisplay.endDay%>
				</td>
			</tr>
			<%
				}
				}
			%>
			<%
				if (ViewUserSearch != null) {
					ViewUserSearch.clear();
				}
			%>
			<%--moi lan vao trang thi empty cai list --%>

		</table>
	</form>
</body>
</html>