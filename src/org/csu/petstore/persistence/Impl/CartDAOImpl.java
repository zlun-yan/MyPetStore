package org.csu.petstore.persistence.Impl;

import org.csu.petstore.domain.Cart;
import org.csu.petstore.domain.CartItem;
import org.csu.petstore.domain.Order;
import org.csu.petstore.persistence.CartDAO;
import org.csu.petstore.persistence.DBUtil;
import org.csu.petstore.persistence.ItemDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static final String GET_CARTITEM_LIST_BY_USERID =
            "SELECT " +
                    "ID, " +
                    "ITEMID, " +
                    "QUANTITY " +
                    "FROM CART " +
                    "WHERE USERID = ?";
    private static final String INSERT_CARTITEM_BY_USERID_ITEMID_QUANTITY =
            "INSERT CART (" +
                    "USERID, " +
                    "ITEMID, " +
                    "QUANTITY) " +
                    "VALUES (?, ?, ?)";
    private static final String GET_CARTITEM_BY_USERID_AND_ITEMID =
            "SELECT " +
                    "ID, " +
                    "QUANTITY " +
                    "FROM CART " +
                    "WHERE USERID = ? AND ITEMID = ?";
    private static final String UPDATE_CARTITEM_BY_USERID_ITEMID_QUANTITY_RELATIVE =
            "UPDATE CART SET " +
                    "QUANTITY = QUANTITY + ? " +
                    "WHERE USERID = ? AND ITEMID = ?";
    private static final String UPDATE_CARTITEM_BY_USERID_ITEMID_QUANTITY_ABSOLUTE =
            "UPDATE CART SET " +
                    "QUANTITY = ? " +
                    "WHERE USERID = ? AND ITEMID = ?";
    private static final String DELETE_CATEITEM_BY_USERID_ITEMID = "DELETE FROM CART WHERE USERID = ? AND ITEMID = ?";
    private static final String GET_CARTITEM_BY_ID =
            "SELECT " +
                    "USERID, " +
                    "ITEMID, " +
                    "QUANTITY " +
                    "FROM CART " +
                    "WHERE ID = ?";
    private static final String DELETE_CARTITEM_BY_ID = "DELETE FROM CART WHERE ID = ?";


    @Override
    public CartItem getCartItemById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CARTITEM_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CartItem cartItem = new CartItem();
                ItemDAO itemDAO = new ItemDAOImpl();

                cartItem.setId(id);
                cartItem.setUserId(resultSet.getString(1));
                cartItem.setItemId(resultSet.getString(2));
                cartItem.setQuantity(resultSet.getInt(3));

                cartItem.setItem(itemDAO.getItemByItemId(cartItem.getItemId()));
                cartItem.setInStock(itemDAO.getInventoryQuantityByItemId(cartItem.getItemId()) >= cartItem.getQuantity());
                cartItem.setTotal(cartItem.getItem().getListPrice().multiply(new BigDecimal(cartItem.getQuantity())));

                return cartItem;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public CartItem getCartItemByUserIdAndItemId(String userId, String itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CARTITEM_BY_USERID_AND_ITEMID);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, itemId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CartItem cartItem = new CartItem();
                ItemDAO itemDAO = new ItemDAOImpl();
                cartItem.setId(resultSet.getInt(1));
                cartItem.setUserId(userId);
                cartItem.setItemId(itemId);
                cartItem.setQuantity(resultSet.getInt(2));

                cartItem.setItem(itemDAO.getItemByItemId(itemId));
                cartItem.setInStock(itemDAO.getInventoryQuantityByItemId(itemId) >= cartItem.getQuantity());
                cartItem.setTotal(cartItem.getItem().getListPrice().multiply(new BigDecimal(cartItem.getQuantity())));

                return cartItem;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return null;
    }

    @Override
    public Cart getCartByUserId(String userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCartItems(getCartItemListByUserId(userId));
        cart.setNumberOfItems(cart.getCartItems().size());

        BigDecimal subTotal = new BigDecimal(0);
        for (CartItem cartItem : cart.getCartItems()) subTotal = subTotal.add(cartItem.getTotal());

        cart.setSubTotal(subTotal);

        return cart;
    }

    @Override
    public List<CartItem> getCartItemListByUserId(String userId) {
        List<CartItem> cartList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CARTITEM_LIST_BY_USERID);
            preparedStatement.setString(1, userId);

            resultSet = preparedStatement.executeQuery();
            ItemDAO itemDAO = new ItemDAOImpl();
            while (resultSet.next()) {
                CartItem cart = new CartItem();
                cart.setId(resultSet.getInt(1));
                cart.setUserId(userId);
                cart.setItemId(resultSet.getString(2));
                cart.setQuantity(resultSet.getInt(3));

                cart.setItem(itemDAO.getItemByItemId(cart.getItemId()));

                cart.setInStock(itemDAO.getInventoryQuantityByItemId(cart.getItemId()) >= cart.getQuantity());
                cart.setTotal(cart.getItem().getListPrice().multiply(new BigDecimal(cart.getQuantity())));

                cartList.add(cart);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return cartList;
    }

    @Override
    public boolean insertCartItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CARTITEM_BY_USERID_ITEMID_QUANTITY);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, itemId);
            preparedStatement.setInt(3, quantity);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean updateRelativeCartItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CARTITEM_BY_USERID_ITEMID_QUANTITY_RELATIVE);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, userId);
            preparedStatement.setString(3, itemId);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean deleteCartItemByUserIdAndItemId(String userId, String itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CATEITEM_BY_USERID_ITEMID);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, itemId);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean deleteCartItemById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CARTITEM_BY_ID);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean updateAbsoluteCartItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CARTITEM_BY_USERID_ITEMID_QUANTITY_ABSOLUTE);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, userId);
            preparedStatement.setString(3, itemId);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }
        return false;
    }
}
