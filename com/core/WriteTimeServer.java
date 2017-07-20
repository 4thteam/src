package com.core;

import com.dao.LoangrantDAO;
import com.dao.UserDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class WriteTimeServer {
	 public String dowritetime(JSONObject dowritetimeMsg) {

	        String user_maturity = dowritetimeMsg.getString("user_maturity");
	        String user_dd_date = dowritetimeMsg.getString("user_dd_date");
	        int user_dd_amt = dowritetimeMsg.getInt("user_dd_amt");
	        
	        LoangrantDAO loangrantDAO= DAOFactory.getLoangrantDAOInstance();
	        
	        Loangrant loangrant = loangrantDAO.writeTimeand_dd_amt(user_maturity,user_dd_date,user_dd_amt);
	        
	        Map<String,Object> loangrantMsg = new HashMap<>();
	        if(loangrant.getUser_maturity()!= null){
	        	loangrantMsg.put("loangrant_1",loangrant.getUser_maturity());
	        	loangrantMsg.put("loangrant_2",loangrant.getUser_dd_date());
	        	loangrantMsg.put("loangrant_3",loangrant.getUser_dd_amt());
	        }
	        JSONObject jsonObject = JSONObject.fromObject(loangrantMsg);

	        return jsonObject.toString();
	 }
}