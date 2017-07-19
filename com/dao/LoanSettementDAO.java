package com.dao;

import com.entity.LoanSettement;

public interface LoanSettementDAO {
	public LoanSettement FindInfoByLOAN_NOAndDD_NO(String LOAN_NO, String DD_NO);

	public LoanSettement ChangeBalanceByLOAN_NOAndDD_NO(String LOAN_NO,String DD_NO);

}
