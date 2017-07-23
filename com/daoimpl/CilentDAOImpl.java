package com.daoimpl;

import com.DBConnection;
import com.dao.CilentDAO;
import com.entity.Client;
import com.factory.DAOFactory;

import java.sql.*;

public class CilentDAOImpl implements CilentDAO {

    //返回0表示用户已经存在，无需登记，返回表示新用户登记成功
    public int New_Cilent(String ch_name, String en_name,
                          String sex, String idcard_type,
                          String idcard_num, String nickname,
                          String type, String address,
                          String nat, String address_num,
                          String city, String city_num,
                          String phone, String email) {
        Connection conn = DBConnection.getConnection();
        String sql = "{call open_an_account(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cstmt = null;
        int is_vailable = 0;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, ch_name);
            cstmt.setString(2, en_name);
            cstmt.setString(3, sex);
            cstmt.setString(4, idcard_type);
            cstmt.setString(5, idcard_num);
            cstmt.setString(6, nickname);
            cstmt.setString(7, type);
            cstmt.setString(8, address);
            cstmt.setString(9, nat);
            cstmt.setString(10, address_num);
            cstmt.setString(11, city);
            cstmt.setString(12, city_num);
            cstmt.setString(13, phone);
            cstmt.setString(14, email);
            cstmt.registerOutParameter(15, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.execute();
            is_vailable = cstmt.getInt(15);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return is_vailable;
    }

    //开户方法，返回0表示还未新用户登记信息，返回1表示成功，返回2表示开户失败：同一个人在同一家银行(以法人为单位)只能开立一个Ⅰ类账户,请办理Ⅱ类或Ⅲ类
    @Override
    public int New_Card(String ch_name, String id_card_num, String card_type, String net_id, String phone, String password) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTime = new java.util.Date();//得到当前系统时间
        String open_time = formatter.format(currentTime); //将日期时间格式化
        Connection conn = DBConnection.getConnection();
        String sql = "{call open_a_card(?,?,?,?,?,?,?,?)}";
        CallableStatement cstmt = null;
        int is_vailable = 0;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, ch_name);
            cstmt.setString(2, id_card_num);
            cstmt.setString(3, card_type);
            cstmt.setString(4, open_time);
            cstmt.setString(5, net_id);
            cstmt.setString(6, phone);
            cstmt.setString(7, password);

            cstmt.registerOutParameter(8, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.execute();
            is_vailable = cstmt.getInt(8);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return is_vailable;
    }

    //销户方法，返回0表示银行卡号错误或者用户信息错误，返回1表示销户成功，返回2表示卡内还有余额或欠费请取款后销户
    @Override
    public int Closing_a_card(String ch_name, String idcard_num, int card_id, String password) {
        int is_vailable;
        Connection conn = DBConnection.getConnection();
        String sql = "{call closing_a_card(?,?,?,?,?)}";
        CallableStatement cstmt = null;
        is_vailable = 0;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, ch_name);
            cstmt.setString(2, idcard_num);
            cstmt.setInt(3, card_id);
            cstmt.setString(4, password);
            cstmt.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.execute();
            is_vailable = cstmt.getInt(5);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return is_vailable;
    }

    @Override
    public Client findCardInfoByClient_idAndClient_card_type(int client_id, String client_card_type) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM \"cilent_card_info\" WHERE \"cilent_id\"=? AND \"cilent_card_type\"=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Client client = new Client();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, client_id);
            pstmt.setString(2, client_card_type);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                client.setClient_card_id(rs.getLong(1) + "");
                client.setClient_id(rs.getInt(2) + "");
                client.setClient_card_type(rs.getString(3) + "");
                client.setCilent_card_balance(rs.getString(4) + "");
                client.setCilent_card_open_time(rs.getString(5) + "");
                client.setCilent_card_open_net_id(rs.getString(6) + "");
                client.setCilent_card_password(rs.getString(7) + "");
                client.setCilent_card_phone(rs.getString(8) + "");
                client.setCilent_card_is_vailable(rs.getString(9) + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(pstmt);
            DBConnection.close(conn);
        }
        return client;
    }
}
