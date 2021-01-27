<%-- 
    Document   : UserPage
    Created on : Jan 19, 2021, 7:11:14 PM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.SubjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            SubjectDao dao = new SubjectDao();
            request.setAttribute("subjectlist", dao.getAllSubject());
            
        %>
        <form method="POST" action="LogoutServlet">
            <input type="submit" value="LogOut">
        </form>
        <c:forEach var="item" items="${subjectlist}">
            <div class="Subject">
            <a href="ControllerServlet?action=quizcheck&id=${item.id}&time=${item.time}"><h1>${item.name}</h1></a> 
            Create by ${item.email}
            DateLine:${item.date}
            Time : ${item.time}
            </div>
        </c:forEach>
        <script>
            function myFunction() {
            var x = document.getElementById("sb-password");
            if (x.type === "password") {
              x.type = "text";
            } else {
              x.type = "password";
            }
          }
        </script>
    </body>
</html>
