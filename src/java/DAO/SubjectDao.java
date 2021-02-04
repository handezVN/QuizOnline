/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SubjectDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author handez
 */
public class SubjectDao {
    public int addNewSubject(String id,String email , String password , String name , String date , int time,int attempt,boolean status) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert SubjectTbl values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(3, email);
            pst.setString(5, password);
            pst.setString(2, name);
            pst.setString(4, date);
            pst.setInt(6, time);
            pst.setInt(7, attempt);
            pst.setBoolean(8, status);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public ArrayList<SubjectDTO> getAllSubject() throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<SubjectDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "Select * from SubjectTbl order by date_exp";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add( new SubjectDTO(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(5),rs.getString(4), rs.getInt(6),rs.getInt(7),rs.getBoolean(8),rs.getBoolean(9)));
            }
            cn.close();
        }
        Collections.reverse(list);
        return list;
    }
    public SubjectDTO getSubjectDTO(String id ) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        SubjectDTO subjectDTO = null;
        if(cn!=null){
            String sql = "Select * from SubjectTbl where subjectID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                subjectDTO=  new SubjectDTO(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(5),rs.getString(4), rs.getInt(6),rs.getInt(7),rs.getBoolean(8));
            }
            cn.close();
        }
        return subjectDTO;
    }
    public int updateSubject(String name, String date, String password, int time , int attempt , String id,boolean status) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "update SubjectTbl set subject_Name=?,date_exp=?,password=?,subject_Time=?,attempt=? ,status=? where subjectID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, date);
            pst.setString(3, password);
            pst.setInt(4, time);
            pst.setInt(5, attempt);
            pst.setString(7, id);
            pst.setBoolean(6, status);
            result= pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public int RemoveSubject(String subjectid) throws SQLException{
        int result =-1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "update SubjectTbl set isDelete=1 where subjectID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, subjectid);
            result= pst.executeUpdate();
            cn.close();
        }
        return result;
    }
}
