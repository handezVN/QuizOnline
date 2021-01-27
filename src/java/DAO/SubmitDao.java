/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SubmitDTO;
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
public class SubmitDao {
    public int insertSubmitbegin(String id, String email , String subjectid) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert SubmitTbl values(?,SYSDATETIME(),'',?,?,'','','')";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(3, email);
            pst.setString(2, subjectid);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public int updateSubmitEnd(String id , float point , float totalpoint , float currentpoint) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "Update SubmitTbl set time_end=SYSDATETIME(),point = ? , totalpoint = ? , currentpoint=? where submitID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setFloat(1, point);
            pst.setFloat(2, totalpoint);
            pst.setFloat(3, currentpoint);
            pst.setString(4, id);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public ArrayList<String> getSubmit() throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        ArrayList<String> list = new ArrayList<>();
        if(cn!=null){
            String sql = "select submitID from SubmitTbl";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }
            cn.close();
        }
        return list;
    }
    public int InsertDetailSubmit(String id , String questionid, String anwser , float point) throws SQLException{
        int result = -1;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "insert DetailSubmitTbl values(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, anwser);
            pst.setString(3, questionid);
            pst.setFloat(4, point);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public SubmitDTO getSubmitbyID(String id) throws SQLException{
        SubmitDTO submitdto = null;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "select * from SubmitTbl";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                submitdto = new SubmitDTO(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getString(5), rs.getFloat(6), rs.getFloat(7),rs.getFloat(8));
            }
            cn.close();
        }
        return  submitdto;
    }
    public ArrayList<SubmitDTO> getSubmitDTOsbyEmailandSubjectID(String email, String subjectid) throws SQLException{
        
        ArrayList<SubmitDTO> list = new ArrayList<>();
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "select * from SubmitTbl where subjectid = ? and email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, subjectid);
            pst.setString(2, email);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new SubmitDTO(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getString(5), rs.getFloat(6), rs.getFloat(7),rs.getFloat(8)));
            }
            cn.close();
        }
        return  list;
    }
}
