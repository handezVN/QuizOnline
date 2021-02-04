/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.SubjectDao;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author handez
 */
@WebServlet(name = "UpdateSubjectServlet", urlPatterns = {"/UpdateSubjectServlet"})
public class UpdateSubjectServlet extends HttpServlet {

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
           String name = request.getParameter("name");
           String password = request.getParameter("password");
            HttpSession session = request.getSession();
           String email = (String) session.getAttribute("email");
           String date_tmp = request.getParameter("date");
           String date1_tmp = request.getParameter("date_tmp");
           
            System.out.println(date_tmp);
           String time_tmp = request.getParameter("time");
           Boolean status = Boolean.parseBoolean(request.getParameter("status"));
           int attempt = Integer.parseInt(request.getParameter("attempt"));
           int time = Integer.parseInt(time_tmp);
           time = Math.abs(time);
           String date = "";
           if(!date_tmp.equals("")){
           String date_tmp1[] = date_tmp.split("T");
            date = date_tmp1[0];
           date = date +" "+date_tmp1[1]+":00";
           }else{
                date = date1_tmp;
           }
            System.out.println(date);
           String id = request.getParameter("id");
            SubjectDao dao = new SubjectDao();
           int result = dao.updateSubject(name, date, password, time, attempt, id,status);
           if(result!=-1){
               request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
           }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateSubjectServlet.class.getName()).log(Level.SEVERE, null, ex);
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
