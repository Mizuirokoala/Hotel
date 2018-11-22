<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/head_config.jspf"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/register page</title>
</head>
<body>
	<%@ include file="../jspf/main_menu.jspf"%>
	<h2 align="center">Your reservation is confirmed :)</h2>
<h3 align="center">${pet.name} will be spending ${datesNumber} days in ${hotel.name}</h3>
<p align="center">Booked dates:</p> <br>
<c:forEach items="${dates}" var="date">
<p align="center">${date.day} ${date.month.name} ${date.year}</p><br>
</c:forEach><br><br>

<p align="center">In case of any further questions, please contact the hotel directly at: ${hotel.email}</p>



</body>
</html>