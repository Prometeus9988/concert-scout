<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,logic.buyticket.*, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String searchString = (String) request.getAttribute("search");

%>
<h1>Search results</h1>
<h1>Music Events</h1>

    <%
    int i;
    List<MusicEventBean> musicEvents = BuyTicketController.getInstance().getSearchMusicEvent(searchString);
    List<ArtistBean> artists = BuyTicketController.getInstance().getSearchArtist(searchString);
    %>
    <ul class = "hs">
    <%
    for(i = 0; i < musicEvents.size(); i++){
    	%><li class = "item"><%=musicEvents.get(i).getName() %></li>
<%
	}
    	%>
    	</ul>
    	<h1>Artists</h1>
    <ul class = "hs">
    <%
    for(i = 0; i < artists.size(); i++){
    	%><li class = "item"><%=artists.get(i).getName() %></li>
<%
	}
    	%>
    	</ul>
</body>
</html>