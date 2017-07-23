package com.teller.loan;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * 是用来将界面里的信息，放入数据库里的
 */
@WebServlet(name = "NewTable",urlPatterns = "/new_table")
public class NewTableServlet extends HttpServlet{

    private String url = "127.0.0.1";
    private int port = 9999;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 //获取表单数据
        String user_dd_no = request.getParameter("user_dd_no");//发放号
    	String user_name = request.getParameter("user_name");//贷款人
    	String user_maturity = request.getParameter("user_maturity");//到期日
    	String user_dd_date = request.getParameter("user_dd_date");//发放日期
    	String user_dd_amt = request.getParameter("user_dd_amt");//发放金额
    	String user_actual_rate = request.getParameter("user_actual_rate");//利息利率
    	String user_basis_rate = request.getParameter("user_basis_rate");//罚息利率
    	String user_plty_rate = request.getParameter("user_plty_rate");//复息利率

        //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> newtableMsg = new HashMap<>();
        
        newtableMsg.put("user_dd_no", user_dd_no);
        newtableMsg.put("user_name",user_name);
        newtableMsg.put("user_maturity",user_maturity);
        newtableMsg.put("user_dd_date",user_dd_date);
        newtableMsg.put("user_dd_amt",user_dd_amt);
        newtableMsg.put("user_actual_rate",user_actual_rate);
        newtableMsg.put("user_basis_rate",user_basis_rate);
        newtableMsg.put("user_plty_rate",user_plty_rate);
        
        newtableMsg.put("action", "newtable");
        JSONObject jsonObject = JSONObject.fromObject(newtableMsg);
        
        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

        //将消息反馈给前端
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
    
}
