package com.core;

import java.util.HashMap;
import java.util.Map;

import com.dao.CilentDAO;
import com.factory.DAOFactory;

import net.sf.json.JSONObject;

public class OpenCardServer {
    public String doNewCard(JSONObject CardMsg) {
        String ch_name = CardMsg.getString("ch_name");
        String id_card_num = CardMsg.getString("id_card_num");
        String card_type = CardMsg.getString("card_type");
        String phone = CardMsg.getString("phone");
        String card_password = CardMsg.getString("card_password");
        String net_id = CardMsg.getString("net_id");

        CilentDAO CilentDAO = DAOFactory.getCilentDAOInstance();
        int is_vailable = CilentDAO.New_Card(ch_name, id_card_num, card_type, net_id, phone, card_password);
        Map<String, Object> NewCardMsg = new HashMap<String, Object>();

        NewCardMsg.put("is_vailable", is_vailable);
        JSONObject jsonObject = JSONObject.fromObject(NewCardMsg);
        return jsonObject.toString();
    }
}
