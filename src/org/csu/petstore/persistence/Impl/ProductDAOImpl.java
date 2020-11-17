package org.csu.petstore.persistence.Impl;

import org.csu.petstore.domain.Product;
import org.csu.petstore.persistence.DBUtil;
import org.csu.petstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String GET_PRODUCT_BY_PRODUCTID =
            "SELECT PRODUCTID, CATEGORYID, PNAME, DESCRIPTION FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String GET_PRODUCT_LIST_BY_CATEGORY =
            "SELECT PRODUCTID, CATEGORYID, PNAME, DESCRIPTION FROM PRODUCT WHERE CATEGORYID = ?";
    private static final String SEARCH_PRODUCT_LIST =
            "SELECT PRODUCTID, CATEGORYID, PNAME, DESCRIPTION FROM PRODUCT WHERE LOWER(PNAME) LIKE ?";

    @Override
    public List<Product> getProductListByCategoryId(String categoryId) {
        List<Product> productList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            preparedStatement.setString(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setCategoryId(resultSet.getString(2));
                product.setName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));

                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return productList;
    }

    @Override
    public Product getProductByProductId(String productId) {
        Product product = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_PRODUCTID);
            preparedStatement.setString(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setCategoryId(resultSet.getString(2));
                product.setName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return product;
    }

    @Override
    public List<Product> searchProductListWithName(String keywords) {
        List<Product> productList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            preparedStatement.setString(1, keywords);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setCategoryId(resultSet.getString(2));
                product.setName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));

                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return productList;
    }
}
