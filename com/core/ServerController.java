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
        
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("balance")){
        	result = String.valueOf((new ReturnBalanceServer().doReturnBalance(jsonObject)));
        }

        if(jsonObject.get("action") != null && jsonObject.get("action").equals("deposit")) {
        	result = new DepositServer().doDeposit(jsonObject);
        }
        
        if(jsonObject.get("action") != null && jsonObject.get("action").equals("withdrawal")) {
        	result = new WithdrawalServer().doWithdrawal(jsonObject);
        }
        
        return result;
    }
}
