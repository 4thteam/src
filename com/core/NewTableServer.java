package com.core;

import com.dao.LoangrantDAO;
import com.dao.UserDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NewTableServer {
	public Stirng donewtable(JSONObject donewtableMsg) {

		int user_dd_no = donewtableMsg.getInt("user_dd_no");
		String user_name = donewtableMsg.getString("user_name");
		String user_maturity = donewtableMsg.getString("user_maturity");
		String user_dd_date = donewtableMsg.getString("user_dd_date");
		int user_dd_amt = donewtableMsg.getInt("user_dd_amt");
		double user_actual_rate = donewtableMsg.getDouble("user_actual_rate");
		double user_basis_rate = donewtableMsg.getDouble("user_basis_rate");
		double user_plty_rate = donewtableMsg.getDouble("user_plty_rate");

		LoangrantDAO loangrantDAO = DAOFactory.getLoangrantDAOInstance();
		loangrantDAO.NewTable(user_dd_no,  user_name,
				user_maturity, user_dd_date, user_dd_amt,
				user_actual_rate,user_basis_rate,user_plty_rate );
		
		return "数据库添加成功";

	}
}