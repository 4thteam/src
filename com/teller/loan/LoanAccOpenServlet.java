package com.teller.loan;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 贷款开立
 */
@WebServlet(name = "LoanAccOpenServlet", urlPatterns = "/loanAcc")
public class LoanAccOpenServlet extends HttpServlet {

    private String url = "127.0.0.1";
    private int port = 9999;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单数据
        String contract_no = request.getParameter("contract_no");//合同号
        String loan_type = request.getParameter("loan_type");//贷款类型
        String loan_sub_type = request.getParameter("loan_sub_type");//贷款子类型
        String borrower = request.getParameter("borrower");//贷款人编号
        String book_branch = request.getParameter("book_branch");//贷款签约银行
        String lender = request.getParameter("lender");//借款人
        String ccy = request.getParameter("ccy");//账户币种
        String start_date = request.getParameter("start_date");//贷款开始时间
        String mature_date = request.getParameter("mature_date");//贷款到期日期
        String loan_amt = request.getParameter("loan_amt");//贷款金额
        String drawn_amt = request.getParameter("drawn_amt");//当前发放额
        String int_rate = request.getParameter("int_rate");//贷款当前利率
        String billing_days = request.getParameter("billing_days");//提前出单天数
        String month_basis = request.getParameter("month_basis");//基准月天数
        String year_basis = request.getParameter("year_basis");//基准年天数

        //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> loanMsg = new HashMap<>();
        loanMsg.put("month_basis", month_basis);
        loanMsg.put("billing_days", billing_days);
        loanMsg.put("int_rate", int_rate);
        loanMsg.put("drawn_amt", drawn_amt);
        loanMsg.put("loan_amt", loan_amt);
        loanMsg.put("mature_date", mature_date);
        loanMsg.put("start_date", start_date);
        loanMsg.put("ccy", ccy);
        loanMsg.put("lender", lender);
        loanMsg.put("book_branch", book_branch);
        loanMsg.put("borrower", borrower);
        loanMsg.put("loan_sub_type", loan_sub_type);
        loanMsg.put("loan_type", loan_type);
        loanMsg.put("contract_no", contract_no);
        loanMsg.put("year_basis", year_basis);
        loanMsg.put("action", "LoanAccOpen");
        JSONObject jsonObject = JSONObject.fromObject(loanMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息" + " ");

        //从socket通道去除后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息" + result);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (result!=null){
            out.println(result);
        }else {
            out.println("贷款开立失败！");
        }
        out.flush();
        out.close();
    }
}
