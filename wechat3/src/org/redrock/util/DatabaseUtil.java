package org.redrock.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class DatabaseUtil {

    private static final String driver = "org.gjt.mm.mysql.Driver";
    //URL指向要访问的数据库名souvc
    private static final String url = "jdbc:mysql://localhost:3306/souvc";
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
}
