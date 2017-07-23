package com.core.loan;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dao.LoanSettementDAO;
import com.entity.LoanSettement;
import com.factory.DAOFactory;

/**
 * 发放结算提交
 */
public class LoanSettementWriteServer {

    public String doLoanSettementWrite(JSONObject loansettementMsg) {
        String cilent_card_id = loansettementMsg.getString("cilent_card_id");
        String money = loansettementMsg.getString("money");
        LoanSettementDAO loansettementDAO = DAOFactory.getLoanSettementDAOInstance();
        String result = loansettementDAO.ChangeBalanceByLOAN_NOAndDD_NO(cilent_card_id, Integer.parseInt(money));
        return result;
    }
}
