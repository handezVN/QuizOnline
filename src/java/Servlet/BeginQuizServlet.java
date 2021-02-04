/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.QuestionDao;
import DAO.SubjectDao;
import DAO.SubmitDao;
import DTO.QuestionDTO;
import DTO.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author handez
 */
@WebServlet(name = "BeginQuizServlet", urlPatterns = {"/BeginQuizServlet"})
public class BeginQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                   SubmitDao dao = new SubmitDao();
                   SubjectDao subjectdao = new SubjectDao();
                   QuestionDao questiondao = new QuestionDao();
                   HttpSession session = request.getSession();
                   String subjectid = request.getParameter("subjectid");
                   String password = "";
                    password = request.getParameter("password");
                    if(password==null){
                        password="";
                    }
                   System.out.println(password);
                   SubjectDTO subjectDTO = subjectdao.getSubjectDTO(subjectid);
                   String sb_password = subjectDTO.getPassword();
                   System.out.println(subjectDTO);
                   System.out.println("ab"+sb_password);
                   if(password==sb_password||password.equals(sb_password)){
                       String email =(String) session.getAttribute("email");
                   String submitid = "sm-"+ (dao.getSubmit().size()+1); 
                   // insert SubmitTbl
                   long millis_begin=System.currentTimeMillis(); 
                   long millis_end=millis_begin+subjectDTO.getTime()*60*1000; 
                   DateFormat simple = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
                   Date result = new Date(millis_begin);
                   Date result1 = new Date(millis_end);
                   String date_begin = simple.format(result); 
                   String date_end = simple.format(result1); 
                   dao.insertSubmitbegin(submitid, email, subjectid,date_begin,date_end);
                   
                   
                   // insert DetailSubmitTbl
                   ArrayList<QuestionDTO> list = questiondao.getQuestionDTOsbySubjectID(subjectid);
                   for(int i=0;i<list.size();i++){
                    if(list.get(i).isStatus()==false){   
                    String count[] = list.get(i).getAnswer().split("/");
                    String select = "";
                    for(int y=0;y<count.length;y++){
                        select+="false/";
                    }
                    dao.InsertDetailSubmit(submitid, list.get(i).getId(), select, 0,list.get(i).getCorrect(),list.get(i).getAnswer(),list.get(i).getPoint(),list.get(i).getQuestion());
                    }}
                   
                   session.setAttribute("submitid", submitid);
                   session.setAttribute("subjectid", subjectDTO.getId());
                   session.setAttribute("time", subjectDTO.getTime()*60);
                   session.setAttribute("user", "student");
                   response.sendRedirect("QuizPage.jsp");
                   }else{
                        request.setAttribute("Errpass", "Password is incorrect!");
                        request.setAttribute("subjectid", request.getParameter("subjectid"));
                        request.getRequestDispatcher("CheckQuizPage.jsp").forward(request, response);
                   }
                   
        } catch (SQLException ex) {
            Logger.getLogger(BeginQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
