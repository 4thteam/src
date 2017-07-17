package com.core;

import net.sf.json.JSONObject;

/**
 * core服务控制器
 */
public class ServerController {

    private JSONObject jsonObject;

    public ServerController(String message) {
        jsonObject = JSONObject.fromObject(message);
    }

    public String doAction() {
        String result = null;

        //处理登录功能
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("login")) {
            result = new LoginServer().doLogin(jsonObject);
        }

        return result;
    }
}
