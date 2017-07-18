package com.dao;

/*
 *Created by 李元富 on 2017/7/17. 
 */

public interface DepositAndWithdrawalDAO {

	public int findBalanceById(String cardId);
	
	public boolean depositById(String cardId,String password,int money);
	
	public boolean withdrawalById(String cardId,String password,int money);
	
}
