<%-- 
    Document   : ListPreview
    Created on : Feb 3, 2021, 10:41:48 AM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.SubmitDao"%>
<%@page import="DAO.SubjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .point{
                border: 1px solid;
                padding: 10px;
                
            }
        </style>
    </head>
    <body>
        <% 
        String subjectid = request.getAttribute("subjectid")+"";
        SubmitDao dao = new SubmitDao();
        request.setAttribute("listStudent", dao.getSubmitbySubjectID(subjectid));
        
        %>
        <a href="AdminPage.jsp">Home</a>
        <c:forEach var="submit" items="${listStudent}">
                <div class="point">
                Submit By:${submit.email} ----- Point:${submit.point}<br>
                Time Begin:${submit.time_begin}----- Time End:${submit.time_end}<br>
                ${submit.point_current}/${submit.point_total}
                <a href="PreviewServlet?action=preview&idsubmit=${submit.submitid}&isTeacher=true&subjectid=${subjectid}">Preview</a>
                </div>
        </c:forEach>
    </body>
</html>
