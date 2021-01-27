/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.QuestionDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author handez
 */
public class QuestionDao {
    public int AddQuestion(String id , String question , String anwser , String correct , String subjectid,int point) throws SQLException{
        int result =-1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = " insert QuestionTbl values(?,?,?,?,SYSDATETIME(),?,1,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, question);
            pst.setString(3, anwser);
            pst.setString(4, correct);
            pst.setString(5, subjectid);
            pst.setInt(6, point);
            result=pst.executeUpdate();
            cn.close();
        }
        return  result;
    }
    public ArrayList<QuestionDTO> getQuestionDTOsbySubjectID(String subjectid) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
         ArrayList<QuestionDTO>  list = new ArrayList<>();
         if(cn!=null){
             String sql = "Select * from QuestionTbl where subjectID=?";
             PreparedStatement pst = cn.prepareStatement(sql);
             pst.setString(1, subjectid);
             ResultSet rs = pst.executeQuery();
             while(rs.next()){
                 list.add(new QuestionDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(8),rs.getBoolean(7) ));
             }
             cn.close();
         }
         return list;
    }
    public String getAnswerCorrectbyQuestionId(String questionID) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        String result = "";
        if(cn!=null){
            String sql= "select answer_correct from QuestionTbl where questionid=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, questionID);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
            cn.close();
        }
        return result;
    }
    public float getPointbyQuestionId(String questionID) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        float result = 0;
        if(cn!=null){
            String sql= "select point from QuestionTbl where questionid=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, questionID);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getFloat(1);
            }
            cn.close();
        }
        return result;
    }
    
}
