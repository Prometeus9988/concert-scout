<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.login.GeneralUserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<%
GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
%>
<h1>Welcome <%= gu.getUsername() %></h1>
</body>
</html>