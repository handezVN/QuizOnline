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

/**
 *
 * @author handez
 */
public class SubjectDao {
    public int addNewSubject(String id,String email , String password , String name , String date , int time) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert SubjectTbl values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(3, email);
            pst.setString(5, password);
            pst.setString(2, name);
            pst.setString(4, date);
            pst.setInt(6, time);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public ArrayList<SubjectDTO> getAllSubject() throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<SubjectDTO> list = new ArrayList<>();
        if(cn!=null){
            String sql = "Select * from SubjectTbl";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add( new SubjectDTO(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(5),rs.getString(4), rs.getInt(6)));
            }
            cn.close();
        }
        return list;
    }
}
