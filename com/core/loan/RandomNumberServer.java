package com.core.loan;

import com.dao.LoangrantDAO;
import com.dao.UserDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RandomNumberServer {

    public String dorandomnumber() {

        LoangrantDAO loangrantDAO = DAOFactory.getLoangrantDAOInstance();

        String loangrant = loangrantDAO.RandomNumber();
        Map<String, Object> loangrantMsg = new HashMap<>();
        if (loangrant != null) {
            loangrantMsg.put("loangrant_1", loangrant);
        }
        JSONObject jsonObject = JSONObject.fromObject(loangrantMsg);

        return jsonObject.toString();
    }
}