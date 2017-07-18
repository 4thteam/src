package com.factory;

import com.dao.DepositAndWithdrawalDAO;
import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.daoimpl.DepositAndWithdrawalDAOImpl;
import com.daoimpl.MenusDAOImpl;
import com.daoimpl.UserDAOImpl;

/**
 * Created by 余文彪 on 2017/7/13.
 */
public class DAOFactory {
    public static MenusDAO getMenusDAOInstance(){
        return new MenusDAOImpl();
    }

    public static UserDAO getUserDAOInstance(){
        return new UserDAOImpl();
    }
    
    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance(){
    	return new DepositAndWithdrawalDAOImpl();
    }
}
