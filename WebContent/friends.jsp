<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, java.util.List, logic.utils.*, logic.bean.UserBean" %>


<% 
    int i;

	List<UserBean> friendList = (List<UserBean>) request.getAttribute("friendList");
	List<UserBean> requestList = (List<UserBean>) request.getAttribute("requestList");
	String foundFriends = (String) request.getAttribute("FoundFriends");
	String foundRequests = (String) request.getAttribute("FoundRequests");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="img/concertIcon.png">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
<title>Friends</title>
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
    <li><form action="FriendsServlet" method="POST"><input type="submit" class = "selected" value="Friends"></form></li>
    <li><form action="redirectPage.jsp" method="POST"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="YourEventsServlet" method="POST"><input type="submit" class = "notSelected" value="Your Events"></form></li>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
<div class="centered" style="margin-left:30px;">
  <form action="SearchUserServlet" method="POST">
  <div class="md-form mt-0">
  <input name = "searchString" class="form-control search" type="text" placeholder="Search user..." aria-label="Search">
  </div>
  </form>
    <!-- Friend list -->
    <h2 class = "h2"><i><%=foundFriends %></i></h2>
    <ul class = "hs">
    <%
    for(i = 0; i < friendList.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    
    <form action="FriendButtonServlet" method="POST">
  	<!-- <img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155> -->
  	  	<button type="submit" name = "<%="f"%>" value = "<%= friendList.get(i).getUsername() %>" style="color: transparent; background-color: transparent; border-color: transparent;">
	<img src="<%="img/profilePictures/" + friendList.get(i).getProfilePicture()%>" class="card-img-top cardImg" height = 215 width = 155 />
	</button>
  	<div class="card-body">
	<input type="submit" name = "<%="f"%>" class = "btTxt astext" value = "<%= friendList.get(i).getUsername() %>">
	<input type = "hidden" name = "name" value = "<%= friendList.get(i).getName()%>">
    <input type = "hidden" name = "surname" value = "<%= friendList.get(i).getSurname()%>">
    <input type = "hidden" name = "profileP" value = "<%= friendList.get(i).getProfilePicture()%>">
  	</div>
  	</form>
</div>

</li>

    		<%
    }
    %>
</ul>
    <!-- Friend requests list -->
    <h2 class = "h2"><i><%=foundRequests %></i></h2>
    <ul class = "hs">
    <%
    for(i = 0; i < requestList.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    
    <form action="FriendButtonServlet" method="POST">
  	<!-- <img class="card-img-top cardImg" src="img/concert.jpg" height = 215 width = 155> -->
  	  	<button type="submit" name = "<%="f"%>" value = "<%= requestList.get(i).getUsername() %>" style="color: transparent; background-color: transparent; border-color: transparent;">
  	<img src="<%="img/profilePictures/" + requestList.get(i).getProfilePicture()%>" height = 50 width = 50 style = "object-fit: cover; border-radius: 300px;">
	</button>
  	<div class="card-body">
	<input type="submit" name = "<%="f"%>" class = "btTxt astext" value = "<%= requestList.get(i).getUsername() %>">
	<input type = "hidden" name = "name" value = "<%= requestList.get(i).getName()%>">
    <input type = "hidden" name = "surname" value = "<%= requestList.get(i).getSurname()%>">
    <input type = "hidden" name = "profileP" value = "<%= requestList.get(i).getProfilePicture()%>">
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