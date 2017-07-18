package com.teller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/*
 * Created by 李元富 on 2017/7/17
 */
@WebServlet(name = "DepositServlet", urlPatterns = {"/deposit"})
public class DepositServlet extends HttpServlet{
	
	private String url = "127.0.0.1";
    private int port = 9999;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取表单数据
		String cilent_card_id = req.getParameter("cilent_card_id");
		String cilent_card_password = req.getParameter("cilent_card_password");
		int money = Integer.parseInt(req.getParameter("money"));
		
		//建立scoket通信，连接服务器
		Socket socket = new Socket(url,port);
		System.out.println("TELLER端已经成功的连接到ESB端");
		
		//封装输入输出流
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		
		//转换成Json格式
		 Map<String, Object> depositMsg = new HashMap<>();
	     depositMsg.put("cilent_card_id", cilent_card_id);
	     depositMsg.put("cilent_card_password", cilent_card_password);
	     depositMsg.put("money", money);
	     depositMsg.put("action", "deposit");
	     JSONObject jsonObject = JSONObject.fromObject(depositMsg);
	     
	     //向socket通道写入消息
	     printWriter.println(jsonObject.toString());
	     System.out.println("TELLER端已经成功向ESB端发送消息");
	     
	     //从socket通道取出返回的结果
	     String result = bufferedReader.readLine();
	     System.out.println("TELLER端已经成功从ESB端接收到响应信息"+result);
	     
	     //处理返回结果
	     JSONObject userDepositMsg = JSONObject.fromObject(result);
	     String depositIsSuccess = userDepositMsg.getString("depositMessage");
	     System.out.println(depositIsSuccess);
	     
	     //将结果返回给jsp页面显示
	     resp.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = resp.getWriter();
	     out.println(depositIsSuccess);
	     out.flush();
	     out.close();

	}
	
}
