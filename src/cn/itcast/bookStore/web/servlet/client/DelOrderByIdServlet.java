package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.service.OrderService;

public class DelOrderByIdServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        OrderService service = new OrderService();
        service.delOrderByIdWithClient(id);
        response.sendRedirect(request.getContextPath() + "/client/delOrderSuccess.jsp");
    }
}
