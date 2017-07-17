package com.core;

import com.dao.CilentDAO;
import com.dao.UserDAO;
import com.entity.User;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NewCilentServer {
	public String doNew(JSONObject NewCilentMsg) {

		String ch_name = NewCilentMsg.getString("ch_name");
		String en_name = NewCilentMsg.getString("en_name");
		String sex = NewCilentMsg.getString("sex");
		String idcard_type = NewCilentMsg.getString("idcard_type");
		String idcard_num = NewCilentMsg.getString("idcard_num");
		String nickname = NewCilentMsg.getString("nickname");
		String type = NewCilentMsg.getString("type");
		String address = NewCilentMsg.getString("address");
		String nat = NewCilentMsg.getString("nat");
		String address_num = NewCilentMsg.getString("address_num");
		String city = NewCilentMsg.getString("city");
		String city_num = NewCilentMsg.getString("city_num");
		String phone = NewCilentMsg.getString("phone");
		String email = NewCilentMsg.getString("email");
		CilentDAO CilentDAO = DAOFactory.getCilentDAOInstance();
		// 插入以及返回插入结果
		int is_vailable = CilentDAO.New_Cilent(ch_name, en_name, sex, idcard_type, idcard_num, nickname, type, address,
				nat, address_num, city, city_num, phone, email);
		// 输出结果
		Map<String, Object> CilentMsg = new HashMap<String, Object>();

		CilentMsg.put("is_vailable", is_vailable);

		JSONObject jsonObject = JSONObject.fromObject(CilentMsg);

		return jsonObject.toString();
	}

}
