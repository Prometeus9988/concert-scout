<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, logic.bean.*, logic.buyticket.*, java.util.List,logic.bean.GeneralUserBean" %>
<!DOCTYPE html>
<% 
    int i;
	GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
	String username = gu.getUsername();	
	
	String searchString = (String) session.getAttribute("search");	
	BuyTicketController btc=BuyTicketController.getInstance();
    List<MusicEventBean> musicEvents = btc.getSearchMusicEvent(searchString);
    List<ArtistBean> artists = btc.getSearchArtist(searchString);
	
    for(i = 0; i < musicEvents.size(); i++){
		if(request.getParameter("m" + i)!=null){
			session.setAttribute("Mevent",musicEvents.get(i));
			%>
			<jsp:forward page="musicEventDetail.jsp"/>
			<%
		}
		if(request.getParameter("ar" + i)!=null){
			List<ArtistBean> sArt=btc.getSearchArtist(musicEvents.get(i).getArtistId());
			request.setAttribute("artist",sArt.get(0));
			%>
			<jsp:forward page="artistDetail.jsp"/>
			<%
		}
	}
	
	for(i = 0; i < artists.size(); i++){
		if(request.getParameter("a" + i)!=null){
			request.setAttribute("artist",artists.get(i));
			%>
			<jsp:forward page="artistDetail.jsp"/>
			<%
		}
	}
%> 
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Results</title>
<link rel="icon" href="img/concertIcon.png">
 <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Logo icon -->
    <link rel="icon" href="img/concertIcon.png">
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

    <link href="./css/style.css" rel="stylesheet" type="text/css">

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
    <li><form action="home.jsp" method="GET"><input type="submit" class = "notSelected" value="Home"></form></li>
    <li><form action="news.jsp" method="GET"><input type="submit" class = "notSelected" value="News"></form></li>
    <li><form action="favorites.jsp" method="GET"><input type="submit" class = "notSelected" value="Favorites"></form></li>
    <li><form action="friends.jsp" method="GET"><input type="submit" class = "notSelected" value="Friends"></form></li>
    <li><form action="aroundyou.jsp" method="GET"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="myevents.jsp" method="GET"><input type="submit" class = "notSelected" value="My Events"></form></li>
    <li><form action="index.jsp" method="GET"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
  <div class="centered" style="margin-left:30px;">

<h1>Search results for "<%=searchString %>"</h1>
<h1>Music Events</h1>

    <ul class = "hs">
    <%
    for(i = 0; i < musicEvents.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    	
    <form action="searchResult.jsp" method="POST">
  	<!-- <img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155> -->
  	<input type="image" name = "<%="m" + i%>" src="img/concert.jpg" class="btTxt card-img-top cardImg submit" height = 215 width = 155 alt="Submit Form"/>
  	<div class="card-body">
	<input type="submit" name = "<%="m" + i%>" class = "btTxt astext" value = "<%= musicEvents.get(i).getName() %>">
  	<br>
  	<input type="submit" name = "<%="ar" + i%>" class = "btTxt astext" value = "<%= musicEvents.get(i).getArtistId() %>">
  </div>
  </form>
</div>

</li>
    		<%
    }
    %>
</ul>
    	<h1>Artists</h1>
       <ul class = "hs">
    <%
    for(i = 0; i < artists.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    <form action="searchResult.jsp" method="POST">
  	<img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155 alt="Submit">
  	<div class="card-body">
    <input type="submit" name = "<%="a" + i%>" class = "btTxt astext" value = "<%= artists.get(i).getBandName() %>">
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