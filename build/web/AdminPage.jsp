<%-- 
    Document   : AdminPage
    Created on : Jan 19, 2021, 6:50:00 PM
    Author     : handez
--%>

<%@page import="DAO.SubjectDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .Subject{
                border: solid 1px;
                padding: 10px;
                background: #009966;
                margin: 5%;
            }
            .Subject .edit{
                position: relative;
                bottom: 120px;
                left: 95%;
                width: 50px;
                
            }
            .delete{
                position: relative;
                top: 0%;
                left: 95%;
                width: 20px;
            }
        </style>
    </head>
    <body>
        <form action="LogoutServlet">
            <input type="submit" value="Logout">
        </form>
        <h1>Hello World!</h1>
        <a href="FormQuizServlet?action=subject-create"> Create Subject</a>
        <br>
        <br>
        <br>
        <br>
        <br>
        
        <%
            SubjectDao dao = new SubjectDao();
            request.setAttribute("subjectlist", dao.getAllSubject());
            String admin1 = session.getAttribute("isAdmin")+"";
            if(!admin1.equals("true")){
                response.sendRedirect("Login.jsp");
            }
        %>
        
        <c:forEach var="item" items="${subjectlist}">
            <c:if test="${item.isdelete eq false}">
            <div class="Subject">
                <h1><a href="ControllerServlet?action=quiz-admin&id=${item.id}">${item.name}</a></h1>
            Create by ${item.email}<br>
            DeadLine:${item.date}<br>
            Time : ${item.time} Minutes
            <div class="edit"><a href="FormQuizServlet?action=subject-update&id=${item.id}"><img src="edit.svg" ></a></div>
            <div class="delete"><a href="FormQuizServlet?action=subject-delete&id=${item.id}"><img src="trash.svg" width="50px"></a></div>
            </div>
            </c:if>
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
