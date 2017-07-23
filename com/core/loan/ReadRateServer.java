package com.core.loan;

import com.dao.LoangrantDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ReadRateServer {

    public String doreadrate(JSONObject doreadrateMsg) {

        String user_int_type = doreadrateMsg.getString("user_int_type");
        LoangrantDAO loangrantDAO = DAOFactory.getLoangrantDAOInstance();
        Loangrant loangrant = loangrantDAO.readrate(user_int_type);
        Map<String, Object> loangrantMsg = new HashMap<>();
        if (loangrant.getUser_actual_rate() != 0) {
            loangrantMsg.put("true_rate", loangrant.getUser_actual_rate());
            loangrantMsg.put("min_rate", loangrant.getUser_min_rate());
            loangrantMsg.put("max_rate", loangrant.getUser_max_rate());
        }
        JSONObject jsonObject = JSONObject.fromObject(loangrantMsg);
        return jsonObject.toString();
    }
}