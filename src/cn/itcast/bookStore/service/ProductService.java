package cn.itcast.bookStore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookStore.dao.ProductDao;
import cn.itcast.bookStore.domain.PageBean;
import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.exception.FindProductByIdException;

public class ProductService {

    private ProductDao dao = new ProductDao();

    public List<Object[]> getWeekHotProduct() {
        try {
            return dao.getWeekHotProduct();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("前台获取本周热销商品失败！");
        }
    }

    public PageBean findProductByPage(int currentPage, int currentCount, String category) {
        PageBean bean = new PageBean();
        bean.setCurrentCount(currentCount);
        bean.setCurrentPage(currentPage);
        bean.setCategory(category);

        try {
            int totalCount = dao.findAllCount(category);
            bean.setTotalCount(totalCount);
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            List<Product> ps = dao.findByPage(currentPage, currentCount, category);
            bean.setPs(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public Product findProductById(String id) throws FindProductByIdException {
        try {
            return dao.findProductById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据ID查找商品失败");
        }
    }

    public PageBean findBookByName(int currentPage, int currentCount, String searchfield) {
        PageBean bean = new PageBean();
        bean.setCurrentCount(currentCount);
        bean.setCurrentPage(currentPage);
        bean.setSearchfield(searchfield);
        try {
            int totalCount = dao.findBookByNameAllCount(searchfield);
            bean.setTotalCount(totalCount);
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            List<Product> ps = dao.findBookByName(currentPage, currentCount, searchfield);
            bean.setPs(ps);
            return bean;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("前台搜索框根据书名查询图书失败！");
        }

    }
}