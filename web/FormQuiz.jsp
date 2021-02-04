<%-- 
    Document   : FormQuiz
    Created on : Jan 30, 2021, 8:08:40 PM
    Author     : handez
--%>

<%@page import="DAO.SubjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String subjectid = request.getAttribute("subjectid")+"";
            SubjectDao dao = new SubjectDao();
            request.setAttribute("list", dao.getSubjectDTO(subjectid));
        %>
        <a href="AdminPage.jsp"> Back</a>
        <c:if test="${not empty sbcreate}">
            <form action="CreateSubjectServlet">

                <input type="text" value="" name="name" placeholder="Subject Name" value="${param.name}"  required=""><br>

                <input type="password" value="" name="password" placeholder="Subject Password" value="${param.password}" id="sb-password" > <br>
                <input type="checkbox" onclick="myFunction()">Show Password<br>
                Time:<input type="number" value="${param.time}" name="time" style="width: 30px">minutes<br>
                Attempts:<input type="number" value="${param.attempt}" name="attempt" style="width: 30px"><br>
                <input type="datetime-local" name="date" value="${param.date}">
                
                <select name="Status">
                    <option value="false">Close</option>
                    <option value="true">Open</option>
                </select>
                <input type="submit" value="Create New Subject">
            </form>
        </c:if>
                
        <c:if test="${not empty sbupdate}">
            <form action="UpdateSubjectServlet">
                <input type="hidden" value="${list.id}" name="id">
                <input type="text" name="name" placeholder="Subject Name" value="${list.name}"  required=""><br>

                <input type="password" name="password" placeholder="Subject Password" value="${list.password}" id="sb-password" > <br>
                <input type="checkbox" onclick="myFunction()">Show Password<br>
                Time:<input type="number" value="${list.time}" name="time" style="width: 30px">minutes<br>
                Attempts:<input type="number" value="${list.attempts}" name="attempt" style="width: 30px"><br>
                DeadLine:${list.date}<br>
                <input type="hidden" value="${list.date}" name="date_tmp">
                Update DeadLine:<input type="datetime-local" name="date" value="${list.date}">
                <select name="Status">
                   
                    <option value="false">Close</option>
                    <c:if test="${list.status eq 'true'}">
                        <option value="true" selected="">Open</option>
                    </c:if>
                    <c:if test="${list.status ne 'true'}">
                        <option value="true" >Open</option>
                    </c:if>    
                </select>
                <input type="submit" value="Update Subject">
            </form>
        </c:if>        
    </body>
    
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
</html>
