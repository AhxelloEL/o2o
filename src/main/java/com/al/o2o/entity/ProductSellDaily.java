package com.al.o2o.entity;

import java.util.Date;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.entity
 * @ClassName:ProductSellDaily
 * @Description 顾客消费的商品映射
 * @date2021/8/24 11:13
 */
public class ProductSellDaily {
    private Integer productSellDailyId;
    private Date createTime;
    private Integer total;
    private Product product;
    private Shop shop;
}
