/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.QuestionDao;
import DAO.SubmitDao;
import DTO.QuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

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
            QuestionDao dao = new QuestionDao();
            String subjectid = request.getParameter("subjectid");
            String submitid = request.getParameter("submitid");
            ArrayList<QuestionDTO> list = dao.getQuestionDTOsbySubjectID(subjectid);
            String seleted[] = request.getParameterValues("selected");
            SubmitDao submitdao = new SubmitDao();
            float point_total = 0;
            float point_total_current= 0;
           HashMap<String, String> map = new HashMap<String, String>();
            String correct = list.get(1).getCorrect();
            for(int i=0;i<seleted.length;i++){
                String s[] = seleted[i].split("/");
                
                String questionid = s[0];
                String ans = s[1];
                if(map.get(questionid)!=null){
                    String tmp =  map.get(questionid)+"/"+ans;
                    map.replace(questionid, tmp);
                }else
                map.put(questionid, ans);
            }
           
            for (String questionid : map.keySet()) {
            String answer[] = dao.getAnswerCorrectbyQuestionId(questionid).split("/");
            float point_qs = dao.getPointbyQuestionId(questionid);
            String selected = "";
            for(int i=0;i<answer.length;i++){
                boolean check = false;
                String ab[] = map.get(questionid).split("/");
                for(int y=0;y<ab.length;y++){
                    if(Integer.parseInt(ab[y])==i) check = true;
                }
                selected+=check+"/";
            }
            // get answer of student
                String selected_tmp[] = selected.split("/");
                float qs_length = selected_tmp.length;
                float qs_current = qs_length;
                int count_true =0;
                for (int y=0;y<answer.length;y++){
                    
                    if(answer[y].equals("true")) count_true++;
                    
                }
                System.out.println(questionid);
                System.out.println(count_true);
                if(count_true>1){
                    for(int z=0;z<selected_tmp.length;z++){
                    if(!selected_tmp[z].equals(answer[z])) {
                        qs_current--;
                    }
                    
                    }
                    point_total_current += point_qs*(qs_current/qs_length) ;
                    submitdao.InsertDetailSubmit(submitid, questionid, selected, (point_qs*(qs_current/qs_length)));
                }else{
                    if(selected.equals(dao.getAnswerCorrectbyQuestionId(questionid)))
                    {
                        point_total_current+=point_qs;
                        submitdao.InsertDetailSubmit(submitid, questionid, selected, point_qs);
                    }
                        
                    else{
                        point_total_current+=0;
                        submitdao.InsertDetailSubmit(submitid, questionid, selected, 0);
                    }
                }
                
            point_total+=point_qs;
            System.out.println(point_total);
                System.out.println(point_total_current);
                float point = point_total_current*10/point_total;
           
            int result = -1;
            result= submitdao.updateSubmitEnd(submitid, point, point_total, point_total_current);
                System.out.println(selected);
          }
            System.out.println(submitid);
           float point = point_total_current*10/point_total;
            
            int result = -1;
            result= submitdao.updateSubmitEnd(submitid, point, point_total, point_total_current);
            request.setAttribute("submitid", submitid);
            request.getRequestDispatcher("ConfirmPage.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
