<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Spring Finite State Machine</title>
<style>
.center {
	text-align: center;
}
</style>
</head>

<div class="center">
	<body>
		<h1>
			<font color="blue">Spring Finite State Machine</font>
		</h1>
		<br>
		<br>
		<form action="EventTrigger" method="post">
			Events: &nbsp; &nbsp; &nbsp; <select name="selectedEvent">
				<option value="event1" selected>EVENT 1</option>
				<option value="event2">EVENT 2</option>
				<option value="event3">EVENT 3</option>
				<option value="event4">EVENT 4</option>
			</select> <br> <br> <input type="submit" name="submitEventButton"
				value="Submit Selected Event" style="width: 160px;"> <br>
		</form>
	</body>

	New State: <font color="red"> <c:out value=" ${currentState}" />
		<br> <font color="black"> Action: <font color="blue">
				<c:out value=" ${currentAction}" />

		</font> <br> <br> <a href="https://letrungthang.blogspot.com">https://letrungthang.blogspot.com</a>

			<br> <br> <img alt="images"
			src="<c:url value='/resources/FSM.PNG'/>" width="600" height="300">
			<br>
</div>
</html>