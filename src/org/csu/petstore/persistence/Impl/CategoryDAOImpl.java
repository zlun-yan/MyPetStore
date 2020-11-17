package org.csu.petstore.persistence.Impl;

import org.csu.petstore.domain.Category;
import org.csu.petstore.persistence.CategoryDAO;
import org.csu.petstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String GET_CATEGORY_LIST =
            "SELECT CATEGORYID, CNAME, DESCRIPTION FROM CATEGORY";
    private static final String GET_CATEGORY_BY_ID =
            "SELECT CATEGORYID, CNAME, DESCRIPTION FROM CATEGORY WHERE CATEGORYID = ?";

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CATEGORY_LIST);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));

                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return categoryList;
    }

    @Override
    public Category getCategoryById(String categoryId) {
        Category category = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_ID);
            preparedStatement.setString(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return category;
    }
}
