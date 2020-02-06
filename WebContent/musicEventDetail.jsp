<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="logic.bean.MusicEventBean, logic.bean.GeneralUserBean, logic.utils.*" %>
<%
	String part;
	String origin = (String) session.getAttribute("origin");
	MusicEventBean meb = (MusicEventBean) session.getAttribute("Mevent");
	GeneralUserBean user = (GeneralUserBean) session.getAttribute("user");
	boolean isPart = (boolean) request.getAttribute("isPart");
	if (isPart == false) {
		part = "Add Participation";
	} else {
		part = "Remove Participation";
	}
	//session.setAttribute("Mevent", meb);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
     -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- 
    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
 -->

    <link href="css/style.css" rel="stylesheet" type="text/css">
<title>Music Event</title>
</head>
<body class = "defaultBackgorund">
<div class="container">

 <div class="splitBanner left">
  <div class="centered">
  
  <!-- Logo -->
  <div class = "logoPosition">
  <img class = "logoImg" src="img/concertIcon.png" height = 106.8 width = 106.8>
  <div class = "row">
  <p class = "logoText">LIVE<mark style = "background-color: rgba(0,0,0,0); color: #6A4C93">the</mark>MUSIC</p>
  </div>
  </div>
  
    <ul>
    <%
    if(origin.equals("AdminMusicEventServlet")){
    	%>
    	<li><form action="AdminMusicEventServlet" method="POST"><input type="submit" class = "selected" value="Home"></form></li>
    	<li><form action="adminNews.jsp" method="POST"><input type="submit" class = "notSelected" value="News"></form></li>
    	<%
    } else {
    	%>
    
    <li><form action="BuyTicketServlet" method="POST"><input type="submit" class = "selected" value="Home"></form></li>
    <li><form action="ReadNewsServlet" method="POST"><input type="submit" class = "notSelected" value="News"></form></li>
    <li><form action="favorites.jsp" method="POST"><input type="submit" class = "notSelected" value="Favorites"></form></li>
    <li><form action="friends.jsp" method="POST"><input type="submit" class = "notSelected" value="Friends"></form></li>
    <li><form action="aroundyou.jsp" method="POST"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="myevents.jsp" method="POST"><input type="submit" class = "notSelected" value="My Events"></form></li>
    <%} %>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
  <!-- Concert Cover -->
  <img src="<%="img/concertPictures/" + meb.getCoverPath()%>" height = 334  width = 1252 style = "object-fit: cover;" >
  <div class="centered" style="margin-left:30px;">
  

<form action = "ButtonHandler" method = "POST">
<input type="submit" class = "submit" name = "back" value = "back">
</form>

<h1><%=meb.getName()%></h1>
<h2><%=meb.getLocation()%></h2>
<h3><%=meb.getArtistId()%></h3>
<!-- Add participation -->
<form action = "ButtonHandler" method = "POST">

<input type = "hidden" name = "isPart" value = "<%=isPart%>">
<%
	if(origin.equals("AdminMusicEventServlet")){
		%>
		<input type="submit" name = "accept" value = "Accept Event">	
		<input type="submit" name = "reject" value = "Reject Event">		
		<%
	} else {
		%><input type="submit" name = "addPart" value = "<%=part%>"><%
	}
%>
<input type="submit" name = "goToTicketone" value = "TicketOne">
</form>
<!-- TicketOne Link -->
</div>
</div>
</div>
</body>
</html>