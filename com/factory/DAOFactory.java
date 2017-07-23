package com.factory;

import com.dao.*;
import com.daoimpl.*;
import com.entity.LoanAccOpen;

public class DAOFactory {
    public static MenusDAO getMenusDAOInstance() {
        return new MenusDAOImpl();
    }

    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }


    public static CilentDAO getCilentDAOInstance() {
        return new CilentDAOImpl();
    }

    public static DepositAndWithdrawalDAO getDepositAndWithdrawalDAOInstance() {
        return new DepositAndWithdrawalDAOImpl();
    }

    public static LoanSettementDAO getLoanSettementDAOInstance() {
        return new LoanSettementDAOImpl();
    }

    public static LoangrantDAO getLoangrantDAOInstance() {
        return new LoangrantDAOImpl();
    }

    public static LoanAccOpenDAO getLoanAccOpenDAOInstance() {
        return new LoanAccOpenDAOImp();
    }
}
