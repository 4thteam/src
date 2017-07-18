package com.core;

import java.util.HashMap;
import java.util.Map;

import com.dao.DepositAndWithdrawalDAO;
import com.factory.DAOFactory;

import net.sf.json.JSONObject;

/*
 * Created by 李元富 on 2017/7/17.
 */
public class DepositServer {
	
	public String doDeposit(JSONObject depositMsg) {
		String cilent_card_id = depositMsg.getString("cilent_card_id");
		String cilent_card_password = depositMsg.getString("cilent_card_password");
		int money = depositMsg.getInt("money");
		DepositAndWithdrawalDAO dao = DAOFactory.getDepositAndWithdrawalDAOInstance();
		boolean depositIsSuccess = dao.depositById(cilent_card_id, cilent_card_password, money);
		Map<String,Object> userDepositMsg = new HashMap<>();
		if(depositIsSuccess) {
			userDepositMsg.put("depositMessage", "存款成功，欢迎再次为您服务!");
		}else {
			userDepositMsg.put("depositMessage", "存款失败，请检查您的账号是否正确!");
		}
		
		JSONObject jsonObject = JSONObject.fromObject(userDepositMsg);
		
		return jsonObject.toString();
	}
	
}
