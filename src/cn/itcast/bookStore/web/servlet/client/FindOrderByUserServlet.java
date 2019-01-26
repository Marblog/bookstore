package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.service.OrderService;

public class FindOrderByUserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        OrderService service = new OrderService();
        List<Order> orders = service.findOrderByUser(user);

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/client/orderlist.jsp").forward(request, response);
    }
}
