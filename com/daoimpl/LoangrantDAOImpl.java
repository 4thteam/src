package com.daoimpl;

import com.DBConnection;
import com.dao.LoangrantDAO;
import com.entity.Loangrant;

import java.sql.*;

public class LoangrantDAOImpl implements LoangrantDAO {

	Loangrant user_loangrant = new Loangrant();

	public Loangrant RandomNumber() {
		Connection conn = DBConnection.getConnection();
		String sql = "{call Random(?)}";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			cstmt.execute();

			user_loangrant.setUser_dd_no(cstmt.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(cstmt);
			DBConnection.close(conn);
		}
		return user_loangrant;
	}

	public Loangrant findUserbyloan_no(String loan_no) {
		Connection conn = DBConnection.getConnection();
		String sql = "{call drawdown(?,?,?,?)}";
		CallableStatement cstmt = null;
		// Loangrant user_loangrant = new Loangrant();
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, loan_no);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
			cstmt.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
			cstmt.execute();

			user_loangrant.setUser_loan_no(loan_no);
			user_loangrant.setUser_needmoney(cstmt.getInt(2));
			user_loangrant.setUser_name(cstmt.getString(3));
			user_loangrant.setUser_number(cstmt.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(cstmt);
			DBConnection.close(conn);
		}
		return user_loangrant;
	}

	public Loangrant writeTimeand_dd_amt(String maturity, String dd_date,
			int dd_amt) {
		// TODO Auto-generated method stub
		/*
		 * Connection conn = DBConnection.getConnection(); String sql =
		 * "{call DRAWDOWN_1(?,?,?)}"; CallableStatement cstmt = null;
		 * //Loangrant user_loangrant = new Loangrant(); try{ cstmt =
		 * conn.prepareCall(sql); cstmt.setString(1,maturity);
		 * cstmt.setString(2,dd_date); cstmt.setInt(3,dd_amt); cstmt.execute();
		 * 
		 * user_loangrant.setUser_maturity(maturity);
		 * user_loangrant.setUser_dd_date(dd_date);
		 * user_loangrant.setUser_dd_amt(dd_amt); }catch(SQLException e){
		 * e.printStackTrace(); }finally{ DBConnection.close(cstmt);
		 * DBConnection.close(conn); }
		 */
		user_loangrant.setUser_maturity(maturity);
		user_loangrant.setUser_dd_date(dd_date);
		user_loangrant.setUser_dd_amt(dd_amt);
		return user_loangrant;
	}

	public Loangrant readrate(String int_type) {
		Connection conn = DBConnection.getConnection();
		String sql = "{ call DRAWDOWN_2(?,?,?,?)}";
		CallableStatement cstmt = null;
		// Loangrant user_loangrant = new Loangrant();
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, int_type);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.FLOAT);
			cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.FLOAT);
			cstmt.registerOutParameter(4, oracle.jdbc.OracleTypes.FLOAT);
			cstmt.execute();

			user_loangrant.setUser_actual_rate(cstmt.getFloat(2));
			user_loangrant.setUser_min_rate(cstmt.getFloat(3));
			user_loangrant.setUser_max_rate(cstmt.getFloat(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(cstmt);
			DBConnection.close(conn);
		}
		return user_loangrant;

	}

	public void NewTable(int dd_no, String name, String maturity,
			String dd_date, int dd_amt, double actual_rate,double basis_rate,double plty_rate) {
		Connection conn = DBConnection.getConnection();
		String sql = "{ call NEWTABLE(?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, dd_no);
			cstmt.setString(2, name);
			cstmt.setString(3, maturity);
			cstmt.setString(4, dd_date);
			cstmt.setInt(5, dd_amt);
			cstmt.setDouble(6, actual_rate);
			cstmt.setDouble(7,basis_rate);
			cstmt.setDouble(8,plty_rate);

			cstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(cstmt);
			DBConnection.close(conn);
		}
	}

	
	public static void main(String arg[]) {
		LoangrantDAOImpl ceshi = new LoangrantDAOImpl();
		ceshi.NewTable(21102,"测试人","2011-11-11","2011-11-11",600, 0.07,0.07,0.07);
	  
	  }
}
