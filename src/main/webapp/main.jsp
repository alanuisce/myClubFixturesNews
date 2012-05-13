<%@ page import="ie.myClub.cloudApp.Fixture"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Club, Fixtures and News</title>
</head>
<body>
<div id="header">My Club Fixtures and News</div>
<div id="menu">
<ul>
<li><a href="main.jsp">Home</a>
<li><a href="fixture.html">Fixtures</a>
<li><a href="news.html">News</a>
</ul>
</div>
<div id="mainContent">
<h1>Select the Fixtures or news on the left</h1>
<h2>List of ALL current Fixtures</h2>
		<table>
			<tr>
				<td>Venue</td>
				<td>When</td>
				<td>Type of Fixture</td>
			</tr>
			<c:forEach items="${main}" var="main" varStatus="row">
				<tr>
					<td>${main.venue}</td>
					<td>${main.when}</td>
					<td><c:choose>
							<c:when test="${main.training}">
								Training
							</c:when>
							<c:otherwise>
								Game
							</c:otherwise>
						</c:choose></td>
				</tr>
		</c:forEach>
		</table>
</div>
</body>
</html>