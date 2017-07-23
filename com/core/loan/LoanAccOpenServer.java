package com.core.loan;

import com.dao.LoanAccOpenDAO;
import com.entity.LoanAccOpen;
import com.factory.DAOFactory;
import net.sf.json.JSONObject;

/**
 * 贷款开立
 */
public class LoanAccOpenServer {

    public String doLoanOpen(JSONObject loanMsg) {

        LoanAccOpen lao = new LoanAccOpen();
        String month_basis = loanMsg.getString("month_basis");//基准月天数
        String billing_days = loanMsg.getString("billing_days");//提前出单天数
        String int_rate = loanMsg.getString("int_rate");//贷款当前利率
        String drawn_amt = loanMsg.getString("drawn_amt");//当前发放额
        String loan_amt = loanMsg.getString("loan_amt");//贷款金额
        String mature_date = loanMsg.getString("mature_date");//贷款到期日期
        String start_date = loanMsg.getString("start_date");//贷款开始时间
        String ccy = loanMsg.getString("ccy");//账户币种
        String lender = loanMsg.getString("lender");//借款人
        String book_branch = loanMsg.getString("book_branch");//贷款签约银行
        String borrower = loanMsg.getString("borrower");//贷款人编号
        String loan_sub_type = loanMsg.getString("loan_sub_type");//贷款子类型
        String loan_type = loanMsg.getString("loan_type");//贷款类型
        String contract_no = loanMsg.getString("contract_no");//合同号
        String year_basis = loanMsg.getString("year_basis");//基准年天数

        lao.setBilling_days(Integer.parseInt(billing_days));
        lao.setBook_branch(book_branch);
        lao.setBorrower(borrower);
        lao.setCcy(ccy);
        lao.setContract_no(contract_no);
        lao.setDrawn_amt(Double.parseDouble(drawn_amt));
        lao.setInt_rate(Double.parseDouble(int_rate));
        lao.setLender(lender);
        lao.setLoan_amt(Double.parseDouble(loan_amt));
        lao.setLoan_sub_type(loan_sub_type);
        lao.setLoan_type(loan_type);
        lao.setMature_date(mature_date);
        lao.setMonth_basis(Integer.parseInt(month_basis));
        lao.setStart_date(start_date);
        lao.setYear_basis(Integer.parseInt(year_basis));

        LoanAccOpenDAO loanopenDAO = DAOFactory.getLoanAccOpenDAOInstance();

        //返回值需要重设！！！！！！！！！！
        int addStatus = loanopenDAO.addLoanAccToDatabase(lao);
        return Integer.toString(addStatus);
    }
}
