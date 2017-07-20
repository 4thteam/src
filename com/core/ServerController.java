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

        //查询余额
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("balance")) {
            result = new ReturnBalanceServer().doReturnBalance(jsonObject);
        }

        //存款
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("deposit")) {
            result = new DepositServer().doDeposit(jsonObject);
        }

        //取款
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("withdrawal")) {
            result = new WithdrawalServer().doWithdrawal(jsonObject);
        }

        
        if (jsonObject.get("action") != null && jsonObject.get("action").equals("LoanSettementRead")) {
			result = new LoanSettementReadServer().doLoanSettementRead(jsonObject);
		}
	
		if (jsonObject.get("action") != null && jsonObject.get("action").equals("LoanSettementWrite")) {
			result = new LoanSettementWriteServer().doLoanSettementWrite(jsonObject);
		}
	
		if (jsonObject.get("action") != null && jsonObjevt.get("action").equals("randomnumber")) {
			resule = new RandomNumberServer().dorandomnumber(jsonObject);
		}

		if (jsonObject.get("action") != null && jsonObject.get("action").equals("finduser")) {
			result = new FindUserServer().dofinduser(jsonObject);
		}

		if (jsonObject.get("action") != null && jsonObject.get("action").equals("writetime")) {
			result = new WriteTimeServer().dowritetime(jsonObject);
		}

		if (jsonObject.get("action") != null && jsonObject.get("action").equals("readrate")) {
			result = new ReadRateServer().doreadrate(jsonObject);
		}

		if (jsonObject.get("action") != null && jsonObject.get("action").equals("newtable")) {
			result = new NewTableServer().donewtable(jsonObject);
		}

        return result;
    }
}
