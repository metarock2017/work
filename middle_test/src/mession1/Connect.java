package mession1;

import java.sql.*;

public class Connect {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/middle_test?userUnicode=true&characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "";

    private  Connection con;
    private PreparedStatement psql;
    private ResultSet rs;
    public Connection getCon() {

        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry, can't find the Driver!");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
        return con;
    }

    public void closeDB(){
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (psql != null) {
                psql.close();
                psql = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
