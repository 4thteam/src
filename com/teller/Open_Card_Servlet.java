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
import com.factory.DAOFactory;

import net.sf.json.JSONObject;



public class Open_Card_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String url = "127.0.0.1";
    private int port = 9999;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Open_Card_Servlet() {
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
		String ch_name=(String)request.getAttribute("ch_name");
		String id_card_num=(String)request.getAttribute("id_card_num");
		String card_type=(String)request.getAttribute("card_type");
		String phone=(String)request.getAttribute("phone");
		String card_password=(String)request.getAttribute("card_password");
		String net_id=request.getSession().getAttribute("net_id").toString();
		  //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> CardMsg = new HashMap<String,String>();
        CardMsg.put("ch_name",ch_name );
        CardMsg.put("id_card_num",id_card_num );
        CardMsg.put("card_type",card_type );
        CardMsg.put("phone",phone );
        CardMsg.put("card_password",card_password );
        CardMsg.put("net_id",net_id );
        CardMsg.put("action", "NewCard");
        JSONObject jsonObject = JSONObject.fromObject(CardMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

        //处理返回结果
        JSONObject NewCardMsg = JSONObject.fromObject(result);
        int is_vailable = NewCardMsg.getInt("is_vailable");
       

        //将结果返回给jsp页面显示
		
		PrintWriter out=response.getWriter();
		if(is_vailable==1)out.println("银行卡开户成功");
		if(is_vailable==0)out.println("该用户为新用户请先登记新用户");
		if(is_vailable==2)out.println("同一个人在同一家银行(以法人为单位)只能开立一个Ⅰ类账户,请办理Ⅱ类或Ⅲ类");
		out.flush();
		out.close();
	}

}
