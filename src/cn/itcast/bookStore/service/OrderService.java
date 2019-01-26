package cn.itcast.bookStore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookStore.dao.OrderDao;
import cn.itcast.bookStore.dao.OrderItemDao;
import cn.itcast.bookStore.dao.ProductDao;
import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.domain.OrderItem;
import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class OrderService {
	
	private OrderItemDao oidao = new OrderItemDao();
	private OrderDao odao = new OrderDao();
	private ProductDao pdao = new ProductDao();

	public void addOrder(Order order) {
		
		try {
			DataSourceUtils.startTransaction();
			odao.addProduct(order);
			oidao.addOrderItem(order);
			pdao.changeProductNum(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Order> findOrderByUser(User user) {
		List<Order> orders = null;
		try {
			orders = odao.findOrderByUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	public Order findOrderById(String id) {
		Order order = null;
		try {
			order = odao.findOrderById(id);
			List<OrderItem> items = oidao.findOrderItemByOrder(order);
			order.setOrderItems(items);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	public void changeOrderstate(String orderid) {
		try {
			odao.changOrderState(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delOrderByIdWithClient(String id) {
		try {
			DataSourceUtils.startTransaction();
			Order order=new Order();
			order.setId(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			pdao.updateProductNum(items);
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
