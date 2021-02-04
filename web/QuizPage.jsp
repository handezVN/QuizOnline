<%-- 
    Document   : QuizPage
    Created on : Jan 20, 2021, 12:58:14 PM
    Author     : handez
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DTO.SubmitDTO"%>
<%@page import="DAO.SubmitDao"%>
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
        <style>
            .point{
                padding: 20px;
                border: 1px solid;
                
            }
            .quiz{
                padding: 20px;
                border: 1px solid;
                margin-left: 5%;
                margin-right: 5%;
                
            }
             .logo{
                position: relative;
                left: 95%;
                bottom: 30px;
            }
            .delete{
                position: relative;
                left: 97%;
                bottom: 30px;
            }
            .search{
                text-align:   center;
                
            }
        </style>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
             String subjectid =""+ session.getAttribute("subjectid");
        %>
        
    <c:if test="${user eq 'admin'}">
        <a href="AdminPage.jsp" > Back</a>
        <a href="PreviewServlet?action=admin&id=${subjectid}"><h1>Preview</h1></a>
         <a href="FormQuizServlet?action=quiz-create&id=${subjectid}"><h1> Create new Question</h1></a>   
         <form action="SearchServlet">
             <div class="search">
             <input type="search" name="search" value="${param.search}" placeholder="Search">
             <input type="submit" value="Search">
             </div>
         </form>
            <%  String subjectid1 = request.getAttribute("subjectid")+"";
                
                String search= request.getAttribute("search")+"";
                SubmitDao submitdao = new SubmitDao();
                
                request.setAttribute("listStudent",submitdao.getSubmitbySubjectID(subjectid1));
            %>
            
            
            <%
                
                if(subjectid1!=null){
                QuestionDao dao = new QuestionDao(); 
                ArrayList<QuestionDTO> list = dao.getQuestionDTOsbySubjectID(subjectid1);
                System.out.println(search+"ab");
                if(!search.equals("null")){
                 list = dao.getQuestionDTOsbySearch(search,subjectid);
                }
                
                int count =0;
            for(int x = 0 ; x<list.size();x++){
             String s[] = list.get(x).getAnswer().split("/");
             String c[] = list.get(x).getCorrect().split("/");
             if (s != null) {
                 if(list.get(x).isStatus()==false){
            %>
            
            <ul class="quiz">
                <%=(count+1)%>.<%=list.get(x).getQuestion()%> | Point:<%=list.get(x).getPoint()%> 
                <%  int a =65;
                    for (int i = 0; i < s.length; i++) {
                        
                        char code = (char)a;
                %>
                <li><%=code%>.<%=s[i]%>       <%if(c[i].equals("true")){
                    %><img src="tick.svg" style="width: 15px"><%
                }%> </li>
                
                    <%
                        a++;
                        }
                    %>
                    <a href="FormQuizServlet?action=quiz-update&id=<%=list.get(x).getId()%>"><img src="edit-2.svg" width="20px" class="logo"></a>
                    <a href="FormQuizServlet?action=quiz-delete&id=<%=list.get(x).getId()%>&sbid=${subjectid}"><img src="trash.svg" width="20px" class="delete" onclick="return confirm('Are you sure?')"></a>
            </ul>
            <%  count++;
                }}
            }   
        }
            %>

    </c:if>
    <c:if test="${user eq 'student'}">
        <%  SubmitDao sub = new SubmitDao();
            String submitid = session.getAttribute("submitid")+"";
            SubmitDTO sb = sub.getSubmitbyID(submitid);
            String timeend = sb.getTime_end()+"";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(timeend);
            long millis = date.getTime();
            long millis_now=System.currentTimeMillis(); 
            long time = (millis-millis_now)/1000;
            
        %>
        <c:if test="${not empty submit}"><a href="SubmitServlet?action=submit&submitid=${submitid}" id="myquiz" > Finish</a></c:if>
        
        <form action="TestServlet" method="POST" id="quiz">
            <div>Closes in <span id="time"></span> minutes!</div>
            <input type="hidden" value="<%=time%>" id="abc">
            <input type="hidden" value="${submitid}" name="submitid" >
        <%      int page_current = 1;
                String tmp = "";
                 tmp = request.getAttribute("page")+"";
                 
                if(!tmp.equals("null")){
                   page_current=Integer.parseInt(tmp); 
                }
                
               
                if(subjectid!=null){
                QuestionDao dao = new QuestionDao();
                ArrayList<QuestionDTO> list1 =dao.getQuestionDTOsbySubjectID(subjectid);
                int count = 0;
                for(QuestionDTO qs : list1){
                    if(qs.isStatus()==false) count++;
                }
                int page1 =0 ; 
                if(count%3!=0){
                    page1= (count/3)+1;

                }else{
                    page1 = count/3;
                }
                int index1 = 1+((page_current-1)*3);
                int index2 = 3+((page_current-1)*3);
                ArrayList<QuestionDTO> list = dao.getQuestionDTOsbySubjectIDandIndex(subjectid,index1,index2);
                int count1 =0;
            for(int x = 0 ; x<list.size();x++){
             String s[] = list.get(x).getAnswer().split("/");
             String c[] = list.get(x).getCorrect().split("/");
             String selected = sub.get123(submitid, list.get(x).getId());
             String se[]=null;
             System.out.println(selected);
             
             if (s != null) {
                 if(list.get(x).isStatus()==false){
                 if(selected!=null){
                      se = selected.split("/");
                 }
            %>
            
            <ul>
                 <%=(count1+1+((page_current-1)*3))%>.       <%=list.get(x).getQuestion()%>|Point:<%=list.get(x).getPoint()%>  
                <%  int a =65;
                    for (int i = 0; i < s.length; i++) {
                        
                        char code = (char)a;
                        if(se!=null){
                        if(se[i].equals("true")){
                %> <li><%=code%>.<%=s[i]%><input type="checkbox" name="selected" value="<%=list.get(x).getId()%>/<%=i%>" checked=""></li> <%
                            }else{
                             %> <li><%=code%>.<%=s[i]%><input type="checkbox" name="selected" value="<%=list.get(x).getId()%>/<%=i%>"></li> <%
                }
                        }
                %>

                
                    <%
                        a++;
                        }
                    %>
            </ul>
            <input type="hidden" value="${subjectid}" name="subjectid">
            <input type="hidden" value="<%=page_current%>" name="page_current">
            <%  count1++;
                }}
            }
                if(page_current>=1&&page_current<page1){
            %>         <input type="submit" value="Next Page" name="page" onautocomplete="">   <%
                }
                if(page_current!=1){
%>         %>    <input type="submit" value="Privious Page" name="page" onautocomplete=""><%
        }
            }
            %>
            
                <input type="submit" value="submit" name="submit">
            
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
            document.getElementById('quiz').submit();
           document.getElementById('myquiz').click();
           

        }
    }, 1000);
}

window.onload = function () {
    var x = document.getElementById("abc");
  var defaultVal = x.defaultValue;
    var fiveMinutes = defaultVal,
        display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
};
        </script>    
    </c:if>
        
    </body>
</html>
