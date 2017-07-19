package com.core;

import java.util.HashMap;
import java.util.Map;

import com.dao.DepositAndWithdrawalDAO;
import com.factory.DAOFactory;

import net.sf.json.JSONObject;

/*
 * Created by 李元富 on 2017/7/17.
 */
public class WithdrawalServer {

    public String doWithdrawal(JSONObject withdrawalMsg) {
        String cilent_card_id = withdrawalMsg.getString("cilent_card_id");
        String cilent_card_password = withdrawalMsg.getString("cilent_card_password");
        int money = withdrawalMsg.getInt("money");
        DepositAndWithdrawalDAO dao = DAOFactory.getDepositAndWithdrawalDAOInstance();
        int balance = dao.findBalanceById(cilent_card_id);
        Map<String, Object> userWithdrawalMsg = new HashMap<>();
        if (balance > money) {
            boolean withdrawalIsSuccess = dao.withdrawalById(cilent_card_id, cilent_card_password, money);

            if (withdrawalIsSuccess) {
                userWithdrawalMsg.put("withdrawalMessage", "取款成功，欢迎您的使用！");
            } else {
                userWithdrawalMsg.put("withdrawalMessage", "取款失败，请检查您的账户！");
            }
        } else {
            userWithdrawalMsg.put("withdrawalMessage", "余额不足，取款失败！");
        }

        JSONObject jsonObject = JSONObject.fromObject(userWithdrawalMsg);

        return jsonObject.toString();
    }

}
