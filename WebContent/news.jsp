<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList, java.util.List, logic.utils.*, logic.bean.NewsBean" %>
    <%
    int i;
    List<NewsBean> nb = (List<NewsBean>) request.getAttribute("news");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
        <!-- Logo icon -->
    <link rel="icon" href="img/concertIcon.png">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
<title>News</title>
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
    <li><form action="ReadNewsServlet" method="POST"><input type="submit" class = "selected" value="News"></form></li>
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
    <h2>News</h2>
            <ul>
            <% 
            for(i = 0; i < nb.size(); i++){
            %>
                <li>
                <hr style = "border: 1;">
                <div>
                <form action = "ButtonHandler" method = POST>
                <img src="<%="img/profilePictures/" + nb.get(i).getProfilePath()%>" height = 50 width = 50 style = "object-fit: cover; border-radius: 300px;">
                <input type="submit" name = "<%="a"%>" class = "btTxt astext" value = "<%= nb.get(i).getBandName()%>">
                <input type = "hidden" name = "artist" value = "<%= nb.get(i).getArtistId() %>">
                <b><span style = "color: rgba(0, 0, 0, 0.5); font-size: 10px;">Posted <%=nb.get(i).getPostedSince() %> ago</span></b>
                </form>
                </div>
                <br>
                <div>
                <p style = "margin-left:30px; word-wrap: break-word; width: 300px;"><%=nb.get(i).getText()%></p>
                <br>
                <%
                if(!nb.get(i).getPicturePath().equals("")){
                %>
                
                <img src="<%="img/newsPictures/" + nb.get(i).getPicturePath()%>" height = 300 width = 300 style = "object-fit: cover; margin-left:30px;">
                
                <br>
                <%} %>
                </div>
                <br>
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