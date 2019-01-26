package cn.itcast.bookStore.web.servlet.client;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.exception.FindProductByIdException;
import cn.itcast.bookStore.service.ProductService;

public class AddCartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ProductService service = new ProductService();
        try {
            Product p = service.findProductById(id);
            HttpSession session = request.getSession();
            Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
            }
        } catch (FindProductByIdException e) {
            e.printStackTrace();
        }
    }
}