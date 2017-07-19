package com.core;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dao.LoanSettementDAO;
import com.entity.LoanSettement;
import com.factory.DAOFactory;

public class LoanSettementWriteServer {
	public String doLoanSettementWrite(JSONObject loansettementMsg) {
		String LOAN_NO = loansettementMsg.getString("LOAN_NO");
		String DD_NO = loansettementMsg.getString("DD_NO");
		LoanSettementDAO loansettementDAO = DAOFactory
				.getLoanSettementDAOInstance();
		LoanSettement loansettement = loansettementDAO
				.ChangeBalanceByLOAN_NOAndDD_NO(LOAN_NO, DD_NO);
		Map<String, Object> loansettementMsg1 = new HashMap<String, Object>();
		if (loansettement.getLoan_no() != null) {
			loansettementMsg1.put("OK", "数据修改完成!");
		}
		JSONObject jsonObject = JSONObject.fromObject(loansettementMsg1);
		return jsonObject.toString();
	}
}
