package cn.itcast.bookStore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.domain.OrderItem;
import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class ProductDao {

    public List<Object[]> getWeekHotProduct() throws SQLException {
        String sql = "SELECT products.id,products.name, " +
                " products.imgurl,SUM(orderitem.buynum) totalsalnum " +
                " FROM orderitem,orders,products " +
                " WHERE orderitem.order_id = orders.id" +
                " AND products.id = ordeitem.product_id" +
                " AND orders.paystate=1 " +
                " AND orders.ordertime > DATE_SUB(NOW(), INTERVAL 7 DAY) " +
                " GROUP BY products.id,products.name,product.imgurl " +
                " ORDER BY totalsalnum DESC " +
                " LIMIT 0,2 ";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ArrayListHandler());
    }

    public int findAllCount(String category) throws SQLException {
        String sql = "select count(*) from products";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        if (!"全部商品".equals(category)) {
            sql += "where category=?";

            Long count = (Long) runner
                    .query(sql, new ScalarHandler(), category);

            return count.intValue();
        } else {
            Long count = (Long) runner.query(sql, new ScalarHandler());

            return count.intValue();
        }
    }

    public List<Product> findByPage(int currentPage, int currentCount, String category) throws SQLException {
        String sql = null;
        Object[] obj = null;
        if (!"全部商品".equals(category)) {
            sql = "select * from products where category=? limit ?,?";
            obj = new Object[]{category, (currentPage - 1) * currentCount,
                    currentCount,};
        } else {
            sql = "select * from products limit ?,?";
            obj = new Object[]{(currentPage - 1) * currentCount,
                    currentCount,};
        }
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class),
                obj);
    }

    public Product findProductById(String id) throws SQLException {
        String sql = "select * from products where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<Product>(Product.class), id);
    }

    public int findBookByNameAllCount(String searchfield) throws SQLException {
        String sql = "SELECT COUNT(*) FROM products WHERE name LIKE '%" + searchfield + "%'";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        Long count = (Long) runner.query(sql, new ScalarHandler());
        return count.intValue();
    }

    public List<Product> findBookByName(int currentPage, int currentCount, String searchfield) throws SQLException {
        String sql = "SELECT * FROM products WHERE name LIKE '%" + searchfield + "%' LIMIT ?,?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class), currentPage - 1, currentCount);
    }

    public void changeProductNum(Order order) throws SQLException {
        String sql = "update prducts set pnum=pnum-? where id=?";
        QueryRunner runner = new QueryRunner();
        List<OrderItem> items = order.getOrderItems();
        Object[][] params = new Object[items.size()][2];

        getA(items, sql, runner, params);
    }

    public void updateProductNum(List<OrderItem> items) throws SQLException {
        String sql = "update products set pnum=pnum+? where id=?";
        QueryRunner runner = new QueryRunner();

        Object[][] params = new QueryRunner[items.size()][2];

        getA(items, sql, runner, params);

    }

    private void getA(List<OrderItem> items, String sql, QueryRunner runner, Object[][] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getBuynum();
            params[i][1] = items.get(i).getP().getId();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }
}
