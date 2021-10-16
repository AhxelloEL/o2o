package com.al.o2o.entity;

import java.util.Date;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.entity
 * @ClassName:UserProductMap
 * @Description 顾客店铺积分映射
 * @date2021/8/24 10:14
 */
public class UserProductMap {
    private Long userProductId;
    private Date createTime;
    private Integer point;
    private Shop shop;
    private Product product;
    private PersonInfo user;
    private PersonInfo operator;

    public Long getUserProductId() {
        return userProductId;
    }

    public void setUserProductId(Long userProductId) {
        this.userProductId = userProductId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }


    public PersonInfo getUser() {
        return user;
    }

    public void setUser(PersonInfo user) {
        this.user = user;
    }

    public PersonInfo getOperator() {
        return operator;
    }

    public void setOperator(PersonInfo operator) {
        this.operator = operator;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
