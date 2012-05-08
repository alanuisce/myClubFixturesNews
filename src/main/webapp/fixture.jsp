<%@ page import="ie.myClub.cloudApp.Fixture"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="fList" class="ie.myClub.cloudApp.FixtureList"
	scope="session"></jsp:useBean>
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
			<li><a href="fixtures">Fixtures S</a>
<li><a href="news">News S</a>
		</ul>
	</div>
	<div id="mainContent">
		<h2>Fixtures</h2>

		<c:if test="${param._method.equals(\"delete\")}">
<%
	String strFixtureId = request.getParameter("fixtureId");
	Integer fixtureId = Integer.valueOf(strFixtureId);
	fList.getFixtures().remove(fixtureId - 1);
%>
</c:if>

<c:if test="${param._method.equals(\"put\")}">
<%
	String strFixtureId = request.getParameter("fixtureId");
	Integer fixtureId = Integer.valueOf(strFixtureId);
	Fixture fixture = fList.getFixtures().get(fixtureId - 1);
	fixture.setTraining(!fixture.isTraining());
%>
</c:if>

<c:if test="${! empty param.text }">
<%
	Fixture fixture = new Fixture();
	fixture.setVenue(request.getParameter("venue"));
	fList.addFixture(fixture);
%>
</c:if>

<c:forEach items="${fList.fixtures }" var="fixture" varStatus="row">

${fixture.venue }<br/>${fixture.training }<br/>

<form method="post">
<input name="_method" type="hidden" value="delete">
<input name="fixtureId" type="hidden" value="${row.count }">
<input type="submit" value="Delete">
</form>

<form method="post">
<input name="_method" type="hidden" value="put">
<input name="fixtureId" type="hidden" value="${row.count }">
<input type="submit" value="Training/Competition">
</form>

</c:forEach>
<h2>Create new Fixture</h2>
<form method="post">
Venue: <input name="text"><input type="submit">
</form>
	</div>
</body>
</html>
