/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.AccountDao;
import DTO.AccountDTO;
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
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author handez
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            AccountDao dao = new AccountDao();
   
           String password_check = dao.CheckLogin(email);
            if(password_check!=null){
            boolean check = passwordEncryptor.checkPassword(password, password_check);
            
                if(check){
                    AccountDTO accountDTO = dao.getAccountDTO(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("email", accountDTO.getEmail());
                    session.setAttribute("name", accountDTO.getName());
                    session.setAttribute("isAdmin", accountDTO.isIsAdmin());
                    if(accountDTO.isIsAdmin()){
                       request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
                    }
                    else request.getRequestDispatcher("UserPage.jsp").forward(request, response);
                    
                     request.getRequestDispatcher("Login.jsp").forward(request, response);
                }else{
                    request.setAttribute("Err", "Password Is Wrong");
                     request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("Err", "This email does not exist");
                     request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
