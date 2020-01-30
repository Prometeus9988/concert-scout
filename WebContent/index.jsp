<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>


<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="generalUserBean" scope="request"
             class="logic.login.GeneralUserBean"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="generalUserBean" property="*"/>

<%@ page import="logic.login.*, logic.buyticket.ArtistBean" %>
<%
String regMessage = "Register";
if (request.getParameter("register") != null){
	Boolean regResult = false;
	String email = request.getParameter("createEmail");
	String username = request.getParameter("createUsername");
	String password = request.getParameter("createPassword");
	String userType = request.getParameter("userType");
	
	if(userType.equals("User")){
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		UserBean u = new UserBean(username, password, firstName, lastName, email);
		regResult = LoginController.getInstance().createUser(u);
	} else if(userType.equals("Artist")){
		String bandName = request.getParameter("bandName");	
		ArtistBean a = new ArtistBean(username, password, bandName, "", email);
		regResult = LoginController.getInstance().createArtist(a);
	}
	if(regResult == true){
		regMessage = "Registration Successfull";
	} else {
		regMessage = "Username already taken";
	}
}
%>
<!-- HTML 5 -->
<!DOCTYPE html>
<html>
<!-- Container tag for title, style, meta-information, linked resources or scripts -->
<head>
<!-- Logo icon -->
    <link rel="icon" href="img/concertIcon.png">
    <!-- Browser title bar, favorites, name for search engines -->
    <title>Login page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script>
	function select(){
		
		var mySelect = document.getElementById('userType');
		var selected = mySelect.options[mySelect.selectedIndex].text;
		if(selected == "User"){
			document.getElementById("bandName").disabled = true;
			document.getElementById("firstName").disabled = false;
			document.getElementById("lastName").disabled = false;
		} else {
			document.getElementById("bandName").disabled = false;
			document.getElementById("firstName").disabled = true;
			document.getElementById("lastName").disabled = true;
		}
		
	}
	</script>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class = "defaultBackgorund">
<div class="container">
<div class = "splitBannerLogin left">
  <!-- Logo -->
  <div class = "logoPosition">
  <img class = "logoImg" src="img/concertIcon.png" height = 106.8 width = 106.8>
  <div class = "row">
  <p class = "logoText">LIVE<mark style = "background-color: rgba(0,0,0,0); color: #6A4C93">the</mark>MUSIC</p>
  </div>
  </div>
  <div class ="col-sm-5" style = "padding-top:170px;">
  <%

    LoginController controller = new LoginController();
  	if (request.getParameter("login") != null) {
    	GeneralUserBean gu = controller.login(generalUserBean);
        if (gu == null) {
        	  %>
              <h6 style="color:red;">Wrong username or password</h6>
      <% 
        } else if(gu.getRole().equals("artist")){
            session.setAttribute("user", generalUserBean);
        	%>
        	<jsp:forward page="artistHome.jsp"/>
        	<%
        } else if (gu.getRole().equals("user")) {
            session.setAttribute("user", generalUserBean);
            %>
            <jsp:forward page="home.jsp"/>
        <% }
    }
%>
</div>
  
  <!-- Login Form -->
    <form action="index.jsp" name="myform" method="POST">

    <div class="form-group" style = "position: absolute; bottom: 10px; left: 15px;">
    <div>
                <label for="username" class = "loginText">Username</label><br>
                <input id="username" name="username" type="text" class = "form-control logTextBox form-control-sm">
                </div>
                <br>
                <div>
                <label for="password" class = "loginText">Password</label><br>
                <input id="password" name="password" type="password" class = "form-control logTextBox form-control-sm">
  </div>
  <br>
              <div>
                <input name="login" type="submit"
                       id="login" value="login" class="btn btn-info buttonColor" >
            </div>
            </div>
    </form>
</div>
<div class = "splitBackgroundLogin right">

<!-- Registration form -->
<form action = "index.jsp" method = "POST">
  <div class="form-group col-md-3 col-md-offset-3" style = "width:500px; border-width:2px; border-style:solid; border-color:#b0b0b0; border-radius: 10px;">
    <div class = "form.group"><br>
    <label><%=regMessage %></label><br>
    <input type="email" class="form-control" name="createEmail" placeholder="Email"><br>
    <input type="text" class="form-control" name="createUsername" placeholder="Username"><br>
    <input type="password" class="form-control" name="createPassword" placeholder="Password"><br>
	<input type="text" class="form-control" name="bandName" id = "bandName" placeholder="Band Name" disabled><br>
	<input type="text" class="form-control" name="firstName" id = "firstName" placeholder="First Name"><br>
	<input type="text" class="form-control" name="lastName" id = "lastName" placeholder="Last Name"><br>
</div>
  <!-- Select type of user -->
	<div>
    <label>Type of user</label>
    <select class="form-control" name="userType" id="userType" onchange="select()">
      <option>User</option>
      <option>Artist</option>
    </select>
  </div>
  <br>
  <input name="register" type="submit" id="register" value="Register" class="btn btn-info buttonColor" >
  </div>
</form>
</div>
</div>
</body>
</html>