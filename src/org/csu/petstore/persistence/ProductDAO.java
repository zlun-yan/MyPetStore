package org.csu.petstore.persistence;

import org.csu.petstore.domain.Product;

import java.util.List;

public interface ProductDAO {
    /**
     * 通过种类Id查询 属于这个种类的所有商品
     * @param categoryId
     * @return 返回这个类别商品的List
     */
    List<Product> getProductListByCategoryId(String categoryId);

    /**
     * 通过product的Id查询
     * @param productId
     * @return
     */
    Product getProductByProductId(String productId);

    /**
     * 通过product的名称的关键词查询
     * @param keyword
     * @return
     */
    List<Product> searchProductListWithName(String keyword);
}
