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
	<%@ include file="../jspf/reservation_progress.jspf"%>
	<p align="center">
		<c:forEach items="${months}" var="month">
			<a href="/reservationDate/${month.id}/month"><button
					type="button" class="btn btn-info" style="width: 9em">
					<h4>${month.name}</h4>
				</button></a>
		</c:forEach>
	</p>
	<br>
	<br>
	<h2 align="center">${chosenMonth.name}</h2>
	<table class="table-small" align="center">
		<thead class="thead-light">
			<tr align="center">
				<th>Monday</th>
				<th>Tuesday</th>
				<th>Wednesday</th>
				<th>Thursday</th>
				<th>Friday</th>
				<th>Saturday</th>
				<th>Sunday</th>
			</tr>
		</thead>
		<c:forEach items="${hotelDates}" var="date">
			<c:if test="${date.weekDay == 1}">
				<tr>
			</c:if>
			<c:if test="${date.day == 1}">
				<c:forEach begin="1" end="${chosenMonth.dayDifference}" var="num">
					<td></td>
				</c:forEach>
			</c:if>
			<td align="center"><c:choose>
					<c:when test="${(date.day < tDay) and (date.month.id <= tMonth)}">
						<button type="button" class="btn btn-default">
							<h3>${date.day}</h3>
							Past date
						</button>

					</c:when>
					<c:when test="${(date.day == tDay) and (date.month.id == tMonth)}">
						<a href="/reservationDate/${date.id}/date"><button
								type="button" class="btn btn-success"
								style="background-color: #F0E68C;">
								<h3>${date.day}</h3>
								Places left ${date.placesLeft}
							</button></a>
					</c:when>
					<c:when test="${date.placesLeft > 5}">
						<a href="/reservationDate/${date.id}/date"><button
								type="button" class="btn btn-success">
								<h3>${date.day}</h3>
								Many places left
							</button></a>
					</c:when>
					<c:when test="${date.placesLeft > 3}">
						<a href="/reservationDate/${date.id}/date"><button
								type="button" class="btn btn-success"
								style="background-color: orange;">
								<h3>${date.day}</h3>
								Places left ${date.placesLeft}
							</button></a>
					</c:when>
					<c:when test="${date.placesLeft > 0}">
						<a href="/reservationDate/${date.id}/date"><button
								type="button" class="btn btn-danger">
								<h3>${date.day}</h3>
								Places left ${date.placesLeft}
							</button></a>
					</c:when>

					<c:otherwise>
						<button type="button" class="btn btn-default">
							<h3>${date.day}</h3>
							Places left ${date.placesLeft}
						</button>
					</c:otherwise>
				</c:choose></td>
			<c:if test="${date.weekDay == 0}">
				</tr>
			</c:if>
		</c:forEach>
	</table>

</body>
</html>