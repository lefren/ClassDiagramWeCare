package OOP.JFRAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authentication {
//    private String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
//    private String USERNAME = "root";
//    private String PASS = "";
//    public boolean authentication(String nik, String password){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASS);
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE nik=? AND password=?");
//            ps.setString(1, nik);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
}
