<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, java.util.List, logic.utils.*, logic.bean.GeneralUserBean, logic.bean.MusicEventBean, logic.bean.ArtistBean" %>


<% 
    int i;

	GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
	String username = gu.getUsername();
	List<MusicEventBean> musicEvents = (List<MusicEventBean>) request.getAttribute("musicEventList");
	List<ArtistBean> artists = (List<ArtistBean>) request.getAttribute("artistList");

%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Logo icon -->
    <link rel="icon" href="img/concertIcon.png">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link href="./css/style.css" rel="stylesheet" type="text/css">

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
    <li><form action="BuyTicketServlet" method="POST"><input type="submit" class = "selected" value="Home"></form></li>
    <li><form action="ReadNewsServlet" method="POST"><input type="submit" class = "notSelected" value="News"></form></li>
    <li><form action="favorites.jsp" method="POST"><input type="submit" class = "notSelected" value="Favorites"></form></li>
    <li><form action="FriendsServlet" method="POST"><input type="submit" class = "notSelected" value="Friends"></form></li>
    <li><form action="aroundyou.jsp" method="POST"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="YourEventsServlet" method="POST"><input type="submit" class = "notSelected" value="Your Events"></form></li>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
  <div class="centered" style="margin-left:30px;">
  
  <!-- Search Bar -->
  <!-- home.jsp -->
  <form action="SearchServlet" method="POST">
  <div class="md-form mt-0">
  <input name = "searchString" class="form-control search" type="text" placeholder="Search..." aria-label="Search">
</div>
</form>

    <h2><i>Welcome <%=username%></i></h2>
    
    <!-- List of suggested events -->
    <h3 class = "h3">Suggested Events</h3>
    <ul class = "hs">
    <%
    for(i = 0; i < musicEvents.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    
    <!-- BuyTicketServlet -->
    <form action="ButtonHandler" method="POST">
  	<!-- <img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155> -->
  	  	<button type="submit" name = "<%="m"%>" style="color: transparent; background-color: transparent; border-color: transparent;">
	<img src="<%="img/concertPictures/" + musicEvents.get(i).getCoverPath()%>" class="card-img-top cardImg" height = 215 width = 155 />
	</button>
  	<div class="card-body">
	<input type="submit" name = "<%="m"%>" class = "btTxt astext" value = "<%= musicEvents.get(i).getName() %>">
  	</div>
  	<input type = "hidden" name = "Mevent" value = "<%= musicEvents.get(i).getId() %>">
  	</form>
  	<form action="ButtonHandler" method="POST">
  	<input type="submit" name = "<%="a"%>" class = "btTxt astext" value = "<%= musicEvents.get(i).getBandName() %>">
  	<input type = "hidden" name = "artist" value = "<%= musicEvents.get(i).getArtistId() %>">
  	</form>
  	
</div>

</li>

    		<%
    }
    %>
</ul>

	<!-- List of suggested artists -->
    <h3 class = "h3">Suggested Artists</h3>
       <ul class = "hs">
    <%
    for(i = 0; i < artists.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    <form action="ButtonHandler" method="POST">
  	  	<button type="submit" name = "<%="a"%>" style="color: transparent; background-color: transparent; border-color: transparent;">
	<img src="<%="img/profilePictures/" + artists.get(i).getProfilePicture() %>" class="card-img-top cardImg" height = 215 width = 155 />
	</button>
  	<div class="card-body">
    <input type="submit" name = "<%="a"%>" class = "btTxt astext" value = "<%= artists.get(i).getBandName() %>">
    <input type = "hidden" name = "artist" value = "<%= artists.get(i).getUsername()%>">
  </div>
  </form>
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