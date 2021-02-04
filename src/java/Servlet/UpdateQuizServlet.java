/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.QuestionDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author handez
 */
@WebServlet(name = "UpdateQuizServlet", urlPatterns = {"/UpdateQuizServlet"})
public class UpdateQuizServlet extends HttpServlet {

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
            String questionid = request.getParameter("questionid");
            String subjectid = request.getParameter("subjectid");
            String point_tmp = request.getParameter("point");
           String question = request.getParameter("question");
           String answer_content_tmp[] = request.getParameterValues("mytext[]");
           String answer_correct[] = request.getParameterValues("selected");
           String answer ="";
           String correct ="";
           System.out.println("Point"+point_tmp);
            System.out.println("Question"+question);
            
            for(int i=0;i<answer_content_tmp.length;i++){
                Boolean check = false;
                answer+=answer_content_tmp[i]+"/";
                for(int y=0;y<answer_correct.length;y++){
                    
                    if(Integer.parseInt(answer_correct[y])==i) check =true;
                }
                correct += check+"/";
            }
            int point = Integer.parseInt(point_tmp);
            QuestionDao dao = new QuestionDao();
            int result = dao.updateQuestion(questionid, question, correct, answer, point);
            if(result!=-1){
                request.setAttribute("subjectid", subjectid);
                request.setAttribute("user", "admin");
                request.getRequestDispatcher("QuizPage.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
