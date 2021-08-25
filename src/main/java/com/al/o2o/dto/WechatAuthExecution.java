package com.al.o2o.dto;

import com.al.o2o.entity.WechatAuth;
import com.al.o2o.enums.WechatAuthStateEnum;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dto
 * @ClassName:WechatAuthExecution
 * @Description 微信返回状态
 * @date2021/8/3 12:46
 */
public class WechatAuthExecution {
    /**
     * 结果状态
     */
    private int state;
    /**
     * 状态标识
     */
    private String stateInfo;
    /**
     * 数量
     */
    private int count;
    /**
     * 用于增删改
     */
    private WechatAuth wechatAuth;
    /**
     * 用于列表查询
     */
    private List<WechatAuth> wechatAuthList;

    //-----------getset-----------------------

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

    public WechatAuth getWechatAuth() {
        return wechatAuth;
    }

    public void setWechatAuth(WechatAuth wechatAuth) {
        this.wechatAuth = wechatAuth;
    }

    public List<WechatAuth> getWechatAuthList() {
        return wechatAuthList;
    }

    public void setWechatAuthList(List<WechatAuth> wechatAuthList) {
        this.wechatAuthList = wechatAuthList;
    }

    public WechatAuthExecution() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 失败时调用的构造器
     * @param stateEnum
     */
    public WechatAuthExecution(WechatAuthStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 成功的构造器
     * @param stateEnum
     * @param wechatAuth
     */
    public WechatAuthExecution(WechatAuthStateEnum stateEnum, WechatAuth wechatAuth){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.wechatAuth = wechatAuth;
    }

    /**
     * 查询成功的调用构造器
     * @param stateEnum
     * @param wechatAuthList
     */
    public WechatAuthExecution(WechatAuthStateEnum stateEnum, List<WechatAuth> wechatAuthList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.wechatAuthList = wechatAuthList;
    }
}
