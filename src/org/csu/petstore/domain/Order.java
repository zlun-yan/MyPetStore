package org.csu.petstore.domain;

import org.csu.petstore.persistence.Impl.ItemDAOImpl;
import org.csu.petstore.persistence.Impl.UserDAOImpl;
import org.csu.petstore.persistence.ItemDAO;
import org.csu.petstore.persistence.UserDAO;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int orderId;
    private String userId;
    private String state;
    private Date orderDate;
    private int addressId;
    private String itemId;
    private int quantity;
    private BigDecimal totPrice;

    private Address address;
    private Item item;

    public Order() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
        UserDAO userDAO = new UserDAOImpl();
        address = userDAO.getAddressById(addressId);
    }

    public Address getAddress() {
        return address;
    }

    public Item getItem() {
        return item;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
        ItemDAO itemDAO = new ItemDAOImpl();
        item = itemDAO.getItemByItemId(itemId);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(BigDecimal totPrice) {
        this.totPrice = totPrice;
    }
}
