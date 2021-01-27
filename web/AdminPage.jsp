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
            }
        </style>
    </head>
    <body>
        <form action="LogoutServlet">
            <input type="submit" value="Logout">
        </form>
        <h1>Hello World!</h1>
        <a href="ControllerServlet?action=subject-create"> Create Subject</a>
        <br>
        <br>
        <br>
        <br>
        <br>
        <c:if test="${not empty sbcreate}">
            <form action="CreateSubjectServlet">

                <input type="text" value="" name="name" placeholder="Subject Name" value="${param.name}"  required=""><br>

                <input type="password" value="" name="password" placeholder="Subject Password" value="${param.password}" id="sb-password" > <br>
                <input type="checkbox" onclick="myFunction()">Show Password<br>
                Time:<input type="number" value="${param.time}" name="time" >
                <input type="datetime-local" name="date" value="${param.date}">
                <input type="submit" value="Create New Subject">
            </form>
        </c:if>
        <%
            SubjectDao dao = new SubjectDao();
            request.setAttribute("subjectlist", dao.getAllSubject());
            
        %>
        
        <c:forEach var="item" items="${subjectlist}">
            <div class="Subject">
            <a href="ControllerServlet?action=quiz-admin&id=${item.id}"><h1>${item.name}</h1></a> 
            Create by ${item.email}<br>
            DateLine:${item.date}<br>
            Time : ${item.time} Minutes
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
