package com.core;

import java.util.HashMap;
import java.util.Map;

import com.dao.CilentDAO;

import com.factory.DAOFactory;

import net.sf.json.JSONObject;

public class ClosingACard {
	public String doClosing(JSONObject ClosingMsg) {

		String ch_name = ClosingMsg.getString("ch_name");
		String idcard_num = ClosingMsg.getString("idcard_num");
		int card_id = Integer.parseInt(ClosingMsg.getString("card_id"));
		
		String password = ClosingMsg.getString("password");
		CilentDAO CilentDAO = DAOFactory.getCilentDAOInstance();
		int is_vailable = CilentDAO.Closing_a_card(ch_name, idcard_num, card_id, password);
		Map<String, Object> ClosingCardMsg = new HashMap<String, Object>();

		ClosingCardMsg.put("is_vailable", is_vailable);
		JSONObject jsonObject = JSONObject.fromObject(ClosingCardMsg);
		return jsonObject.toString();
	}
}