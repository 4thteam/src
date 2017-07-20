package com.core;

import com.dao.LoangrantDAO;
import com.dao.UserDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class RandomNumberServer {
	 public String dorandomnumber(JSONObject dorandomnumberMsg) {
		 
	        LoangrantDAO loangrantDAO= DAOFactory.getLoangrantDAOInstance();
	        
	        Loangrant loangrant = loangrantDAO.RandomNumber();
	        
	        Map<String,Object> loangrantMsg = new HashMap<>();
	        if(loangrant.getUser_maturity()!= null){
	        	loangrantMsg.put("loangrant_1",loangrant.getUser_dd_no());
	        }
	        JSONObject jsonObject = JSONObject.fromObject(loangrantMsg);

	        return jsonObject.toString();
	 }
}