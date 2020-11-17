package org.csu.petstore.persistence;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Address;
import org.csu.petstore.domain.MyList;

import java.util.List;

public interface UserDAO {
    /**
     * 这个可以是在用户注册的时候使用 判断是否有相同用户名
     * 还可以在更新用户信息之后 更新网页页面信息
     * @param account
     * @return 如果这个用户名存在 则返回这个用户的所有信息 若不存在 则返回 null
     */
    Account getAccountByUserId(Account account);

    /**
     * 这个就在登录的时候用
     * @param account
     * @return
     */
    Account getAccountByUserIdAndPassword(Account account);

    List<Address> getAddressListByUserId(String userId);

    Address getAddressById(int id);

    boolean insertAccount(Account account);

    boolean insertProfile(Account account);

    boolean insertSignon(Account account);

    /**
     * 对于一个账户 插入收货地址
     * 注意这个插入之后 还要更新一下这个账户的addressList(调用 getAddressListByUserId方法)
     * @param address
     */
    boolean insertAddress(Address address);

    /**
     * 创建用户后 不允许修改UserId
     * @param account
     */
    boolean updateAccount(Account account);

    boolean updateProfile(Account account);

    boolean updateSignon(Account account);

    /**
     * 对于一个账户 修改一个现有的收货地址
     * 修改的现有的收货地址是哪一个 可以根据 order和userId确认
     * 注意这个修改之后 还要更新一下这个账户的addressList(调用 getAddressListByUserId方法)
     * @param address
     */
    boolean updateAddress(Address address);

    boolean deleteAddress(Address address);

    String getFavCategotyURL(String favCategory);

    MyList getMyListByCategory(String favcategory);
}
