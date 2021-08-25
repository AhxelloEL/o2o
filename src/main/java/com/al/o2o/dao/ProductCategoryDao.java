package com.al.o2o.dao;

import com.al.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:ProductCategory
 * @Description
 * @date2021/5/16 19:50
 */
public interface ProductCategoryDao {
    /**
     * 查询ShopId下的商品类型
     * @param shopId 店铺ID
     * @return 返回该店铺下的所有商品类型
     */
    List<ProductCategory> queryProductCategoryList(Long shopId);

    /**
     * 删除商品类型
     * @param shopId 店铺ID
     * @return 返回0：删除失败 1：删除成功
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                              @Param("shopId") long shopId);

    /**
     * 批量添加
     * @param productCategoryList 商品类型列表
     * @return int 标识影响的行数
     */
    int batchProductCategoryAdd(List<ProductCategory> productCategoryList);
}
