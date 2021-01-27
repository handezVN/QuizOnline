<%-- 
    Document   : CheckQuizPage
    Created on : Jan 27, 2021, 10:49:44 AM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.SubmitDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.SubmitDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .point{
                padding: 20px;
                border: 1px solid;
                
            }
        </style>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            String subjectid =(String) request.getAttribute("subjectid");
            String email =(String) session.getAttribute("email");
            System.out.println(email);
            SubmitDao dao = new SubmitDao();
            ArrayList<SubmitDTO> list = dao.getSubmitDTOsbyEmailandSubjectID(email, subjectid);
            if(list!=null){
                request.setAttribute("listsubmit", list);
            }else{
                request.setAttribute("listsubmit", "");
            }
            System.out.println(list.size());
        %>
        <c:if test="${not empty listsubmit}">
            <c:forEach var="submit" items="${listsubmit}">
                <div class="point">
                 ${submit.email} ----- ${submit.point}<br>
                ${submit.time_begin}----- ${submit.time_end}<br>
                ${submit.point_current}/${submit.point_total}
                </div>
            </c:forEach>
        </c:if>
    </body>
</html>
