package org.csu.petstore.domain;

import org.csu.petstore.persistence.Impl.UserDAOImpl;
import org.csu.petstore.persistence.UserDAO;

import java.util.List;

public class Account {
    // 在这个 Account POJO 里面 结合了
    //Account(用户信息) signon(账号密码) profile(用户配置信息) address(收货地址) 四个表的信息

    //Account
    private String userId;
    private String nickName;
    private String realName;
    private String email;
    private boolean sex;
    private String phone;

    //signon
    private String password;

    //profile
    private String languagePrefer;
    private String favCategory;
    private boolean listOption;
    private boolean bannerOption;

    private String favCategoryURL;

    //address
    private List<Address> addressList;  // 一个userId 可能对应着多个 address

    //cart
    private List<CartItem> cartList; // 代表着里面的货物

    public Account() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguagePrefer() {
        return languagePrefer;
    }

    public void setLanguagePrefer(String languagePrefer) {
        this.languagePrefer = languagePrefer;
    }

    public String getFavCategory() {
        return favCategory;
    }

    public String getFavCategoryURL() {
        return favCategoryURL;
    }

    public void setFavCategory(String favCategory) {
        this.favCategory = favCategory;
        UserDAO userDAO = new UserDAOImpl();
        favCategoryURL = userDAO.getFavCategotyURL(favCategory);
    }

    public boolean isListOption() {
        return listOption;
    }

    public void setListOption(boolean listOption) {
        this.listOption = listOption;
    }

    public boolean isBannerOption() {
        return bannerOption;
    }

    public void setBannerOption(boolean bannerOption) {
        this.bannerOption = bannerOption;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<CartItem> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartItem> cartList) {
        this.cartList = cartList;
    }
}
