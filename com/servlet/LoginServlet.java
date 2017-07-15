package com.servlet;

import com.dao.MenusDAO;
import com.dao.UserDAO;
import com.entity.Menus;
import com.entity.User;
import com.factory.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 余文彪 on 2017/7/13.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        User user = userDAO.findUserByUseridAndUser_passwod(user_id, password);
        if (user.getUser_name() != null) {
            request.getSession().setAttribute("user", user.getUser_name());
            request.getSession().setAttribute("role", user.getUser_role());
            MenusDAO menusDAO = DAOFactory.getMenusDAOInstance();
            List<Menus> menusList = menusDAO.findMenusById(user_id);
            request.setAttribute("menus", menusList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login.html");
        }
    }
}