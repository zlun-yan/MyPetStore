package org.csu.petstore.service;

import org.csu.petstore.domain.Cart;
import org.csu.petstore.domain.CartItem;
import org.csu.petstore.persistence.CartDAO;
import org.csu.petstore.persistence.Impl.CartDAOImpl;

public class CartService {
    CartDAO cartDAO;

    public CartService() {
        cartDAO = new CartDAOImpl();
    }

    public boolean buyItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity) {
        if (cartDAO.getCartItemByUserIdAndItemId(userId, itemId) == null) {
            return cartDAO.insertCartItemByUserIdAndItemIdAndQuantity(userId, itemId, quantity);
        }

        return cartDAO.updateRelativeCartItemByUserIdAndItemIdAndQuantity(userId, itemId, quantity);
    }

    public CartItem getCartItemById(int id) {
        return cartDAO.getCartItemById(id);
    }

    public Cart getCartByUserId(String userId) {
        return cartDAO.getCartByUserId(userId);
    }

    public boolean deleteCartItemByUserIdAndItemId(String userId, String itemId) {
        return cartDAO.deleteCartItemByUserIdAndItemId(userId, itemId);
    }

    public boolean deleteCartItemById(int id) {
        return cartDAO.deleteCartItemById(id);
    }

    public boolean updateCartItem(String userId, String itemId, int quantity) {
        return cartDAO.updateAbsoluteCartItemByUserIdAndItemIdAndQuantity(userId, itemId, quantity);
    }
}
