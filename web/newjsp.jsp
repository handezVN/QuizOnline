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
       <input type="text" id="input_1" value="1" gia_tri="2">
<div class="mt-3" id="kq_input_1">ABC</div>
    <script type="text/javascript">
    var input_1 = document.getElementById("input_1");
    var value_input_1 = input_1.value;
    console.log(value_input_1);
    var gia_tri_input_1 = input_1.getAttribute("gia_tri");
    console.log(gia_tri_input_1);
    var div_input_1 = document.getElementById("kq_input_1");
    div_input_1.innerHTML = value_input_1;
    </script>
    </body>
</html>
