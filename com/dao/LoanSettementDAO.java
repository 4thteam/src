package com.dao;

import com.entity.LoanSettement;

public interface LoanSettementDAO {
	public LoanSettement FindInfoByLOAN_NOAndDD_NO(String LOAN_NO, String DD_NO);

	public String ChangeBalanceByLOAN_NOAndDD_NO(String cilent_card_id,int money);

}
