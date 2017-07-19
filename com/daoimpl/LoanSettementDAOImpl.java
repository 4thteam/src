package com.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBConnection;
import com.dao.LoanSettementDAO;
import com.entity.LoanSettement;

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

	/*
	 * public static void main(String[] args) { LoanSettementDAOImpl a = new
	 * LoanSettementDAOImpl(); LoanSettement b;
	 * b=a.FindInfoByLOAN_NOAndDD_NO("123456", "123456");
	 * System.out.println(b.getBase_acct_no());
	 * System.out.println(b.getBorrower()); System.out.println(b.getBranch());
	 * System.out.println(b.getCcy()); System.out.println(b.getDd_amt());
	 * System.out.println(b.getUser_id()); System.out.println(b.getInt_rate());
	 * System.out.println(b.getLender()); System.out.println(b.getLoan_type());
	 * System.out.println(b.getLoan_sub_type()); }
	 */
	public LoanSettement ChangeBalanceByLOAN_NOAndDD_NO(String LOAN_NO,
			String DD_NO) {
		LoanSettementDAOImpl a = new LoanSettementDAOImpl();
		LoanSettement b = a.FindInfoByLOAN_NOAndDD_NO("123456", "123456");
		Connection conn = DBConnection.getConnection();
		String sql = "{call CHANGE_BALANCE(?,?,?)}";
		CallableStatement cstmt = null;
		LoanSettement changebalance = new LoanSettement();
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, LOAN_NO);
			cstmt.setString(2, DD_NO);
			cstmt.setString(3, b.getDd_amt());
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(cstmt);
			DBConnection.close(conn);
		}
		return changebalance;
	}

	/*
	 * public static void main(String[] args) { LoanSettementDAOImpl c = new
	 * LoanSettementDAOImpl(); LoanSettement d =
	 * c.ChangeBalanceByLOAN_NOAndDD_NO("123456", "123456");
	 * System.out.println("完成！"); }
	 */
}
