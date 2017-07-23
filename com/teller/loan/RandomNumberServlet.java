package com.teller.loan;

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
* 是用来从数据库里得到发放号
* */
@WebServlet(name = "RandomNumber", urlPatterns = {"/random_number"})
public class RandomNumberServlet extends HttpServlet {
    private String url = "127.0.0.1";
    private int port = 9999;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> randomnumberMsg = new HashMap<>();
        randomnumberMsg.put("action", "random_number");
        JSONObject jsonObject = JSONObject.fromObject(randomnumberMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

        //接受处理结果
        JSONObject randomnumberMsg1 = JSONObject.fromObject(result);
        String num = randomnumberMsg1.getString("loangrant_1");

        response.setContentType("text/html;charaset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(num);
        out.flush();
        out.close();
    }

}
