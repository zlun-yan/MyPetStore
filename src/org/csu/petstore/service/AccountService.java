package org.csu.petstore.service;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Address;
import org.csu.petstore.domain.MyList;
import org.csu.petstore.persistence.Impl.UserDAOImpl;
import org.csu.petstore.persistence.UserDAO;

public class AccountService {
    private UserDAO userDAO;

    public AccountService() {
        userDAO = new UserDAOImpl();
    }

    public Account getAccountById(String userId) {
        Account account = new Account();
        account.setUserId(userId);
        return userDAO.getAccountByUserId(account);
    }

    public Account getAccountByUserIdAndPassword(String userId, String password) {
        Account account = new Account();
        account.setUserId(userId);
        account.setPassword(password);
        return userDAO.getAccountByUserIdAndPassword(account);
    }

    public void SignUp(Account account) {
        userDAO.insertAccount(account);
        userDAO.insertSignon(account);
        userDAO.insertProfile(account);
    }

    public void updateInfo(Account account) {
        userDAO.updateAccount(account);
        userDAO.updateSignon(account);
        userDAO.updateProfile(account);
    }

    public void deleteAddress(Address address) {
        userDAO.deleteAddress(address);
    }

    public void updateAddress(Address address) {
        userDAO.updateAddress(address);
    }

    public Account updateAddressInSession(Account account) {
        account.setAddressList(userDAO.getAddressListByUserId(account.getUserId()));
        return account;
    }

    public void insertAddress(Address address) {
        userDAO.insertAddress(address);
    }

    public Address getAddressById(int id) {
        return userDAO.getAddressById(id);
    }

    public MyList getMyList(String favCatefory) {
        return userDAO.getMyListByCategory(favCatefory);
    }
}
