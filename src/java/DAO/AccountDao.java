/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AccountDTO;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author handez
 */
public class AccountDao {
    public String CheckLogin(String email) throws SQLException{
        String password = "";
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "select * from UserTbl where email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                password = rs.getString(3);
            }
            cn.close();
        }
        return password;
    }
    public AccountDTO getAccountDTO(String email) throws SQLException{
        AccountDTO accountDTO = null;
        Connection cn = MyConnection.MakeConnection();
        if(cn!=null){
            String sql = "select * from UserTbl where email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                accountDTO = new AccountDTO(rs.getString(1), rs.getString(3), rs.getString(2),rs.getString(4), rs.getString(5),rs.getBoolean(6));
            }
            cn.close();
        }
        return accountDTO;
    }
    public int addNewAccount(String email , String password , String name) throws SQLException{
        Connection cn = MyConnection.MakeConnection();
        int result = -1;
        if(cn!=null){
            String sql = "insert UserTbl values(?,?,?,'Student','New',0)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, name);
            pst.setString(3, password);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
   
}
