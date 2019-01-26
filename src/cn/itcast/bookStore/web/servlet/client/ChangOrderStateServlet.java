package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.service.OrderService;

public class ChangOrderStateServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderid = request.getParameter("orderid");
        OrderService orderService = new OrderService();
        orderService.changeOrderstate(orderid);
        request.getRequestDispatcher("/findOrderByUser").forward(request, response);
    }
}
