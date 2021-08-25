package com.al.o2o.dao;

import com.al.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * PackageName:com.al.o2o.dao
 * InterfaceName ShopCategoryDao
 * @author yunSun
 */
public interface ShopCategoryDao {
    /**
     * 查询店铺类型
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);


}
