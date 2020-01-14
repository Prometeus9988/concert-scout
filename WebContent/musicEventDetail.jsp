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
MusicEventBean meb = (MusicEventBean) session.getAttribute("musicEvent");
%>

<h1><%=meb.getName()%></h1>
<h2><%=meb.getLocation()%></h2>
</body>
</html>