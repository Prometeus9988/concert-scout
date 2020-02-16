<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.GeneralUserBean" %>
<%
String resString = "Post a news";

String res = (String) request.getAttribute("result");
if(res != null){
	if (res.equals("added")) {
		resString = "News posted";
	} else {
		resString = "Failed to post the news";
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>News</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Logo icon -->
    <link rel="icon" href="img/concertIcon.png">

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="./css/style.css" rel="stylesheet" type="text/css">

</head>
<body class = "defaultBackgorund">
<div class="container">

 <div class="splitBanner left">
  <div class="centered">
    
<%
GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
%>
  <!-- Logo -->
  <div class = "logoPosition">
  <img class = "logoImg" src="img/concertIcon.png" height = 106.8 width = 106.8>
  <div class = "row">
  <p class = "logoText">LIVE<mark style = "background-color: rgba(0,0,0,0); color: #6A4C93">the</mark>MUSIC</p>
  </div>
  </div>
  
    <ul>
    <li><form action="artistHome.jsp" method="POST"><input type="submit" class = "notSelected" value="Add Music Event"></form></li>
    <li><form action="addNews.jsp" method="POST"><input type="submit" class = "selected" value="Add News"></form></li>
    <li><form action="LogoutServlet" method="POST"><input type="submit" class = "notSelected" value="Logout"></form></li>
    </ul>
  </div>
</div>
<div class="splitBackground right">
  <div class="centered">
 <h1><i>Welcome <%= gu.getUsername() %></i></h1>
<form action = "AddNewsServlet" method = "POST" enctype='multipart/form-data'>
  <div class="form-group col-md-3 col-md-offset-3" style = "width:500px; border-width:2px; border-style:solid; border-color:#b0b0b0; border-radius: 10px;">
    <div class = "form.group"><br>
    <label><i><%=resString %></i></label><br>

    <!-- News text area -->
    <div class="input-group">
  		<textarea class="form-control" aria-label="With textarea" name="text" style="width:450px;"></textarea>
	</div>
	
	<label for="avatar"><i>Choose a picture(optional):</i></label>
	<input type="file" id="file" name="file" accept="image/png, image/jpeg"><br>
    <input name="add" type="submit" value="Post News" class="btn btn-info buttonColor"><br>
    
</div>
</div>
</form>
</div>
</div>
</div>
</body>
</html>
  