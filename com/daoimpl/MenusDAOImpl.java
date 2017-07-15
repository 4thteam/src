package com.daoimpl;

import com.DBConnection;
import com.dao.MenusDAO;
import com.entity.Menus;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 余文彪 on 2017/7/13.
 */
public class MenusDAOImpl implements MenusDAO {
    @Override
    public List<Menus> findMenusById(String id) {
        int userid1 = Integer.parseInt(id);
        //创建一个List实例menuList用于存放相应权限的所用菜单。
        List<Menus> menuList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "{call user_limit(?,?)}";
        CallableStatement cstmt = null;
        try {
            ResultSet rs = null;
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, userid1);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet) cstmt.getObject(2);
            //判断rs是否为空，不为空则将得到的值加入menuList中
            if (rs != null) {
                while (rs.next()) {
                    Menus menu = new Menus();
                    menu.setPower_name(rs.getString(1));
                    menu.setPower_path(rs.getString(2));
                    menu.setMenu_tag(rs.getString(3));
                    menuList.add(menu);//将该菜单加入到List中
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return menuList;
    }
}
