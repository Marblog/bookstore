package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.service.OrderService;

public class FindOrderByIdServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String id = request.getParameter("id");
        OrderService service = new OrderService();
        Order order = service.findOrderById(id);
        request.setAttribute("order", order);

        if (type != null) {
            request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/client/orderInfo.jsp").forward(request, response);
    }
}
