package com.entity;


public class LoanAccOpen {

    private String contract_no;//合同号
    private String loan_type;//贷款类型
    private String loan_sub_type;//贷款子类型
    private String borrower;//贷款人编号
    private String book_branch;//贷款签约银行
    private String lender;//借款人
    private String ccy;//账户币种
    private String start_date;//贷款开始时间
    private String mature_date;//贷款到期日期
    private double loan_amt;//贷款金额
    private double drawn_amt;//当前发放额
    private double int_rate;//贷款当前利率
    private int billing_days;//提前出单天数
    private int month_basis;//基准月天数
    private int year_basis;//基准年天数


    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getLoan_sub_type() {
        return loan_sub_type;
    }

    public void setLoan_sub_type(String loan_sub_type) {
        this.loan_sub_type = loan_sub_type;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBook_branch() {
        return book_branch;
    }

    public void setBook_branch(String book_branch) {
        this.book_branch = book_branch;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getMature_date() {
        return mature_date;
    }

    public void setMature_date(String mature_date) {
        this.mature_date = mature_date;
    }

    public double getLoan_amt() {
        return loan_amt;
    }

    public void setLoan_amt(double loan_amt) {
        this.loan_amt = loan_amt;
    }

    public double getDrawn_amt() {
        return drawn_amt;
    }

    public void setDrawn_amt(double drawn_amt) {
        this.drawn_amt = drawn_amt;
    }

    public double getInt_rate() {
        return int_rate;
    }

    public void setInt_rate(double int_rate) {
        this.int_rate = int_rate;
    }

    public int getBilling_days() {
        return billing_days;
    }

    public void setBilling_days(int billing_days) {
        this.billing_days = billing_days;
    }

    public int getMonth_basis() {
        return month_basis;
    }

    public void setMonth_basis(int month_basis) {
        this.month_basis = month_basis;
    }

    public int getYear_basis() {
        return year_basis;
    }

    public void setYear_basis(int year_basis) {
        this.year_basis = year_basis;
    }


}
