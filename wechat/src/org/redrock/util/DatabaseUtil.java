package org.redrock.util;

import java.sql.*;
import java.sql.PreparedStatement;
public class DatabaseUtil {

    private static final String driver = "org.gjt.mm.mysql.Driver";
    //URL指向要访问的数据库名souvc
    private static final String url = "jdbc:mysql://localhost:3306/wodi?useUnicode=true&amp;characterEncoding=utf8";
    //MySQL配置时的用户名
    private static final String user = "root";
    //MySQL配置时的密码
    private static String password = "";

    private static Connection conn;

    static {
        try {
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不能close
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }

    public static PreparedStatement preparedStmt(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public static ResultSet executeQuery(PreparedStatement pstmt) {
        ResultSet rs = null;
        try{
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int executeUpdate(PreparedStatement pstmt) {
        int ret = 0;
        try{

            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try{
                conn.close();
                conn = null;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try{
                rs.close();
                rs = null;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
