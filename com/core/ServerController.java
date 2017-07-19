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

        //处理新用户
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("NewCilent")) {
            result = new NewCilentServer().doNew(jsonObject);
        }

        //办理新银行卡
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("NewCard")) {
            result = new OpenCardServer().doNewCard(jsonObject);
        }

        //银行卡销户
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("ClosingCard")) {
            result = new OpenCardServer().doNewCard(jsonObject);
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
        
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("LoanSettementRead")) {
			result = new LoanSettementReadServer().doLoanSettementRead(jsonObject);
		}
	
		if (jsonObject.get("action") != null && jsonObject.get("action").equals("LoanSettementWrite")) {
			result = new LoanSettementWriteServer().doLoanSettementWrite(jsonObject);
		}
	
        
        return result;
    }
}
