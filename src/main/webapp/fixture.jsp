<%@page import="ie.myClub.cloudApp.Fixture"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="fList" class="ie.myClub.cloudApp.FixtureList" scope="request"></jsp:useBean>
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Club Fixtures</title>
</head>
<body>
<div id="header">My Club Fixtures and News</div>
<div id="menu">
<ul>
<li><a href="main.jsp">Home</a>
<li><a href="fixture.jsp">Fixtures</a>
<li><a href="news.jsp">News</a>
</ul>
</div>
<div id="mainContent">
<h2>Fixtures</h2>

<c:if test="${! empty param.venue }">
 <%
    Fixture fixture = new Fixture();
 	fixture.setVenue(request.getParameter("venue"));
 	fList.addFixture(fixture);
 %>
</c:if>
<c:forEach items="${fList.fixtures}" var="fixture">
${fixture.venue}<br/>
</c:forEach>
<h2>Create new Fixture</h2>
<form>
Venue : <input name="venue"><input type="submit">
</form>
</div>
</body>
</html>
