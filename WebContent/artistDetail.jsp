<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="logic.buyticket.*" %>

<%
	ArtistBean ab = (ArtistBean) request.getAttribute("artist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Artist details</title>
</head>
<body>
<h1><%=ab.getBandName() %></h1>
</body>
</html>