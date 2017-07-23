package com.dao;

import com.entity.Loangrant;

public interface LoangrantDAO {

	public String RandomNumber();

	public Loangrant findUserbyloan_no(String loan_no);

	public Loangrant writeTimeand_dd_amt(String maturity, String dd_date,
			int dd_amt);

	public Loangrant readrate(String int_type);

	public String NewTable(int dd_no, String name, String maturity,
			String dd_date, int dd_amt, double actual_rate, double basis_rate,
			double plty_rate);
}
