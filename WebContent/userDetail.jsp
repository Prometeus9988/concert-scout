<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="logic.friends.*, logic.bean.UserBean, logic.bean.MusicEventBean, java.util.List" %>

<%
	String fr;
	int i;
	UserBean ub = (UserBean) session.getAttribute("target");
	List <MusicEventBean> targetEvents = (List <MusicEventBean>)session.getAttribute("musicEventList");
	boolean isFriend = (boolean) request.getAttribute("isFriend");
	String who = (String) request.getAttribute("request");
	
	if (!isFriend) {
		if (who.equals("user"))
			fr = "Remove Friend Request";
		else if (who.equals("target"))
			fr = "Accept Friend Request";
		else
			fr = "Add Friend";
	} else {
		fr = "Remove Friend";
	}
%>

<!DOCTYPE html>
<html>
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

<title>User Details</title>
</head>
<body>
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
    <li><form action="FriendsServlet" method="POST"><input type="submit" class = "selected" value="Friends"></form></li>
    <li><form action="redirectPage.jsp" method="POST"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="YourEventsServlet" method="POST"><input type="submit" class = "notSelected" value="Your Events"></form></li>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
<img src = "<%="img/profilePictures/" + ub.getProfilePicture() %>" style = "position:absolute; top:0;" height = 334  width = 1252 style = "object-fit: cover;" >
<div class="centered" style="margin-left:30px; margin-top: 350px;">
<form action = "ButtonHandler" method = "POST">
<input type="submit" class = "submit" name = "back" value = "back">
</form>
	<h1><i><%=ub.getUsername()%></i></h1>
	<h2><i><%=ub.getName() + " " + ub.getSurname() %></i></h2>
  <form action = "FriendButtonServlet" method = "POST">
  <input type = "submit" name = "friend" value = "<%=fr%>">
  <input type = "hidden" name = "target" value = "<%=ub.getUsername() %>">
  <%
  if (who.equals("target")) {
  %>
  <input type = "submit" name = "decline" value = "Decline Friend Request">
  <input type = "hidden" name = "target" value = "<%=ub.getUsername() %>">
  <%
  }
  %>
  </form>
  <h2><i><%=ub.getUsername() + " is going to:" %></i></h2>
      <ul class = "hs">
    <%
    for(i = 0; i < targetEvents.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    	
<form action="ButtonHandler" method="POST">
  	 <button type="submit" name = "<%="m"%>" style="color: transparent; background-color: transparent; border-color: transparent;">
	<img src="<%="img/concertPictures/" + targetEvents.get(i).getCoverPath()%>" height = 50 width = 50 style = "object-fit: cover; border-radius: 300px;" />
	</button>
  	<div class="card-body">
	<input type="submit" name = "<%="m"%>" class = "btTxt astext" value = "<%= targetEvents.get(i).getName() %>">
  	</div>
  	<input type = "hidden" name = "Mevent" value = "<%= targetEvents.get(i).getId() %>">
  	<input type = "hidden" name = "index" value = "<%=i%>">
  	</form>
  	<form action="ButtonHandler" method="POST">
  	<input type="submit" name = "<%="a"%>" class = "btTxt astext" value = "<%= targetEvents.get(i).getBandName() %>">
  	<input type = "hidden" name = "artist" value = "<%= targetEvents.get(i).getArtistId() %>">
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