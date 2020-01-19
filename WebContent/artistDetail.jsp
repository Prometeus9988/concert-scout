<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="logic.buyticket.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	ArtistBean ab = (ArtistBean) request.getAttribute("artist");
%>

<h1><%=ab.getName()%></h1>
</body>
</html>