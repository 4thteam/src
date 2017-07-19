package com.factory;

import com.dao.DepositAndWithdrawalDAO;
import com.dao.LoanSettementDAO;
import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.daoimpl.DepositAndWithdrawalDAOImpl;
<<<<<<< HEAD
import com.daoimpl.LoanSettementDAOImpl;
=======
>>>>>>> 2563e8b2059a9e2c3ea731cb16778c938b2d11ef
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
<<<<<<< HEAD
    
    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance(){
    	return new DepositAndWithdrawalDAOImpl();

    public static CilentDAO getCilentDAOInstance(){
=======

    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance() {
        return new DepositAndWithdrawalDAOImpl();
    }

    public static CilentDAO getCilentDAOInstance() {
>>>>>>> 2563e8b2059a9e2c3ea731cb16778c938b2d11ef
        return new CilentDAOImpl();
    }

	public static LoanSettementDAO getLoanSettementDAOInstance() {
		return new LoanSettementDAOImpl();
	}
	
}
