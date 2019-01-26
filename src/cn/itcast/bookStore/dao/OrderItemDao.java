package cn.itcast.bookStore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.domain.OrderItem;
import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class OrderItemDao {

    public void addOrderItem(Order order) throws SQLException {
        String sql = "insert into orderItem values(?,?,?)";
        QueryRunner runner = new QueryRunner();
        List<OrderItem> items = order.getOrderItems();
        Object[][] params = new Object[items.size()][3];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getOrder().getId();
            params[i][1] = items.get(i).getP().getId();
            params[i][2] = items.get(i).getBuynum();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    public List<OrderItem> findOrderItemByOrder(final Order order) throws SQLException {
        String sql = "select * from orderItem,Products where products.id=orderItem.product_id and order_id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, rs -> {
            List<OrderItem> items = new ArrayList<>();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setBuynum(rs.getInt("buynum"));

                Product p = new Product();
                p.setCategory(rs.getString("category"));
                p.setId(rs.getString("id"));
                p.setDescription(rs.getString("description"));
                p.setImgurl(rs.getString("imgurl"));
                p.setName(rs.getString("name"));
                p.setPnum(rs.getInt("pnum"));
                p.setPrice(rs.getDouble("price"));
                item.setP(p);

                items.add(item);
            }
            return items;
        }, order.getId());
    }

    public void delOrderItems(String id) throws SQLException {
        String sql = "delete from orderItem where order_id=?";
        QueryRunner runner = new QueryRunner();
        runner.update(DataSourceUtils.getConnection(), sql, id);
    }
}