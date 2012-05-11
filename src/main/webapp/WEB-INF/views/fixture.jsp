<%@ page import="ie.myClub.cloudApp.Fixture"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Club Fixtures</title>
</head>
<body>
	<div id="header">My Club Fixtures and News</div>
	<div id="menu">
	<a href="j_spring_security_logout">Logout: <security:authentication property="principal.username" /></a>
		<ul>
			<li><a href="main.jsp">Home</a>
			<li><a href="fixture.html">Fixtures C</a>
			<li><a href="news.html">News C</a>
		</ul>
	</div>
	<div id="mainContent">
		<h1>Fixture application (controller)</h1>
		<h2>Create new Fixture</h2>
		<form method="post">
			<table>
				<tr>
					<td>Venue:</td>
					<td><input name="venue"></td>
				</tr>
				<tr>
					<td>When:</td>
					<td><input name="when"></td>
				</tr>
				<tr>
					<td>Training:</td>
					<td><input name="training" type="checkbox"></td>
				</tr>
				<tr>
					<td>&nbsp</td>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>
		<h2>List of current Fixtures</h2>
		<table>
			<tr>
				<td>Venue</td>
				<td>When</td>
				<td>Type of Fixture</td>
				<td>Update Type</td>
				<td>Delete</td>
			</tr>
			<c:forEach items="${fixture}" var="fixture" varStatus="row">


<tr>
	<td>${fixture.venue}</td>
	<td>${fixture.when}</td>
	<td><c:choose>
			<c:when test="${fixture.training}">
Training
</c:when>
			<c:otherwise>
Game
</c:otherwise>
		</c:choose></td>

	<td>
		<form method="post">
			<input name="_method" type="hidden" value="put"> <input
				name="fixtureId" type="hidden" value="${fixture.id}"> <input
				type="submit" value="Training/Competition">
		</form>
	</td>
	<td>
		<form method="post">
			<input name="_method" type="hidden" value="delete"> <input
				name="fixtureId" type="hidden" value="${fixture.id}"> <input
				type="submit" value="Delete">
		</form>
	</td>
</tr>
</c:forEach>
		</table>
	</div>
</body>
</html>