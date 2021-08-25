package com.al.o2o.service;

import com.al.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @author yunSun
 */
public interface ShopCategoryService {
    public static final String SHOPCATEGORYKEY = "shopcategorylist";
    /**
     * 查询
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
