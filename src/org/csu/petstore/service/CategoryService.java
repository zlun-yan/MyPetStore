package org.csu.petstore.service;

import org.csu.petstore.domain.Category;
import org.csu.petstore.domain.Item;
import org.csu.petstore.domain.Product;
import org.csu.petstore.persistence.CategoryDAO;
import org.csu.petstore.persistence.Impl.CategoryDAOImpl;
import org.csu.petstore.persistence.Impl.ItemDAOImpl;
import org.csu.petstore.persistence.Impl.ProductDAOImpl;
import org.csu.petstore.persistence.ItemDAO;
import org.csu.petstore.persistence.ProductDAO;

import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAOImpl();
        productDAO = new ProductDAOImpl();
        itemDAO = new ItemDAOImpl();
    }
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    public Product getProduct(String productId) {
        return productDAO.getProductByProductId(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategoryId(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductListWithName("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProductId(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItemByItemId(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantityByItemId(itemId) > 0;
    }
}
