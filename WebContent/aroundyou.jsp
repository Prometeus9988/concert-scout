<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList, java.util.List, logic.utils.*, logic.bean.GeneralUserBean, logic.bean.MusicEventBean, logic.bean.ArtistBean" %>
    <%
    String maxdist = (String) request.getAttribute("MAXDISTANCE");
    String mindist = (String) request.getAttribute("MINDISTANCE");
    String defrad = (String) request.getAttribute("DEFAULTRADIUS");
    List<MusicEventBean> musicEvents = (List<MusicEventBean>) request.getAttribute("musicEventList");
    int i;
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

<title>Around you</title>
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
    <li><form action="BuyTicketServlet" method="POST"><input type="submit" class = "notSelected" value="Home"></form></li>
    <li><form action="ReadNewsServlet" method="POST"><input type="submit" class = "notSelected" value="News"></form></li>
    <li><form action="favorites.jsp" method="POST"><input type="submit" class = "notSelected" value="Favorites"></form></li>
    <li><form action="FriendsServlet" method="POST"><input type="submit" class = "notSelected" value="Friends"></form></li>
    <li><form action="redirectPage.jsp" method="POST"><input type="submit" class = "selected" value="Around you"></form></li>
    <li><form action="YourEventsServlet" method="POST"><input type="submit" class = "notSelected" value="Your Events"></form></li>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
  <div class="centered" style="margin-left:30px;">
  
  <h1>Select search range</h1>
  <div style = "width: 50%;">
  <form action = "AroundYouServlet" method = POST id = "form">
  <input type = "hidden" name = "latitude" id = "latitude" value = 0>
  <input type = "hidden" name = "longitude" id = "longitude" value = 0>
  <input type="range" min="<%=mindist %>" max="<%=maxdist%>" value="<%=defrad %>" class="slider" name = "slider" id="slider">
  <p>Maximum distance: <span id="preview"></span>km</p>
  <button type="button" onclick = "getLocation()">Update</button>
  </form>
  
  <!-- Shows preview of the slider -->
  <script>
var slider = document.getElementById("slider");
var output = document.getElementById("preview");

output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
}

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
	  alert("Geolocalitation is not supported by this browser");
  }
}

function showPosition(position) {
	  var lat = document.getElementById("latitude");
	  var lng = document.getElementById("longitude");

	  lat.value = position.coords.latitude;
	  lng.value = position.coords.longitude;
	  
	  document.getElementById("form").submit(); 
}
</script>
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
  	<h6><%=musicEvents.get(i).getDistance() + "km from you" %></h6>
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
  </div>
</body>
</html>