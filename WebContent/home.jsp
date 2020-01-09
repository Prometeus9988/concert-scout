<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,logic.buyticket.*, java.util.List" %>
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
<title>Home</title>
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
    <li><form action="home.jsp" method="GET"><input type="submit" class = "selected" value="Home"></form></li>
    <li><form action="news.jsp" method="GET"><input type="submit" class = "notSelected" value="News"></form></li>
    <li><form action="favorites.jsp" method="GET"><input type="submit" class = "notSelected" value="Favorites"></form></li>
    <li><form action="friends.jsp" method="GET"><input type="submit" class = "notSelected" value="Friends"></form></li>
    <li><form action="aroundyou.jsp" method="GET"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="myevents.jsp" method="GET"><input type="submit" class = "notSelected" value="My Events"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
  <div class="centered">
  <% String username = session.getAttribute("username").toString(); //session.getAttribute("user").getUsername();%>
    <h2>Welcome <%=username%></h2>
    <% 
    List<MusicEventBean> musicEvents = BuyTicketController.getInstance().getSuggestedEvents(username);
    List<ArtistBean> artist = BuyTicketController.getInstance().getSuggestedArtist(username);
    %>
    <h3 class = "h3">Suggested Events</h3>
    <ul class = "hs">
    <%
    for(int i = 0; i < musicEvents.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    	
    	
  	<img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155>
  	<div class="card-body">
    <h5 class="card-title"><%= musicEvents.get(i).getName() %></h5>
    <h6 class="card-title"><%= musicEvents.get(i).getArtistId() %></h6>
  </div>
  
</div>

</li>
    		<%
    }
    %>
</ul>

    <h3 class = "h3">Suggested Artists</h3>
       <ul class = "hs">
    <%
    for(int i = 0; i < artist.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    	
  	<img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155>
  	<div class="card-body">
    <h5 class="card-title"><%= artist.get(i).getName() %></h5>
  </div>
</div>

</li>
    		<%
    }
    %>
    </ul>
  </div>
</div> 

</div>
</body>
</html>