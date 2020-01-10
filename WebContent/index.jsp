<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>


<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="generalUserBean" scope="request"
             class="logic.login.GeneralUserBean"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="generalUserBean" property="*"/>

<%@ page import="logic.login.*" %>

<!-- HTML 5 -->
<!DOCTYPE html>
<html>
<!-- Container tag for title, style, meta-information, linked resources or scripts -->
<head>
    <!-- Browser title bar, favorites, name for search engines -->
    <title>Login page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class = "defaultBackgorund">
<div class="container">
<div class = "splitBannerLogin left">
    <form action="index.jsp" name="myform" method="POST">

    <div class="form-group" style = "position: absolute; bottom: 10px; left: 15px;">
    <div>
                <label for="username" class = "loginText">Username</label><br>
                <input id="username" name="username" type="text" class = "logTextBox">
                </div>
                <br>
                <div>
                <label for="password" class = "loginText">Password</label><br>
                <input id="password" name="password" type="password" class = "logTextBox">
  </div>
  <br>
              <div>
                <input name="login" type="submit"
                       id="login" value="login" class="btn btn-info buttonColor" >
                <input name="tipoLogin" type="hidden" value="user"
                       id="tipoLogin" class="btn btn-warning">
            </div>
            </div>
    </form>
    
</div>
<div class = "splitBackgroundLogin right">
<%
	LoginController controller = LoginController.getInstance();
    if (request.getParameter("login") != null) {
    	GeneralUserBean gu = controller.login(generalUserBean);
        if (gu == null) {
        	  %>
              <h1 class="text-warning text-center" style="text-color:red;">Data not found</h1>
      <% 
        } else if(gu.getRole().equals("artist")){
        	session.setAttribute("username", generalUserBean.getUsername());
        	%>
        	<jsp:forward page="artistHome.jsp"/>
        	<%
        } else if (gu.getRole().equals("user")) {
            session.setAttribute("username", generalUserBean.getUsername());
            %>
            <jsp:forward page="home.jsp"/>
        <% }
    } else {%>
        <h1 class="text-info text-center">Login</h1>
        <%
    }
%>
</div>
</div>
</body>
</html>