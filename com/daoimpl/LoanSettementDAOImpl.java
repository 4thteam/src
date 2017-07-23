package com.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBConnection;
import com.dao.LoanSettementDAO;
import com.entity.LoanSettement;

/**
 * 发放结算
 */
public class LoanSettementDAOImpl implements LoanSettementDAO {

    public LoanSettement FindInfoByLOAN_NOAndDD_NO(String LOAN_NO, String DD_NO) {
        Connection conn = DBConnection.getConnection();
        String sql = "{call loan_settement(?,?,?)}";
        CallableStatement cstmt = null;
        LoanSettement loansettement = new LoanSettement();
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, LOAN_NO);
            cstmt.setString(2, DD_NO);
            cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(3);
            if (rs != null) {
                if (rs.next()) {
                    loansettement.setBorrower(rs.getString(1));
                    loansettement.setLender(rs.getString(2));
                    loansettement.setBranch(rs.getString(3));
                    loansettement.setLoan_type(rs.getString(4));
                    loansettement.setLoan_sub_type(rs.getString(5));
                    loansettement.setUser_id(rs.getString(6));
                    loansettement.setCcy(rs.getString(7));
                    loansettement.setDd_amt(rs.getString(8));
                    loansettement.setInt_rate(rs.getString(9));
                    loansettement.setBase_acct_no(rs.getString(10));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return loansettement;
    }

    public String ChangeBalanceByLOAN_NOAndDD_NO(String cilent_card_id,int money ) {
        Connection conn = DBConnection.getConnection();
        String sql = "{call CHANGE_BALANCE(?,?)}";
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, cilent_card_id);
            cstmt.setInt(2, money);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);
        }
        return "结算成功 ";
    }
}
