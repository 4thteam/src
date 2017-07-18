package com.teller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MenusDAO;
import com.entity.Menus;
import com.factory.DAOFactory;

import net.sf.json.JSONObject;

/*
 * Created by 李元富 on 2017/7/17.
 */

@WebServlet(name = "ReturnBalanceServlet", urlPatterns = {"/returnBalance"})
public class ReturnBalanceServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 private String url = "127.0.0.1";
	 private int port = 9999;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获得前端传过来的客户账号
		String cilent_card_id = req.getParameter("cilent_card_id");
		
		 //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");
        
        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        
        //转换成Json格式
        Map<String, String> balanceMsg = new HashMap<>();
        balanceMsg.put("cilent_card_id", cilent_card_id);
        balanceMsg.put("action", "balance");
        JSONObject jsonObject = JSONObject.fromObject(balanceMsg);
        
        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");
        
        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);
        
      //处理返回结果
        JSONObject userBalanceMsg = JSONObject.fromObject(result);
        int user_balance = userBalanceMsg.getInt("balance");
        System.out.println(user_balance);
        
        //将结果返回给jsp页面显示
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(user_balance);
        out.flush();
        out.close();
        
	}
	
}
