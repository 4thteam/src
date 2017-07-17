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

import com.dao.CilentDAO;
import com.dao.UserDAO;
import com.factory.DAOFactory;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class New_Cilent
 */

public class New_Cilent_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private String url = "127.0.0.1";
	    private int port = 9999;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New_Cilent_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		///以下是获取表单的数据
		String ch_name =(String)request.getAttribute("ch_name");
		String en_name=	(String)request.getAttribute("en_name");
		String sex=(String)request.getAttribute("sex");
		String idcard_type=(String)request.getAttribute("idcard_type");
		String idcard_num=(String)request.getAttribute("idcard_num");
		String nickname=(String)request.getAttribute("nickname");
		String type=(String)request.getAttribute("type");
		String address=(String)request.getAttribute("address");
		String nat=(String)request.getAttribute("nat");
		String address_num=(String)request.getAttribute("address_num");
		String city=(String)request.getAttribute("city");
		String city_num=(String)request.getAttribute("city_num");
		String phone=(String)request.getAttribute("phone");
		String email=(String)request.getAttribute("email");
		//建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> NewCilentMsg = new HashMap<String,String>();
        NewCilentMsg.put("ch_name", ch_name);
        NewCilentMsg.put("en_name", en_name);
        NewCilentMsg.put("sex", sex);
        NewCilentMsg.put("idcard_type", idcard_type);
        NewCilentMsg.put("idcard_num", idcard_num);
        NewCilentMsg.put("nickname", nickname);
        NewCilentMsg.put("type", type);
        NewCilentMsg.put("address", address);
        NewCilentMsg.put("nat", nat);
        NewCilentMsg.put("address_num", address_num);
        NewCilentMsg.put("city", city);
        NewCilentMsg.put("city_num", city_num);
        NewCilentMsg.put("phone", phone);
        NewCilentMsg.put("email", email);
        NewCilentMsg.put("action", "NewCilent");
        JSONObject jsonObject = JSONObject.fromObject(NewCilentMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);
        JSONObject CilentMsg = JSONObject.fromObject(result);
        int is_vailable =CilentMsg.getInt("is_vailable");
		PrintWriter out=response.getWriter();
		if(is_vailable==1)out.println("新用户登记成功");
		else out.println("用户已经存在");
		out.flush();
		out.close();
		
	}

}
