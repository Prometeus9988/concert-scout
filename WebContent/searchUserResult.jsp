<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, logic.bean.UserBean, logic.utils.*, java.util.List, logic.bean.GeneralUserBean" %>
<!DOCTYPE html>
<% 
    int i;
	GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
	String username = gu.getUsername();	
	
	String searchString = (String) request.getAttribute("searchString");	
	request.setAttribute("searchString", searchString);
	
	List<UserBean> users = (List<UserBean>) request.getAttribute("userList");

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
    <li><form action="BuyTicketServlet" method="POST"><input type="submit" class = "notSelected" value="Home"></form></li>
    <li><form action="ReadNewsServlet" method="POST"><input type="submit" class = "notSelected" value="News"></form></li>
    <li><form action="favorites.jsp" method="POST"><input type="submit" class = "notSelected" value="Favorites"></form></li>
    <li><form action="FriendsServlet" method="POST"><input type="submit" class = "selected" value="Friends"></form></li>
    <li><form action="redirectPage.jsp" method="POST"><input type="submit" class = "notSelected" value="Around you"></form></li>
    <li><form action="YourEventsServlet" method="POST"><input type="submit" class = "notSelected" value="Your Events"></form></li>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>

<div class="splitBackground right">
  <div class="centered" style="margin-left:30px;">

<h1>Search results for "<%=searchString %>"</h1>
   	<h1>Users</h1>
       <ul class = "hs">
    <%
    for(i = 0; i < users.size(); i++){
    	%><li class = "item">

    	<div class="card text-center" style="width: 18rem;">
    <form action="ButtonHandler" method="POST">
  	<button type="submit" name = "<%="f"%>" value = "<%= users.get(i).getUsername() %>" style="color: transparent; background-color: transparent; border-color: transparent;">
  	<img src="<%="img/profilePictures/" + users.get(i).getProfilePicture()%>" class="card-img-top cardImg" height = 215 width = 155 />
	</button>
  	<div class="card-body">
<input type="submit" name = "<%="f"%>" class = "btTxt astext" value = "<%= users.get(i).getUsername() %>">
    <input type = "hidden" name = "name" value = "<%= users.get(i).getName()%>">
    <input type = "hidden" name = "surname" value = "<%= users.get(i).getSurname()%>">
    <input type = "hidden" name = "profileP" value = "<%= users.get(i).getProfilePicture()%>">
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