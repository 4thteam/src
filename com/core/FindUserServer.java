package com.core;

import com.dao.LoangrantDAO;
import com.dao.UserDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class FindUserServer {
	 public String dofinduser(JSONObject dofinduserMsg) {

	        String user_loan_no = dofinduserMsg.getString("user_loan_no");
	        LoangrantDAO loangrantDAO= DAOFactory.getLoangrantDAOInstance();
	        Loangrant loangrant = loangrantDAO.findUserbyloan_no(user_loan_no);
	        Map<String,Object> loangrantMsg = new HashMap<>();
	        if(loangrant.getUser_loan_no()!= null){
	        	loangrantMsg.put("loangrant_1",loangrant.getUser_needmoney());
	        	loangrantMsg.put("loangrant_2",loangrant.getUser_name().trim());
	        	loangrantMsg.put("loangrant_3",loangrant.getUser_number().trim());
	        }
	        JSONObject jsonObject = JSONObject.fromObject(loangrantMsg);

	        return jsonObject.toString();
	 }
}