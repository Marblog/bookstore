package cn.itcast.bookStore.web.servlet.client;

import cn.itcast.bookStore.domain.Notice;
import cn.itcast.bookStore.service.NoticeService;
import cn.itcast.bookStore.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowIndexServlet")
public class ShowIndexServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        NoticeService nService = new NoticeService();
        Notice notice = nService.getRecentNotice();
        req.setAttribute("n", notice);
        ProductService pService = new ProductService();
        List<Object[]> pList = pService.getWeekHotProduct();
        req.setAttribute("pList", pList);
        req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
    }
}
