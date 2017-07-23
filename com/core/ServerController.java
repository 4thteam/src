package com.core;

import com.core.depositAndWithdraw.DepositServer;
import com.core.depositAndWithdraw.ReturnBalanceServer;
import com.core.depositAndWithdraw.WithdrawalServer;
import com.core.loan.*;
import com.core.open.NewCilentServer;
import com.core.open.OpenCardServer;
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

		//获取发放号
		if (jsonObject.get("action") != null && jsonObject.get("action").equals("random_number")) {
			result = new RandomNumberServer().dorandomnumber();
		}

		//根据贷款号获取贷款人信息
		if (jsonObject.get("action") != null && jsonObject.get("action").equals("finduser")) {
			result = new FindUserServer().dofinduser(jsonObject);
		}

		//根据利率号获取利率信息
		if (jsonObject.get("action") != null && jsonObject.get("action").equals("read_rate")) {
			result = new ReadRateServer().doreadrate(jsonObject);
		}

		if (jsonObject.get("action") != null && jsonObject.get("action").equals("newtable")) {
			result = new NewTableServer().donewtable(jsonObject);
		}

        //处理贷款开户功能
        if(jsonObject.get("action")!=null && jsonObject.get("action").equals("LoanAccOpen")){
            result = new LoanAccOpenServer().doLoanOpen(jsonObject);
        }

        return result;
    }
}
