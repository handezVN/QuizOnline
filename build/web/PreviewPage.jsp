<%-- 
    Document   : PreviewPage
    Created on : Jan 28, 2021, 12:19:46 PM
    Author     : handez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.DetailSubmitDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.SubmitDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${'true'eq isTeacher}">
        <a href="PreviewServlet?action=admin&isTeacher=true&id=${subjectid}" > Back</a>
        </c:if>
        <c:if test="${isTeacher eq 'false'}">
        <a href="UserPage.jsp" > Back</a>
        </c:if>
        HelloWorld
        <%
            String submitid =(String) request.getAttribute("submitid");
            System.out.println(submitid);
            SubmitDao dao = new SubmitDao();
            ArrayList<DetailSubmitDTO> list = dao.getDetailSubmit(submitid);
            
        %>
        <img src="tick.svg" style="width: 15px">
        <%
            for(int i=0;i<list.size();i++){
            String a[] = list.get(i).getAnswer_content().split("/");
            String b[] = list.get(i).getAnswer_correct().split("/");
            String c[] = list.get(i).getAnswer_selected().split("/");
        
        %>
                <ul><%=(i+1)%>.<%=list.get(i).getQuestion_content()%><%=list.get(i).getPoint_get()%>/<%=list.get(i).getPoint()%>
                    <%  int d = 65;
                        for(int x=0;x<a.length;x++){
                            char code =(char)d;
                    %>
                    <li><%if(c[x].equals("true")){%>
                        <input type="checkbox" name="selected" value="" checked="" disabled="">
                        <%
                        }else{
                        %><input type="checkbox" name="selected" value="" disabled=""><%    
                        }%>
                        <%=code%>.<%=a[x]%>   
                        <%if(b[x].equals("true")&&c[x].equals(b[x])){
                            %><img src="tick.svg" style="width: 15px"><%
                            }else if(c[x].equals("true")&&!c[x].equals(b[x])){
                            %> <img src="https://upload.wikimedia.org/wikipedia/commons/c/c6/False.svg" style="width: 15px">     <%
                            }
                        %> </li>
                    <%  d++;
                        }
                    %>
                    The correct answer is: <%
                        for(int y=0;y<b.length;y++){
                            if(b[y].equals("true")){
                                %><br>+<%=a[y]%><%
                            }
                        }
                    %>
                </ul>
        <%
            }
        %>
    </body>
</html>
