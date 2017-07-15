package com.daoimpl;

import com.DBConnection;
import com.dao.UserDAO;
import com.entity.User;

import java.sql.*;

/**
 * Created by 余文彪 on 2017/7/13.
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
            if (rs!=null){
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
}
