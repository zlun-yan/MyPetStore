package org.csu.petstore.persistence;

import org.csu.petstore.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemDAO {
    boolean updateInventoryQuantityByItemId(Map<String, Object> param);

    int getInventoryQuantityByItemId(String itemId);

    List<Item> getItemListByProductId(String productId);

    Item getItemByItemId(String itemId);
}
