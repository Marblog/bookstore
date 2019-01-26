package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.service.UserService;

public class ModifyUserInfoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");

        User user = (User) request.getSession().getAttribute("user");
        user.setPassword(password);
        user.setGender(sex);
        user.setTelephone(tel);
        UserService userService = new UserService();

        try {
            int n = userService.updateUser(user);
            if (n == 1) {
                request.getRequestDispatcher("/client/modifyuserinfosuccess.js").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
