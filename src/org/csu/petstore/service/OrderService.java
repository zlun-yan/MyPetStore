package org.csu.petstore.service;

import org.csu.petstore.domain.Order;
import org.csu.petstore.domain.OrderList;
import org.csu.petstore.persistence.Impl.OrderDAOImpl;
import org.csu.petstore.persistence.OrderDAO;

public class OrderService {
    OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAOImpl();
    }

    public int insertOrder(Order order) {
        orderDAO.insertOrder(order);
        return orderDAO.getMaxOrderId();
    }

    public OrderList getOrderListByUserId(String userId) {
        OrderList orderList = new OrderList();
        orderList.setUserId(userId);
        orderList.setOrders(orderDAO.getOrdersByUserId(userId));
        return orderList;
    }

    public Order getOrderByOrderId(int orderId) {
        return orderDAO.getOrderByOrderId(orderId);
    }

    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }
}
