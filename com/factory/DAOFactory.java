package com.factory;

import com.dao.DepositAndWithdrawalDAO;
import com.dao.LoanSettementDAO;
import com.dao.LoangrantDAO;
import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.daoimpl.DepositAndWithdrawalDAOImpl;
import com.daoimpl.LoanSettementDAOImpl;
import com.daoimpl.LoangrantDAOImpl;
import com.dao.CilentDAO;
import com.daoimpl.CilentDAOImpl;
import com.daoimpl.MenusDAOImpl;
import com.daoimpl.UserDAOImpl;

public class DAOFactory {
    public static MenusDAO getMenusDAOInstance() {
        return new MenusDAOImpl();
    }

    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }

    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance(){
    	return new DepositAndWithdrawalDAOImpl();
    }

    public static CilentDAO getCilentDAOInstance(){}

    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance() {
        return new DepositAndWithdrawalDAOImpl();
    }

    public static CilentDAO getCilentDAOInstance() {
        return new CilentDAOImpl();
    }

	public static LoanSettementDAO getLoanSettementDAOInstance() {
		return new LoanSettementDAOImpl();
	}
	
    public static UserDAO getUserDAOInstance(){
	        return new UserDAOImpl();
	    }
	public static LoangrantDAO getLoangrantDAOInstance(){
	    	return new LoangrantDAOImpl();
	    }
}
