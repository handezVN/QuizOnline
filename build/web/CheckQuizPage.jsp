<%-- 
    Document   : CheckQuizPage
    Created on : Jan 27, 2021, 10:49:44 AM
    Author     : handez
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="DTO.SubjectDTO"%>
<%@page import="DAO.SubjectDao"%>
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
        <a href="UserPage.jsp" > Back</a>
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
            SubjectDao subjectdao = new SubjectDao();
            SubjectDTO subject = subjectdao.getSubjectDTO(subjectid);
            System.out.println(subject.getEmail());
            request.setAttribute("subject", subject);
            int attempt = Integer.parseInt(""+subject.getAttempts());
            if(list.size()<attempt){
                int count = attempt-list.size();
                
                request.setAttribute("attempt", count);
                request.setAttribute("quizstart", "quizstart");
            }
                int time = subject.getTime();
                ArrayList<SubmitDTO> listsb = dao.getSubmitDTOsbyEmailandSubjectID(email, subjectid);
                for(int i=0;i<listsb.size();i++){
                    
                            %>
                            <div class="point">
                                <%=listsb.get(i).getEmail()%>-------<%=listsb.get(i).getPoint()%><br>
                                <%=listsb.get(i).getPoint_current()%>/<%=listsb.get(i).getPoint_total()%>
                                Begin:<%=listsb.get(i).getTime_begin()%>
                                End:<%=listsb.get(i).getTime_end()%>
                                <%
                                  
                                  String myDate = listsb.get(i).getTime_begin();
                                  String myEnd = listsb.get(i).getTime_end();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = sdf.parse(myDate);
                                    Date date1 = sdf.parse(myEnd);
                                    long millis = date.getTime()+time*60*1000;
                                    long millis1 = date1.getTime()+time*60*1000;
                                    long millis_now=System.currentTimeMillis();
                                    System.out.println(millis);
                                    System.out.println(millis_now);
                                    if(millis1-millis==(subject.getTime()*60*1000)){
                                    if(millis>millis_now){
                                        long time_tmp = (millis_now-millis)/1000;
                                        %><a href="ContinueServlet?action=continue&submitid=<%=listsb.get(i).getSubmitid()%>&subjectid=<%=subjectid%>">Continue</a><%
                                    }else{
                                        %>
                                        <a href="PreviewServlet?action=preview&idsubmit=<%=listsb.get(i).getSubmitid()%>&isTeacher=false">Preview</a>
                                        <%
                                    }}else{
                                        %>
                                        <a href="PreviewServlet?action=preview&idsubmit=<%=listsb.get(i).getSubmitid()%>&isTeacher=false">Preview</a>
                                        <%
                                    }
                                %>
                            </div>        
            <%
                }
            %>
       
        
        <br>
        <c:if test="${not empty quizstart}">
            <form action="BeginQuizServlet" method="POST">
                <h1>${subject.name}</h1>
                <p>Time:${subject.time}</p>
                <p>Deadline:${subject.date}</p>
                <p>Attempts:${attempt}</p>    
                <input type="hidden" name="subjectid" value="${subjectid}">
                    <% String mydate =  subject.getDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = sdf.parse(mydate);
                                    long milis = date.getTime();
                                    long milis_now = System.currentTimeMillis();
                                    if(milis-milis_now>0){          
                    %>
                    <c:if test="${not empty subject.password}">
                        <input type="password" value="${param.password}" name="password" placeholder="PassWord!" required="" minlength="3">
                    </c:if>
                        <c:if test="${not empty Errpass}"><p><font style="color: red">${Errpass}</font></p></c:if>
                <input type="submit" value="Submit" onclick="return confirm('Are you sure ?')">
                                    <%}else{
                                    %>Please Contact with Teacher<%
                }%>
            </form>
        </c:if>
    </body>
</html>
