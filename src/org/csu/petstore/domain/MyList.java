package org.csu.petstore.domain;

import java.util.List;

public class MyList {
    private String categoryId;
    private List<Product> productList;

    public MyList() {}

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
