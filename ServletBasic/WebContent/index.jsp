<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>UserLogin</title>
</head>
<%@page import="sample.MySessionListener"%>
<body>
	<h1>User Login</h1>
	<form action="url-login" method="post">
		Enter User Name: <input type="text" name="uname"> <br> <br>
		Enter Pin Number: <input type="password" name="pname"> <br>
		<br> <input type="submit" name="Login" value="Login"> <br>
		<br>Login Number:
		<%=MySessionListener.onlineNumber%>
	</form>
</body>

<br> To create a MySQL DB name: "examuser" and a table "users" as below
<br>
<br> CREATE DATABASE `examuser`
<br> CREATE TABLE `users` ( `title` varchar(45) DEFAULT NULL,
`description` varchar(45) DEFAULT NULL, `startDay` varchar(45) DEFAULT
NULL, `endDay` varchar(45) DEFAULT NULL ) ENGINE=InnoDB DEFAULT
CHARSET=utf8;
<br>
</html>