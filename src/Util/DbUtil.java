package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private String dbUrl = "jdbc:mysql://localhost:3306/studentinfo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private String dbUserName = "root";
    private String dbPassword = "admin";
    private String jdbcName = "com.mysql.jdbc.Driver";

    //获取数据库连接
    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    //关闭数据库
    public void closeCon(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fault");
        }
    }
}
