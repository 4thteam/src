package com.teller.loan;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet(name = "loan_settlement_read",urlPatterns = "/loan_settlement_read")
public class LoanSettementReadServlet extends HttpServlet {
	private String url = "127.0.0.1";
	private int port = 9999;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取表单数据
		String LOAN_NO = request.getParameter("LOAN_NO");
		String DD_NO = request.getParameter("DD_NO");

		// 建立socket通信，连接服务器
		Socket socket = new Socket(url, port);
		System.out.println("TELLER端已经成功的连接到ESB端！");

		//封装输入输出流
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));


		// 转换成Json格式
		Map<String, String> loansettementMsg = new HashMap();
		loansettementMsg.put("LOAN_NO", LOAN_NO);
		loansettementMsg.put("DD_NO", DD_NO);
		loansettementMsg.put("action", "LoanSettementRead");
		JSONObject jsonObject = JSONObject.fromObject(loansettementMsg);

		// 向socket通道写入消息
		printWriter.println(jsonObject.toString());
		System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

		// 从socket通道取出后端返回的结果
		String result = bufferedReader.readLine();
		System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

		// 将结果返回给jsp页面显示
		JSONObject loansettementMsg1 = JSONObject.fromObject(result);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(loansettementMsg1.toString());
		out.flush();
		out.close();
	}
}
