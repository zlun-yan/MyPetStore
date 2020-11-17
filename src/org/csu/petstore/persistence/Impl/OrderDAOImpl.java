package org.csu.petstore.persistence.Impl;

import org.csu.petstore.domain.Order;
import org.csu.petstore.persistence.DBUtil;
import org.csu.petstore.persistence.OrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String GET_ORDER_BY_ORDERID =
            "SELECT " +
                    "USERID, " +
                    "STATE, " +
                    "ORDERDATE, " +
                    "ADDRESSID, " +
                    "ITEMID, " +
                    "QUANTITY, " +
                    "TOTALPRICE " +
                    "FROM ORDERS " +
                    "WHERE ORDERID = ?";
    private static final String GET_ORDERS_BY_USERID =
            "SELECT " +
                    "ORDERID, " +
                    "STATE, " +
                    "ORDERDATE, " +
                    "ADDRESSID, " +
                    "ITEMID, " +
                    "QUANTITY, " +
                    "TOTALPRICE " +
                    "FROM ORDERS " +
                    "WHERE USERID = ?";
    private static final String INSERT_ORDER =
            "INSERT INTO ORDERS (" +
                    "USERID, " +
                    "STATE, " +
                    "ORDERDATE, " +
                    "ADDRESSID, " +
                    "ITEMID, " +
                    "QUANTITY, " +
                    "TOTALPRICE) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER =
            "UPDATE ORDERS SET " +
                    "STATE = ? " +
                    "WHERE ORDERID = ?";
    private static final String GET_MAX_ORDERID =
            "SELECT MAX(ORDERID) FROM ORDERS";
//            "SELECT LAST_INSERT_ID()";

    @Override
    public Order getOrderByOrderId(int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDER_BY_ORDERID);
            preparedStatement.setInt(1, orderId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(orderId);
                order.setUserId(resultSet.getString(1));
                order.setState(resultSet.getString(2));
                order.setOrderDate(resultSet.getDate(3));

                order.setAddressId(resultSet.getInt(4));
                order.setItemId(resultSet.getString(5));
                order.setQuantity(resultSet.getInt(6));
                order.setTotPrice(resultSet.getBigDecimal(7));

                return order;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USERID);
            preparedStatement.setString(1, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt(1));
                order.setUserId(userId);
                order.setState(resultSet.getString(2));
                order.setOrderDate(resultSet.getDate(3));

                order.setAddressId(resultSet.getInt(4));
                order.setItemId(resultSet.getString(5));
                order.setQuantity(resultSet.getInt(6));
                order.setTotPrice(resultSet.getBigDecimal(7));

                orderList.add(order);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return orderList;
    }

    @Override
    public boolean insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setString(1, order.getUserId());
            preparedStatement.setString(2, order.getState());
            preparedStatement.setDate(3, new Date(order.getOrderDate().getTime()));
            preparedStatement.setInt(4, order.getAddressId());
            preparedStatement.setString(5, order.getItemId());
            preparedStatement.setInt(6, order.getQuantity());
            preparedStatement.setBigDecimal(7, order.getTotPrice());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setString(1, order.getState());
            preparedStatement.setInt(2, order.getOrderId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }
        return false;
    }

    @Override
    public int getMaxOrderId() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_MAX_ORDERID);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
