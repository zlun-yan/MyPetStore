package org.csu.petstore.domain;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    // 这整个POJO都是非数据库中的内容
    private String userId;
    private List<CartItem> cartItems;

    private BigDecimal subTotal;

    private int numberOfItems;

    public Cart() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
