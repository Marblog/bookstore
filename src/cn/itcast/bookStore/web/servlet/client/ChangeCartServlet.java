package cn.itcast.bookStore.web.servlet.client;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.bookStore.domain.Product;

public class ChangeCartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        int count = Integer.parseInt(request.getParameter("count"));
        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

        Product p = new Product();
        p.setId(id);

        if (count != 0) {
            cart.put(p, count);
        } else {
            cart.remove(p);
        }

        response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
    }
}
