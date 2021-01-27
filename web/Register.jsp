<%-- 
    Document   : Register
    Created on : Jan 18, 2021, 8:46:30 AM
    Author     : handez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="RegisterServlet">
            <input type="email" name="email" value="${param.email}" required="" placeholder="Email"><br>
            <input type="text" name="name" value="${param.name}" required="" placeholder="Name"><br>
            <input type="password" name="password" value="${param.password}" required="" minlength="8" placeholder="PassWord"><br>
            <input type="password" name="confirm" value="${param.confirm}" required="" minlength="8" placeholder="Confirm"><br>
            <input type="submit" value="Login">
        </form>
            <c:if test="${not empty Err}">
                <Strong style="color: red">${Err}</Strong>
            </c:if>
    </body>
</html>
