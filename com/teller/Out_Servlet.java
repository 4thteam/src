package com.teller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.factory.DAOFactory;

/**
 * 柜员登出
 */
@WebServlet(name = "OutServlet", urlPatterns = {"/out"})
public class Out_Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取url中用户信息
        String login_time = request.getParameter("login_time");
        String user_id = request.getParameter("user_id");
        String net_id = request.getParameter("net_id");

        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTime = new java.util.Date();//得到当前系统时间
        String date = formatter.format(currentTime); //将日期时间格式化
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        userDAO.SignIn(user_id, net_id, request.getRemoteAddr(), login_time, date);
        response.sendRedirect("/login.html");
    }
}
