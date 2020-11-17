package org.csu.petstore.persistence.Impl;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Address;
import org.csu.petstore.domain.MyList;
import org.csu.petstore.domain.Product;
import org.csu.petstore.persistence.DBUtil;
import org.csu.petstore.persistence.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String GET_ACCOUNT_BY_USERID =
            "SELECT " +
                    "A.USERID, " +
                    "A.NICKNAME, " +
                    "A.REALNAME, " +
                    "A.EMAIL, " +
                    "A.SEX, " +
                    "A.PHONE, " +
                    "S.PASSWORD, " +
                    "P.LANGPREF, " +
                    "P.FAVCATEGORY, " +
                    "P.MYLISTOPT, " +
                    "P.BANNEROPT " +
                    "FROM ACCOUNT A, SIGNON S, PROFILE P " +
                    "WHERE S.USERID = A.USERID AND P.USERID = A.USERID AND A.USERID = ?";
    private static final String GET_ACCOUNT_BY_USERID_AND_PASSWORD =
        "SELECT " +
                "A.USERID, " +
                "A.NICKNAME, " +
                "A.REALNAME, " +
                "A.EMAIL, " +
                "A.SEX, " +
                "A.PHONE, " +
                "S.PASSWORD, " +
                "P.LANGPREF, " +
                "P.FAVCATEGORY, " +
                "P.MYLISTOPT, " +
                "P.BANNEROPT " +
                "FROM ACCOUNT A, SIGNON S, PROFILE P " +
                "WHERE " +
                "A.USERID = ? AND " +
                "S.PASSWORD = ? AND " +
                "S.USERID = A.USERID AND P.USERID = A.USERID";
    private static final String GET_ADDRESS_BY_USERID =
            "SELECT " +
                    "ID, " +
                    "USERID, " +
                    "RECEIVER, " +
                    "PHONE, " +
                    "PROVINCE, " +
                    "CITY, " +
                    "DISTRICT, " +
                    "DETAILS " +
                    "FROM ADDRESS " +
                    "WHERE USERID = ?";
    private static final String INSERT_ACCOUNT =
            "INSERT INTO ACCOUNT (" +
                    "USERID, " +
                    "NICKNAME, " +
                    "REALNAME, " +
                    "EMAIL, " +
                    "SEX, " +
                    "PHONE) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SIGNON =
            "INSERT INTO SIGNON (" +
                    "USERID, " +
                    "PASSWORD) " +
                    "VALUES(?, ?)";
    private static final String INSERT_PROFILE =
            "INSERT INTO PROFILE (" +
                    "USERID, " +
                    "LANGPREF, " +
                    "FAVCATEGORY, " +
                    "MYLISTOPT, " +
                    "BANNEROPT) " +
                    "VALUES(?, ?, ?, ?, ?)";
    private static final String INSERT_ADDRESS =
            "INSERT INTO ADDRESS (" +
                    "USERID, " +
                    "RECEIVER, " +
                    "PHONE, " +
                    "PROVINCE, " +
                    "CITY, " +
                    "DISTRICT, " +
                    "DETAILS) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ACCOUNT =
            "UPDATE ACCOUNT SET " +
                    "NICKNAME = ?, " +
                    "REALNAME = ?, " +
                    "EMAIL = ?, " +
                    "SEX = ?, " +
                    "PHONE = ? " +
                    "WHERE USERID = ?";
    private static final String UPDATE_SIGNON =
            "UPDATE SIGNON SET " +
                    "PASSWORD = ? " +
                    "WHERE USERID = ?";
    private static final String UPDATE_PROFILE =
            "UPDATE PROFILE SET " +
                    "LANGPREF = ?, " +
                    "FAVCATEGORY = ?, " +
                    "MYLISTOPT = ?, " +
                    "BANNEROPT = ? " +
                    "WHERE USERID = ?";
    private static final String UPDATE_ADDRESS =
            "UPDATE ADDRESS SET " +
                    "RECEIVER = ?, " +
                    "PHONE = ?, " +
                    "PROVINCE = ?, " +
                    "CITY = ?, " +
                    "DISTRICT = ?, " +
                    "DETAILS = ? " +
                    "WHERE ID = ?";
    private static final String DELETE_ADDRESS =
            "DELETE FROM ADDRESS WHERE ID = ?";
    private static final String GET_ADDRESS_BY_ID =
            "SELECT " +
                    "USERID, " +
                    "RECEIVER, " +
                    "PHONE, " +
                    "PROVINCE, " +
                    "CITY, " +
                    "DISTRICT, " +
                    "DETAILS " +
                    "FROM ADDRESS WHERE ID = ?";
    private static final String GET_FAVCATEGORY =
            "SELECT " +
                    "BANNERNAME " +
                    "FROM BANNERDATA WHERE FAVCATEGORY = ?";
    private static final String GET_MYLIST_BY_FAVCATEGORY =
            "SELECT " +
                    "PRODUCTID, " +
                    "PNAME " +
                    "FROM PRODUCT WHERE CATEGORYID = ?";

    @Override
    public Account getAccountByUserId(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERID);
            preparedStatement.setString(1, account.getUserId());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setUserId(resultSet.getString(1));
                account.setNickName(resultSet.getString(2));
                account.setRealName(resultSet.getString(3));
                account.setEmail(resultSet.getString(4));
                account.setSex(resultSet.getInt(5) == 1);
                account.setPhone(resultSet.getString(6));

                account.setPassword(resultSet.getString(7));

                account.setLanguagePrefer(resultSet.getString(8));
                account.setFavCategory(resultSet.getString(9));
                account.setListOption(resultSet.getInt(10) == 1);
                account.setBannerOption(resultSet.getInt(11) == 1);

                account.setAddressList(getAddressListByUserId(account.getUserId()));

                return account;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public Account getAccountByUserIdAndPassword(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERID_AND_PASSWORD);
            preparedStatement.setString(1, account.getUserId());
            preparedStatement.setString(2, account.getPassword());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setUserId(resultSet.getString(1));
                account.setNickName(resultSet.getString(2));
                account.setRealName(resultSet.getString(3));
                account.setEmail(resultSet.getString(4));
                account.setSex(resultSet.getInt(5) == 1);
                account.setPhone(resultSet.getString(6));

                account.setPassword(resultSet.getString(7));

                account.setLanguagePrefer(resultSet.getString(8));
                account.setFavCategory(resultSet.getString(9));
                account.setListOption(resultSet.getInt(10) == 1);
                account.setBannerOption(resultSet.getInt(11) == 1);

                account.setAddressList(getAddressListByUserId(account.getUserId()));

                return account;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return null;
    }

    @Override
    public List<Address> getAddressListByUserId(String userId) {
        List<Address> addressList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ADDRESS_BY_USERID);
            preparedStatement.setString(1, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();

                address.setId(resultSet.getInt(1));
                address.setUserId(resultSet.getString(2));
                address.setReceiver(resultSet.getString(3));
                address.setPhone(resultSet.getString(4));
                address.setProvince(resultSet.getString(5));
                address.setCity(resultSet.getString(6));
                address.setDistrict(resultSet.getString(7));
                address.setDetails(resultSet.getString(8));

                addressList.add(address);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return addressList;
    }

    @Override
    public Address getAddressById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ADDRESS_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Address address = new Address();
                address.setId(id);
                address.setUserId(resultSet.getString(1));
                address.setReceiver(resultSet.getString(2));
                address.setPhone(resultSet.getString(3));
                address.setProvince(resultSet.getString(4));
                address.setCity(resultSet.getString(5));
                address.setDistrict(resultSet.getString(6));
                address.setDetails(resultSet.getString(7));

                return address;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public boolean insertAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1, account.getUserId());
            preparedStatement.setString(2, account.getNickName());
            preparedStatement.setString(3, account.getRealName());
            preparedStatement.setString(4, account.getEmail());
            preparedStatement.setInt(5, account.isSex() ? 1 : 0);
            preparedStatement.setString(6, account.getPhone());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean insertProfile(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1, account.getUserId());
            preparedStatement.setString(2, account.getLanguagePrefer());
            preparedStatement.setString(3, account.getFavCategory());
            preparedStatement.setInt(4, account.isListOption() ? 1 : 0);
            preparedStatement.setInt(5, account.isBannerOption() ? 1 : 0);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean insertSignon(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_SIGNON);
            preparedStatement.setString(1, account.getUserId());
            preparedStatement.setString(2, account.getPassword());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean insertAddress(Address address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ADDRESS);
            preparedStatement.setString(1, address.getUserId());
            preparedStatement.setString(2, address.getReceiver());
            preparedStatement.setString(3, address.getPhone());
            preparedStatement.setString(4, address.getProvince());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getDistrict());
            preparedStatement.setString(7, address.getDetails());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1, account.getNickName());
            preparedStatement.setString(2, account.getRealName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setInt(4, account.isSex() ? 1 : 0);
            preparedStatement.setString(5, account.getPhone());
            preparedStatement.setString(6, account.getUserId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean updateProfile(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1, account.getLanguagePrefer());
            preparedStatement.setString(2, account.getFavCategory());
            preparedStatement.setInt(3, account.isListOption() ? 1 : 0);
            preparedStatement.setInt(4, account.isBannerOption() ? 1 : 0);
            preparedStatement.setString(5, account.getUserId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean updateSignon(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SIGNON);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUserId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean updateAddress(Address address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1, address.getReceiver());
            preparedStatement.setString(2, address.getPhone());
            preparedStatement.setString(3, address.getProvince());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getDistrict());
            preparedStatement.setString(6, address.getDetails());
            preparedStatement.setInt(7, address.getId());

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public boolean deleteAddress(Address address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
            preparedStatement.setInt(1, address.getId());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(null, preparedStatement, connection);
        }

        return false;
    }

    @Override
    public String getFavCategotyURL(String favCategory) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FAVCATEGORY);
            preparedStatement.setString(1, favCategory);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public MyList getMyListByCategory(String favcategory) {
        MyList myList = new MyList();
        myList.setCategoryId(favcategory);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_MYLIST_BY_FAVCATEGORY);
            preparedStatement.setString(1, favcategory);

            resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();

                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));

                productList.add(product);
            }

            myList.setProductList(productList);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return myList;
    }
}