<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>


<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="userBean" scope="request"
             class="logic.login.UserBean"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="userBean" property="*"/>



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

        <div class="row">
            <div class="col-lg-4 form-group" style = "position: absolute; bottom : 100px;">
                <label for="username" class = "loginText">Username</label>
                <input id="username" name="username" type="text">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 form-group" style = "position: absolute; bottom : 70px;">
                <label for="password" class = "loginText">Password</label>
                <input id="password" name="password" type="password">
            </div>
        </div>
            <div class="col-lg-4 text-center" style = "position: absolute; bottom : 20px;">
                <input name="login" type="submit"
                       id="login" value="login" class="btn btn-info buttonColor" >
                <input name="tipoLogin" type="hidden" value="user"
                       id="tipoLogin" class="btn btn-warning">
            </div>
    </form>
</div>
<div class = "splitBackgroundLogin right">
<%
    if (request.getParameter("login") != null) {
        if (userBean.validate()) {
            // Passa il controllo alla nuova pagina
            session.setAttribute("username", userBean.getUsername());
            %>
            <jsp:forward page="home.jsp"/>
        <%
        } else {
            %>
            <h1 class="text-warning text-center" style="text-color:red;">Data not found</h1>
    <%  }
    } else {%>
        <h1 class="text-info text-center">Login</h1>
        <%
    }
%>
</div>
</div>
</body>
</html>