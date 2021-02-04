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
    public ArrayList<QuestionDTO> getQuestionDTOsbySearch(String search,String subjectid) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
         ArrayList<QuestionDTO>  list = new ArrayList<>();
         if(cn!=null){
             String sql = "Select * from QuestionTbl where question_content like ? and subjectID=?";
             PreparedStatement pst = cn.prepareStatement(sql);
             pst.setString(1, "%"+search+"%");
             pst.setString(2, subjectid);
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
    public QuestionDTO getQuestionDTObyQuestionID(String questionid) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
         QuestionDTO  questionDTO = null;
         if(cn!=null){
             String sql = "Select * from QuestionTbl where questionid=?";
             PreparedStatement pst = cn.prepareStatement(sql);
             pst.setString(1, questionid);
             ResultSet rs = pst.executeQuery();
             while(rs.next()){
                 questionDTO =new QuestionDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(8),rs.getBoolean(7) );
             }
             cn.close();
         }
         return questionDTO;
    }
    public int updateQuestion(String questionid,String question , String corrent , String answer , int point) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int  result =-1;
        if(cn!=null){
          String sql = "update QuestionTbl set question_content=?,answer_content=?,answer_correct=?,createDate=SYSDATETIME(),point=? where questionid=?";
          PreparedStatement pst = cn.prepareStatement(sql);
          pst.setString(1, question);
          pst.setString(2, answer);
          pst.setString(3, corrent);
          pst.setInt(4, point);
          pst.setString(5, questionid);
          result= pst.executeUpdate();
          cn.close();
        }
        return result;
    }
    public ArrayList<QuestionDTO> getQuestionDTOsbySubjectIDandIndex(String subjectid,int index1 , int index2) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
         ArrayList<QuestionDTO>  list = new ArrayList<>();
         if(cn!=null){
             String sql = "With ab AS (\n" +
"SELECT * , ROW_NUMBER() Over (ORDER BY  questionid) AS RowNum FROM  QuestionTbl where subjectID=? and status=0\n" +
")\n" +
"select * from ab\n" +
"WHERE RowNum >=?\n" +
"AND RowNum <= ?";
             PreparedStatement pst = cn.prepareStatement(sql);
             pst.setString(1, subjectid);
             pst.setInt(2, index1);
             pst.setInt(3, index2);
             ResultSet rs = pst.executeQuery();
             while(rs.next()){
                 list.add(new QuestionDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(8),rs.getBoolean(7) ));
             }
             cn.close();
         }
         return list;
    }
    
    public int RemoveQuestion(String questionid) throws SQLException{
        int result =-1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "update QuestionTbl set status=1 where questionid=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, questionid);
            result= pst.executeUpdate();
            cn.close();
        }
        return result;
    }
}
