<%-- 
    Document   : ConfirmPage
    Created on : Jan 26, 2021, 5:07:10 PM
    Author     : handez
--%>

<%@page import="DAO.SubmitDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="UserPage.jsp" > Back</a>
        <%
            String submitid =(String) request.getAttribute("submitid");
            System.out.println(submitid);
            SubmitDao dao = new SubmitDao();
            request.setAttribute("submit", dao.getSubmitbyID(submitid));
        %>
        <h1>Hello World!</h1>
        ${submit.email} ----- ${submit.point}<br>
        ${submit.time_begin}----- ${submit.time_end}<br>
        ${submit.point_current}/${submit.point_total}
    </body>
</html>
