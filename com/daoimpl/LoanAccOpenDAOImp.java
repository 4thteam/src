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
        String sql = "{?=call addLoaninfom(?,?,?,?,?,?)}";
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
