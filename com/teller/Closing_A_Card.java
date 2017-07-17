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

/**
 * Servlet implementation class Closing_A_Card
 */
@WebServlet("/Closing_A_Card")
public class Closing_A_Card extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String url = "127.0.0.1";
    private int port = 9999;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Closing_A_Card() {
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
		  //获取表单数据
        String ch_name = request.getParameter("ch_name");
        String idcard_num=request.getParameter("idcard_num");
        String card_id=request.getParameter("card_id");
        String password = request.getParameter("password");

        //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> ClosingMsg = new HashMap<String,String>();
        ClosingMsg.put("ch_name",ch_name );
        ClosingMsg.put("idcard_num", idcard_num);
        ClosingMsg.put("card_id", card_id);
        ClosingMsg.put("password", password);
        ClosingMsg.put("action", "ClosingCard");
        JSONObject jsonObject = JSONObject.fromObject(ClosingMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

        //处理返回结果
        JSONObject ClosingCardMsg = JSONObject.fromObject(result);
        int is_vailable  = ClosingCardMsg.getInt("is_vailable");
        PrintWriter out=response.getWriter();
        if(is_vailable==1)out.println("银行卡销户成功");
		if(is_vailable==0)out.println("银行卡号错误或者用户信息错误");
		if(is_vailable==2)out.println("卡内还有余额或欠费请取款或缴费后销户");
		out.flush();
		out.close();
       

       
	}

}
