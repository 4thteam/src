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
 * 登录功能
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单数据
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String net_id = request.getParameter("net_id");

        //从数据库判断用户是否存在
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        User user = userDAO.findUserByUseridAndUser_passwod(user_id, password);
        if (user.getUser_name() != null) {
            request.getSession().setAttribute("user", user.getUser_name());
            request.getSession().setAttribute("role", user.getUser_role());
            request.getSession().setAttribute("user_id", user_id);
            request.getSession().setAttribute("net_id", net_id);
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date currentTime = new java.util.Date();
            String date = formatter.format(currentTime);
            request.getSession().setAttribute("login_time", date);

            //获取菜单集合
            MenusDAO menusDAO = DAOFactory.getMenusDAOInstance();
            List<Menus> menusList = menusDAO.findMenusById(user_id);
            request.setAttribute("menus", menusList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login.html");
        }
    }
}