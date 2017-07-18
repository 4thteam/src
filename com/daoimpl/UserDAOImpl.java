package com.daoimpl;

import com.DBConnection;
import com.dao.UserDAO;
import com.entity.User;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO实现类
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserByUseridAndUser_passwod(String user_id, String user_password) {
        Connection conn = DBConnection.getConnection();
        String sql = "{call getUser(?,?,?)}";
        CallableStatement cstmt = null;
        User user = new User();
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, Integer.parseInt(user_id));
            cstmt.setInt(2, Integer.parseInt(user_password));
            cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(3);
            if (rs != null) {
                if (rs.next()) {
                    user.setUser_id(rs.getString(1));
                    user.setUser_name(rs.getString(2));
                    user.setUser_sex(rs.getString(3));
                    user.setUser_age(rs.getString(4));
                    user.setUser_idcard(rs.getString(5));
                    user.setUser_phone(rs.getString(6));
                    user.setUser_password(rs.getString(7));
                    user.setUser_status(rs.getString(8));
                    user.setUser_role(rs.getString(9));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return user;
    }

    //上线记录
    public void SignIn(String user_id, String net_id, String sign_ip, String sign_time) {

        String sql = "{call SIGN_IN(?,?,?,?)}";
        CallableStatement cstmt = null;
        Connection conn = DBConnection.getConnection();
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, user_id);
            cstmt.setString(2, sign_time);
            cstmt.setString(3, net_id);
            cstmt.setString(4, sign_ip);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
    }

    //下线记录
    public void Out(String user_id, String login_time) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTime = new java.util.Date();//得到当前系统时间
        String out_time = formatter.format(currentTime); //将日期时间格式化
        Connection conn = DBConnection.getConnection();
        String sql = "{call OUT(?,?,?)}";
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, user_id);
            cstmt.setString(2, login_time);
            cstmt.setString(3, out_time);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
    }
}
