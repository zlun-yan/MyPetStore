package org.csu.petstore.persistence;

import org.csu.petstore.domain.Cart;
import org.csu.petstore.domain.CartItem;

import java.util.List;

public interface CartDAO {
    CartItem getCartItemById(int id);

    CartItem getCartItemByUserIdAndItemId(String userId, String itemId);

    Cart getCartByUserId(String userId);

    List<CartItem> getCartItemListByUserId(String userId);

    boolean insertCartItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity);

    boolean updateRelativeCartItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity);

    boolean updateAbsoluteCartItemByUserIdAndItemIdAndQuantity(String userId, String itemId, int quantity);

    boolean deleteCartItemByUserIdAndItemId(String userId, String itemId);

    boolean deleteCartItemById(int id);
}
