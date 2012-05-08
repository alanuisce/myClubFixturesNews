<%@page import="ie.myClub.cloudApp.News"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="nList" class="ie.myClub.cloudApp.NewsItems" scope="session"></jsp:useBean>
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Club News</title>
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
<h2>News</h2>

<c:if test="${! empty param.heading }">
 <%
 	News news = new News();
 	news.setHeading(request.getParameter("heading"));
 	nList.addNews(news);
 %>
</c:if>
<c:forEach items="${nList.news}" var="news">
${news.heading}<br/>
</c:forEach>
<h2>Create new News</h2>
<form>
Heading : <input name="heading"><input type="submit">
</form>
</div>
</body>
</html>
