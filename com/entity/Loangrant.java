package com.entity;

public class Loangrant {

	private String user_loan_no;//贷款号	
	private int user_dd_no;//发放号
	private int user_needmoney;//贷款额度
	private String user_name;//贷款人
	private String user_number;//贷款人客户号
	private String user_maturity;//到期日
	private String user_dd_date;//发放日期
	private int user_dd_amt;//发放金额
	private String user_int_type;
	private float user_actual_rate;//实际利息
	private float user_min_rate;//最小利息
	private float user_max_rate;//最大利息
	
	public String getUser_loan_no() {
        return user_loan_no;
    }

    public void setUser_loan_no(String user_loan_no) {
        this.user_loan_no = user_loan_no;
    }
    
    public int getUser_dd_no(){
    	return user_dd_no;
    }
    
    
    public void setUser_dd_no(int user_dd_no){
    	this.user_dd_no = user_dd_no;
    }

    public int getUser_needmoney() {
        return user_needmoney;
    }

    public void setUser_needmoney(int user_needmoney) {
        this.user_needmoney = user_needmoney;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUser_maturity() {
        return user_maturity;
    }

    public void setUser_maturity(String user_maturity) {
        this.user_maturity = user_maturity;
    }

    public String getUser_dd_date() {
        return user_dd_date;
    }

    public void setUser_dd_date(String user_dd_date) {
        this.user_dd_date = user_dd_date ;
    }

    public int getUser_dd_amt() {
        return user_dd_amt;
    }

    public void setUser_dd_amt(int user_dd_amt) {
        this.user_dd_amt = user_dd_amt;
    }
    
    public String getUser_int_type(){
    	return user_int_type;
    }
    
    public void setUser_int_type(String user_int_type){
    	this.user_int_type = user_int_type;
    }
    public float getUser_actual_rate(){
    	return user_actual_rate;
    }
    
    public void setUser_actual_rate( float user_actual_rate){
    	this.user_actual_rate = user_actual_rate;
    }
    
    public float getUser_min_rate(){
    	return user_min_rate;
    }
    
    public void setUser_min_rate(float user_min_rate){
    	this.user_min_rate = user_min_rate;
    }
    
    public float getUser_max_rate(){
    	return user_max_rate;
    }
    
    public void setUser_max_rate(float user_max_rate){
    	this.user_max_rate = user_max_rate;
    }

}
