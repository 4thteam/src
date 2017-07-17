package com.teller;

import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.entity.Menus;
import com.entity.User;
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

/**
 * Created by 余文彪 on 2017/7/13.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private String url = "127.0.0.1";
    private int port = 9999;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单数据
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");

        //建立socket通信，连接服务器
        Socket socket = new Socket(url, port);
        System.out.println("TELLER端已经成功的连接到ESB端！");

        //封装输入输出流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        //转换成Json格式
        Map<String, String> loginMsg = new HashMap<>();
        loginMsg.put("user_id", user_id);
        loginMsg.put("password", password);
        loginMsg.put("action", "login");
        JSONObject jsonObject = JSONObject.fromObject(loginMsg);

        //向socket通道写入消息
        printWriter.println(jsonObject.toString());
        System.out.println("TELLER端已经成功的向ESB端发送消息  " + "");

        //从socket通道取出后端返回的结果
        String result = bufferedReader.readLine();
        System.out.println("TELLER端已经成功的从ESB端接收到响应消息 " + result);

        //处理返回结果
        JSONObject userMsg = JSONObject.fromObject(result);
        String user_name = userMsg.getString("user");
        String user_role = userMsg.getString("role");
        System.out.println(user_name);

        //将结果返回给jsp页面显示
        if (user_name != null) {
            request.getSession().setAttribute("user", user_name);
            request.getSession().setAttribute("role", user_role);
            MenusDAO menusDAO = DAOFactory.getMenusDAOInstance();
            List<Menus> menusList = menusDAO.findMenusById(user_id);
            request.setAttribute("menus", menusList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login.html");
        }
    }
}