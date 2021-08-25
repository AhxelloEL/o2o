package com.al.o2o.dto;

import com.al.o2o.entity.ShopAuthMap;
import com.al.o2o.enums.ShopAuthMapStateEnum;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dto
 * @ClassName:ShopAuthMapExecution
 * @Description shopAuthMap实体类增强类
 * @date2021/8/25 10:54
 */
public class ShopAuthMapExecution {
    private int state;
    private String stateInfo;
    private ShopAuthMap shopAuthMap;
    private List<ShopAuthMap> shopAuthMapList;
    private int count;

    public int getState() {
        return this.state;
    }

    public void setState(final int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return this.stateInfo;
    }

    public void setStateInfo(final String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public ShopAuthMap getShopAuthMap() {
        return this.shopAuthMap;
    }

    public void setShopAuthMap(final ShopAuthMap shopAuthMap) {
        this.shopAuthMap = shopAuthMap;
    }

    public List<ShopAuthMap> getShopAuthMapList() {
        return this.shopAuthMapList;
    }

    public void setShopAuthMapList(final List<ShopAuthMap> shopAuthMapList) {
        this.shopAuthMapList = shopAuthMapList;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public ShopAuthMapExecution() {
    }

    /**
     * 操作失败是调用的构造器
     * @param stateEnum
     */
    public ShopAuthMapExecution(ShopAuthMapStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作增删改使用的构造器
     * @param stateEnum
     * @param shopAuthMap
     */
    public ShopAuthMapExecution(ShopAuthMapStateEnum stateEnum, ShopAuthMap shopAuthMap){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopAuthMap = shopAuthMap;
    }

    /**
     * 操作分页查询时使用
     * @param stateEnum
     * @param shopAuthMapList
     */
    public ShopAuthMapExecution(ShopAuthMapStateEnum stateEnum, List<ShopAuthMap> shopAuthMapList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopAuthMapList = shopAuthMapList;
    }
}
