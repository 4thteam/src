package com.core;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dao.LoanSettementDAO;
import com.entity.LoanSettement;
import com.factory.DAOFactory;

public class LoanSettementReadServer {
	public String doLoanSettementRead(JSONObject loansettementMsg) {
		String LOAN_NO = loansettementMsg.getString("LOAN_NO");
		String DD_NO = loansettementMsg.getString("DD_NO");
		LoanSettementDAO loansettementDAO = DAOFactory
				.getLoanSettementDAOInstance();
		LoanSettement loansettement = loansettementDAO
				.FindInfoByLOAN_NOAndDD_NO(LOAN_NO, DD_NO);
		Map<String, Object> loansettementMsg1 = new HashMap<String, Object>();
		if (loansettement.getLoan_no() != null) {
			loansettementMsg1.put("branch", loansettement.getBranch());
			loansettementMsg1.put("ccy", loansettement.getCcy());
			loansettementMsg1.put("borrower", loansettement.getBorrower());
			loansettementMsg1.put("lender", loansettement.getLender());
			loansettementMsg1.put("dd_amt", loansettement.getDd_amt());
			loansettementMsg1.put("user_id", loansettement.getUser_id());
			loansettementMsg1.put("int_rate", loansettement.getInt_rate());
			loansettementMsg1.put("base_acct_no", loansettement
					.getBase_acct_no());
			loansettementMsg1.put("loan_type", loansettement.getLoan_type());
			loansettementMsg1.put("loan_sub_type", loansettement
					.getLoan_sub_type());
			loansettementMsg1.put("loan_no", loansettement.getLoan_no());
		}

		JSONObject jsonObject = JSONObject.fromObject(loansettementMsg1);
		return jsonObject.toString();

	}
}
