package com.core;

import java.util.HashMap;
import java.util.Map;

import com.dao.DepositAndWithdrawalDAO;
import com.factory.DAOFactory;

import net.sf.json.JSONObject;

public class ReturnBalanceServer {
	public int doReturnBalance(JSONObject balanceMsg) {
		
		String cilent_card_id = balanceMsg.getString("cilent_card_id");
		DepositAndWithdrawalDAO daw = DAOFactory.getDepositAndWithdrawalDAOInstance();
		int balance = daw.findBalanceById(cilent_card_id);
		Map<String,Object> userBalanceMsg = new HashMap<>();
		userBalanceMsg.put("balance", balance);
		JSONObject jsonObject = JSONObject.fromObject(userBalanceMsg);
		
		return Integer.parseInt(jsonObject.toString());
	}
}
