package com.al.o2o.dto;

import com.al.o2o.entity.Shop;
import com.al.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * @author Xiahuicheng
 * 店铺返回状态
 */
public class ShopExecution {
    /**
     * 结果状态
     */
    private int state;
    /**
     * 状态标识
     */
    private String stateInfo;
    /**
     * 店铺数量
     */
    private int count;
    /**
     * 操作的店铺（增删改）
     */
    private Shop shop;
    /**
     * 店铺列表（查询使用
     */
    private List<Shop> shopList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public ShopExecution(){

    }

    /**
     * 操作失败调用的构造器
     * @param sateEnum
     */
    public ShopExecution(ShopStateEnum sateEnum){
        this.state = sateEnum.getState();
        this.stateInfo = sateEnum.getStateInfo();
    }

    /**
     * 用于增删改
     * 店铺操作成功时返回的构造器
     * @param stateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum stateEnum, Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 用于查询
     * 店铺操作成功调用的构造器
     * @param stateEnum
     * @param shopList
     */
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
