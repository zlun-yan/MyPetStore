package org.csu.petstore.persistence.Impl;

import org.csu.petstore.domain.Item;
import org.csu.petstore.domain.Product;
import org.csu.petstore.persistence.DBUtil;
import org.csu.petstore.persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    private static final String UPDATE_INVENTORY_QUANTITY_BY_ITEM_ID =
            "UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE ITEMID = ?";
    private static final String GET_INVENTORY_QUANTITY_BY_ITEM_ID =
            "SELECT QUANTITY FROM INVENTORY WHERE ITEMID = ?";

    private static final String GET_ITEM_LIST_BY_PRODUCT =
            "SELECT " +
                    "I.ITEMID, " +
                    "I.PRODUCTID, " +
                    "P.CATEGORYID, " +
                    "P.PNAME, " +
                    "P.DESCRIPTION, " +
                    "LISTPRICE, " +
                    "UNITPRICE, " +
                    "SUPPLIERID, " +
                    "STATUS, " +
                    "ATTR1, " +
                    "ATTR2, " +
                    "ATTR3, " +
                    "ATTR4, " +
                    "ATTR5 " +
                    "FROM ITEM I, PRODUCT P " +
                    "WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String GET_ITEM =
            "SELECT " +
                    "I.ITEMID, " +
                    "I.PRODUCTID, " +
                    "P.CATEGORYID, " +
                    "P.PNAME, " +
                    "P.DESCRIPTION, " +
                    "LISTPRICE, " +
                    "UNITPRICE, " +
                    "SUPPLIERID, " +
                    "STATUS, " +
                    "ATTR1, " +
                    "ATTR2, " +
                    "ATTR3, " +
                    "ATTR4, " +
                    "ATTR5, " +
                    "V.QUANTITY " +
                    "FROM ITEM I, INVENTORY V, PRODUCT P " +
                    "WHERE P.PRODUCTID = I.PRODUCTID AND I.ITEMID = V.ITEMID AND I.ITEMID = ?";
    @Override
    public boolean updateInventoryQuantityByItemId(Map<String, Object> param) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY_BY_ITEM_ID);

            for (Map.Entry<String, Object> entry : param.entrySet()) {
                preparedStatement.setInt(1, Integer.parseInt(entry.getValue().toString()));
                preparedStatement.setString(2, entry.getKey());

                return preparedStatement.executeUpdate() == 1;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public int getInventoryQuantityByItemId(String itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY_BY_ITEM_ID);
            preparedStatement.setString(1, itemId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return -1;
    }

    @Override
    public List<Item> getItemListByProductId(String productId) {
        List<Item> itemList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            preparedStatement.setString(1, productId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();

                item.setItemId(resultSet.getString(1));
                item.setProductId(resultSet.getString(2));

                Product product = new Product();
                product.setProductId(productId);
                product.setCategoryId(resultSet.getString(3));
                product.setName(resultSet.getString(4));
                product.setDescription(resultSet.getString(5));
                item.setProduct(product);

                item.setListPrice(resultSet.getBigDecimal(6));
                item.setUnitPrice(resultSet.getBigDecimal(7));
                item.setSupplierId(resultSet.getInt(8));
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));

                itemList.add(item);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return itemList;
    }

    @Override
    public Item getItemByItemId(String itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1, itemId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();

                item.setItemId(resultSet.getString(1));
                item.setProductId(resultSet.getString(2));

                Product product = new Product();
                product.setProductId(item.getProductId());
                product.setCategoryId(resultSet.getString(3));
                product.setName(resultSet.getString(4));
                product.setDescription(resultSet.getString(5));
                item.setProduct(product);

                item.setListPrice(resultSet.getBigDecimal(6));
                item.setUnitPrice(resultSet.getBigDecimal(7));
                item.setSupplierId(resultSet.getInt(8));
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                item.setQuantity(resultSet.getInt(15));

                return item;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return null;
    }
}
