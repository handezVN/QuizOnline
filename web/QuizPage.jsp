<%-- 
    Document   : QuizPage
    Created on : Jan 20, 2021, 12:58:14 PM
    Author     : handez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.QuestionDTO"%>
<%@page import="DAO.QuestionDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
             String subjectid =""+ request.getAttribute("subjectid");
        %>
        
    <c:if test="${user eq 'admin'}">
        <a href="AdminPage.jsp" > Back</a>
         <a href="ControllerServlet?action=quiz-create&id=${subjectid}"><h1> Create new Question</h1></a>   
         
            <%
                
                if(subjectid!=null){
                QuestionDao dao = new QuestionDao();
                ArrayList<QuestionDTO> list = dao.getQuestionDTOsbySubjectID(subjectid);
                
            for(int x = 0 ; x<list.size();x++){
             String s[] = list.get(x).getAnswer().split("/");
             String c[] = list.get(x).getCorrect().split("/");
             if (s != null) {
                 
            %>
            
            <ul>
                Point:<%=list.get(x).getPoint()%>   |<%=(x+1)%>.<%=list.get(x).getQuestion()%>
                <%  int a =65;
                    for (int i = 0; i < s.length; i++) {
                        
                        char code = (char)a;
                %>
                    <li><%=code%>.<%=s[i]%><input type="checkbox" name="selected" value="<%=list.get(x).getId()%>/<%=i%>"><%=c[i]%> </li>
                
                    <%
                        a++;
                        }
                    %>
            </ul>
            <%
                }
            }   
        }
            %>

    </c:if>
    <c:if test="${user eq 'student'}">
        <a href="UserPage.jsp" > Back</a>
        <form action="TestServlet" method="POST" id="myquiz">
            <div>Closes in <span id="time"></span> minutes!</div>
            <input type="hidden" value="${time}" id="abc">
            <input type="hidden" value="${submitid}" name="submitid" >
        <%
                
                if(subjectid!=null){
                QuestionDao dao = new QuestionDao();
                ArrayList<QuestionDTO> list = dao.getQuestionDTOsbySubjectID(subjectid);
                
            for(int x = 0 ; x<list.size();x++){
             String s[] = list.get(x).getAnswer().split("/");
             String c[] = list.get(x).getCorrect().split("/");
             if (s != null) {
                 
            %>
            
            <ul>
                Point:<%=list.get(x).getPoint()%>   |<%=(x+1)%>.<%=list.get(x).getQuestion()%>
                <%  int a =65;
                    for (int i = 0; i < s.length; i++) {
                        
                        char code = (char)a;
                %>
                    <li><%=code%>.<%=s[i]%><input type="checkbox" name="selected" value="<%=list.get(x).getId()%>/<%=i%>"></li>
                
                    <%
                        a++;
                        }
                    %>
            </ul>
            <%
                }
            }   
        }
            %>
            <input type="hidden" value="${subjectid}" name="subjectid">
            <input type="submit" value="Submit" onautocomplete="">
        </form>   
            
         
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
           document.getElementById('myquiz').submit();

        }
    }, 1000);
}

window.onload = function () {
    var x = document.getElementById("abc");
  var defaultVal = x.defaultValue;
    var fiveMinutes = defaultVal*60,
        display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
};
        </script>    
    </c:if>
        
    </body>
</html>
