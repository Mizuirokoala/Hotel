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

	<table class="table">
		<tr>
			<th>Name</th>
			<td>${pet.name}</td>
		</tr>
		<tr>
			<th>Category</th>
			<td>${pet.category}</td>
		</tr>
		<tr>
			<th>Breed</th>
			<td>${pet.breed}</td>
		</tr>
		<tr>
			<th>Diet</th>
			<td>${pet.diet}</td>
		</tr>
		<tr>
			<th>Description</th>
			<td>${pet.description}</td>
		</tr>
		<tr>
			<th>Requirements</th>
			<td>${pet.requirements}</td>
		</tr>
		<tr>
			<th>Vaccinated</th>
			<td>${pet.vaccinated}</td>
		</tr>
		<tr>
			<th>Owner email</th>
			<td>${pet.user.email}</td>
		</tr>

	</table>

</body>
</html>