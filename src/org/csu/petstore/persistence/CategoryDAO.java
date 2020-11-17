package org.csu.petstore.persistence;

import org.csu.petstore.domain.Category;

import java.util.List;

public interface CategoryDAO {
    /**
     * 获取所有类别
     * @return
     */
    List<Category> getCategoryList();

    /**
     * 根据类别Id查询
     * @param categoryId
     * @return
     */
    Category getCategoryById(String categoryId);
}
