<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action = "AroundYouServlet" method = POST id = "form">
<input type = "hidden" id = "latitude" name = "latitude" value = 0>
<input type = "hidden" id = "longitude" name = "longitude" value = 0>
</form>

<button onclick = "getLocation()" id = "start"></button>

<script>
window.onload = function(){
    var button = document.getElementById("start");
    button.click();
}

var x = document.getElementById("demo");

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
	  var lat = document.getElementById("latitude");
	  var lng = document.getElementById("longitude");
	  
	  lat.value = position.coords.latitude;
	  lng.value = position.coords.longitude;
	  
	  document.getElementById("form").submit(); 
	}
</script>

</body>
</html>