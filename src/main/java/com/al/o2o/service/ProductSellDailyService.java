package com.al.o2o.service;

import com.al.o2o.entity.ProductSellDaily;

import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @InterFaceName:ProductSellDailyService
 * @Description
 * @date2021/9/7 12:49
 */
public interface ProductSellDailyService {
    /**
     * 每日定时对所有店铺的商品销量进行统计
     */
    void dailyCalculate();

    /**
     * 分页查询
     * @param productSellDailyCondition
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ProductSellDaily> getProductSellDailyList(ProductSellDaily productSellDailyCondition, Date beginTime, Date endTime);
}
