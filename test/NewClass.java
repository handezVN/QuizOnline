
import DAO.SubmitDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author handez
 */
public class NewClass {
    public static void main(String[] args) throws SQLException {
        SubmitDao dao = new SubmitDao();
        String result = dao.get123("sm-1", "sb2-2");
        System.out.println(result);
    }
}
