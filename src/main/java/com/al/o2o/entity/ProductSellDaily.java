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
    /**
     * 主键Id
     */
    private Integer productSellDailyId;
    /**
     * 哪天的销量，精确到天
     */
    private Date createTime;
    /**
     * 销量
     */
    private Integer total;
    /**
     * 关联商品信息
     */
    private Product product;
    /**
     * 关联店铺信息
     */
    private Shop shop;

    public Integer getProductSellDailyId() {
        return productSellDailyId;
    }

    public void setProductSellDailyId(Integer productSellDailyId) {
        this.productSellDailyId = productSellDailyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ProductSellDaily(Integer productSellDailyId, Date createTime, Integer total, Product product, Shop shop) {
        this.productSellDailyId = productSellDailyId;
        this.createTime = createTime;
        this.total = total;
        this.product = product;
        this.shop = shop;
    }

    public ProductSellDaily() {
    }
}
