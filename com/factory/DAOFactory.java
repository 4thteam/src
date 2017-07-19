package com.factory;

<<<<<<< HEAD
import com.dao.DepositAndWithdrawalDAO;
import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.daoimpl.DepositAndWithdrawalDAOImpl;
=======
import com.dao.CilentDAO;
import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.daoimpl.CilentDAOImpl;
>>>>>>> 1ba8082ef644de54cc10105043ff99ca28e2b73f
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
<<<<<<< HEAD
    
    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance(){
    	return new DepositAndWithdrawalDAOImpl();
=======
    public static CilentDAO getCilentDAOInstance(){
        return new CilentDAOImpl();
>>>>>>> 1ba8082ef644de54cc10105043ff99ca28e2b73f
    }
}
