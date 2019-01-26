package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.exception.RegisterException;
import cn.itcast.bookStore.service.UserService;
import cn.itcast.bookStore.utils.ActiveCodeUtils;

public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            user.setActiveCode(ActiveCodeUtils.createActiveCode());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserService();
        try {
            service.register(user);
        } catch (RegisterException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
            return;
        }
        response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
    }

}
