<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file = "../jspf/head_config.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file = "../jspf/main_menu.jspf" %>
	<h2 align="center">Welcome to Pet Hotel!</h2>
	<%@ include file = "../jspf/edit_hotel_small.jspf" %>


			<%@ include file="../jspf/available_hotels.jspf"%>


</body>
</html>