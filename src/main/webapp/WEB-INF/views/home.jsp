<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="jspf/head_config.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="jspf/main_menu.jspf"%>
	<h2 align="center">The Best Place for your Pets!</h2>
	<form method="post">
		Filter hotels by cities <select name="city">
			<c:forEach var="city" items="${citiesList}">
				<option value="${city}">${city}</option>
			</c:forEach>
		</select>

		<%-- 		<form:select path="hotel1.addressCity" items="${availableHotels}"
			itemValue="addressCity" label="please select" itemLabel="addressCity"></form:select>
 --%>
		<input type="submit" />
	</form>

	<c:choose>
		<c:when test="${cityHotels == null}">
			<%@ include file="jspf/available_hotels.jspf"%>
		</c:when>
		<c:otherwise>
			<%@ include file="jspf/city_hotels.jspf"%>
		</c:otherwise>
	</c:choose>
</body>
</html>