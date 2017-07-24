package com.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.DBConnection;
import com.dao.LoanAccOpenDAO;
import com.entity.LoanAccOpen;

import oracle.jdbc.OracleTypes;

public class LoanAccOpenDAOImp implements LoanAccOpenDAO {

    @Override
    public int addLoanAccToDatabase(LoanAccOpen lao) {

        Connection conn = DBConnection.getConnection();
        String sql = "{?=call addLoaninfom(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cstmt = null;

        try {
            cstmt = conn.prepareCall(sql);

            cstmt.registerOutParameter(1, OracleTypes.NUMBER);
            cstmt.setString(2, lao.getBorrower());
            cstmt.setString(3, lao.getLender());
            cstmt.setString(4, lao.getStart_date());
            cstmt.setString(5, lao.getMature_date());
            cstmt.setDouble(6, lao.getLoan_amt());
            cstmt.setDouble(7, lao.getInt_rate());
            cstmt.setString(8, lao.getContract_no());
            cstmt.setString(9, lao.getLoan_type());
            cstmt.setString(10,lao.getLoan_sub_type());
            cstmt.setString(11, lao.getBook_branch());
            cstmt.setString(12, lao.getCcy());
            cstmt.setDouble(13, lao.getDrawn_amt());
            cstmt.setDouble(14, lao.getBilling_days());
            cstmt.setDouble(15, lao.getMonth_basis());
            cstmt.setDouble(16, lao.getYear_basis());
            cstmt.executeUpdate();

            int loan_no = cstmt.getInt(1);

            System.out.println("loan_no is:" + loan_no);
            return loan_no;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(conn);

        }

    }
}
