package com.core.open;

import java.util.HashMap;
import java.util.Map;

import com.dao.CilentDAO;
import com.entity.Client;
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
        Client client=CilentDAO.findCardInfoByClient_idAndClient_card_type(Integer.parseInt(id_card_num),card_type);
        Map<String, Object> NewCardMsg = new HashMap<String, Object>();
        NewCardMsg.put("is_vailable", is_vailable);
        if (is_vailable==1){
            NewCardMsg.put("client_card_id",client.getClient_card_id());
            NewCardMsg.put("client_id",client.getClient_id());
            NewCardMsg.put("client_card_type",client.getClient_card_type());
            NewCardMsg.put("cilent_card_balance",client.getCilent_card_balance());
            NewCardMsg.put("cilent_card_open_time",client.getCilent_card_open_time());
            NewCardMsg.put("cilent_card_open_net_id",client.getCilent_card_open_net_id());
            NewCardMsg.put("cilent_card_password",client.getCilent_card_password());
            NewCardMsg.put("cilent_card_phone",client.getCilent_card_phone());
            NewCardMsg.put("cilent_card_is_vailable",client.getCilent_card_is_vailable());
        }
        JSONObject jsonObject = JSONObject.fromObject(NewCardMsg);
        return jsonObject.toString();
    }
}
