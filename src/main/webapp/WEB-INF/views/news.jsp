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
<title>My Club News</title>
</head>
<body>
<div id="header">My Club Fixtures and News</div>
<div id="menu">
<a href="j_spring_security_logout">Logout: <security:authentication property="principal.username" /></a>
<ul>
<li><a href="main.jsp">Home</a>
<li><a href="fixture.html">Fixtures</a>
<li><a href="news.html">News</a>
</ul>
</div>
<div id="mainContent">
	<h1>News</h1>
	<h2>Create new News</h2>
	<form method="post">
	<table>
		<tr><td>Heading:</td><td><input name="heading"></td></tr>
		<tr><td>Body:</td><td><input name="body"></td></tr>
		<tr><td>&nbsp</td><td><input type="submit"></td></tr>
	</table>
	</form>
	<h2>List of My current News</h2>
	<table width=100%>
	<c:forEach items="${news}" var="news" varStatus="row">
			<tr><td><b>${news.heading}</b></td>
			<td>
		<form method="post">
			<input name="_method" type="hidden" value="delete"> <input
				name="newsId" type="hidden" value="${news.id}"> <input
				type="submit" value="Delete">
		</form></td></tr>
		<tr><td>${news.body}</td></tr>
	</c:forEach>
	</table>
	
	<h2>List of ALL current News</h2>
	<table width=100%>
	<c:forEach items="${newsAll}" var="newsAll" varStatus="row">
		<tr><td><b>${newsAll.heading}</b></td></tr>
		<tr><td>${newsAll.body}</td></tr>
	</c:forEach>
	</table>
	
	</div>
</body>
</html>