<%-- 
    Document   : newjsp
    Created on : Jan 20, 2021, 1:13:35 PM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div>Closes in <span id="time">05:00</span> minutes!</div>
         <input type="number" id="myText" value="10">
        <script>
            function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            window.location.href = "http://stackoverflow.com";

        }
    }, 1000);
}

window.onload = function () {
     var x = document.getElementById("myText");
  var defaultVal = x.defaultValue;
    var fiveMinutes = defaultVal * 60,
        display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
};
        </script>
    </body>
</html>
