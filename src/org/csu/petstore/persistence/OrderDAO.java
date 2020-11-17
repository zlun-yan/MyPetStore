package org.csu.petstore.persistence;

import org.csu.petstore.domain.Order;

import java.util.List;

public interface OrderDAO {
    Order getOrderByOrderId(int orderId);

    List<Order> getOrdersByUserId(String userId);

    boolean insertOrder(Order order);

    boolean updateOrder(Order order);

//    这个方法以后有时间再加
//    List<Order> getOrderListByItemId(String itemId);
    int getMaxOrderId();
}
