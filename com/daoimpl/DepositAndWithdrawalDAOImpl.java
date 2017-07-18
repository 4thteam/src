package com.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.DBConnection;
import com.dao.DepositAndWithdrawalDAO;

import oracle.jdbc.OracleTypes;

/*
 * Created by 李元富 on 2017/7/13.
 */

public class DepositAndWithdrawalDAOImpl implements DepositAndWithdrawalDAO{
	
	//通过调用数据库存储函数来查询客户账户余额
	public int findBalanceById(String cardId) {
		int balance = 0;
		String sql = "{?=call ReturnBalance(?)}";
		Connection conn = null;
		CallableStatement call = null;
		try {
			conn = DBConnection.getConnection();
			call = conn.prepareCall(sql);
			
			call.registerOutParameter(1, OracleTypes.INTEGER);
			call.setString(2, cardId);
			
			call.execute();
			
			balance = call.getInt(1);
			return balance;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			DBConnection.close(call);
			DBConnection.close(conn);
		}
	}
	
	@Override
	public boolean depositById(String cardId,String password,int money) {
		String sql = "{call StoryMoney(?,?,?)}";
		Connection conn = null;
		CallableStatement call = null;
		
		try {
			conn = DBConnection.getConnection();
			call = conn.prepareCall(sql);
			
			call.setString(1, cardId);
			call.setString(2, password);
			call.setInt(3, money);
			
			call.execute();
			return true;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}finally {
			DBConnection.close(call);
			DBConnection.close(conn);
		}
		
	}

	@Override
	public boolean withdrawalById(String cardId,String password,int money) {
		String sql = "{call WithDrawal(?,?,?)}";
		Connection conn = null;
		CallableStatement call = null;
		
		try {
			conn = DBConnection.getConnection();
			call = conn.prepareCall(sql);
			
			call.setString(1, cardId);
			call.setString(2, password);
			call.setInt(3, money);
			call.execute();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}finally {
			DBConnection.close(call);
			DBConnection.close(conn);
		}
		
	}
}
