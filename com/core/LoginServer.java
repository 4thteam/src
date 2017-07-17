package com.core;

import com.dao.UserDAO;
import com.entity.User;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginServer {

    public String doLogin(JSONObject loginMsg) {

        String user_id = loginMsg.getString("user_id");
        String password = loginMsg.getString("password");
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        User user = userDAO.findUserByUseridAndUser_passwod(user_id, password);
        Map<String, Object> userMsg = new HashMap<>();
        if (user.getUser_name() != null) {
            userMsg.put("user", user.getUser_name().trim());
            userMsg.put("role", user.getUser_role());
        }
        JSONObject jsonObject = JSONObject.fromObject(userMsg);

        return jsonObject.toString();
    }
}
