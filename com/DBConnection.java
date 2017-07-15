package com;

import java.sql.*;
public class DBConnection {
    //声明需要加载的驱动；
    private static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    //声明连接的数据库的URL及数据库名
    private static String DATABASE_URL = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
    //声明数据库用户名
    private static String USER_NAME = "scott";
    //声明数据库中用户名对应的密码
    private static String PASSWORD = "199601";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn){
        if (conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement pstmt){
        if (pstmt!=null){
            try{
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if (rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

