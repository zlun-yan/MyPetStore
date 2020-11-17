package org.csu.petstore.domain;

import java.math.BigDecimal;
import java.util.List;

public class OrderList {
    private String userId;
    private List<Order> orders;
    private BigDecimal subTotal;

    public OrderList() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
