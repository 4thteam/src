package com.teller.loan;

import com.dao.LoangrantDAO;
import com.entity.Loangrant;
import com.factory.DAOFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/*
* 当从界面输入利率编号，然后从数据库里读取 当前利率，最小利率，最大利率
* */
@WebServlet(name = "ReadRateServlet", urlPatterns = "/read_rate")
public class ReadRateServlet extends HttpServlet {

    private String url = "127.0.0.1";
    private int port = 9999;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单数据
        String user_int_type = request.getParameter("user_int_type");

        //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> readrateMsg = new HashMap<>();
        readrateMsg.put("user_int_type", user_int_type);
        readrateMsg.put("action", "read_rate");
        JSONObject jsonObject = JSONObject.fromObject(readrateMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

        //接受处理结果
        JSONObject readrateMsg1 = JSONObject.fromObject(result);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(readrateMsg1.toString());
        out.flush();
        out.close();
    }
}
