package com.al.o2o.dao;

import com.al.o2o.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:ProductSellDailyDao
 * @Description 店铺商品销量
 * @date2021/8/24 12:14
 */
public interface ProductSellDailyDao {
    /**
     * 根据商品查询条件返回商品销量的统计表
     * @param productSellDailyCondition
     * @param beginTime
     * @param editTime
     * @return
     */
    List<ProductSellDaily> productSellDailyList(@Param("productSellDailyCondition")ProductSellDaily productSellDailyCondition,
                                                @Param("beginTime") Date beginTime, @Param("editTime")Date editTime);

    /**
     * 统计平台所以商品的日销售量
     * @return
     */
    int insertProductSellDaily();
}
