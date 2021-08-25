package com.al.o2o.dto;

import com.al.o2o.entity.LocalAuth;
import com.al.o2o.enums.LocalAuthStateEnum;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dto
 * @ClassName:LocalAuthExecution
 * @Description localauth增强类
 * @date2021/8/4 17:00
 */
public class LocalAuthExecution {
    /**
     * 平台账号结果标识
     */
    private int state;

    /**
     * 平台账号状态标识
     */
    private String stateInfo;

    /**
     * 用于平台账号增删改
     */
    private LocalAuth localAuth;

    /**
     * 用于平台账号查询
     */
    private List<LocalAuth> localAuthList;

    /**
     * 平台账号计数
     */
    private int count;

    /**
     * 失败时调用的构造器
     * @param stateEnum
     */
    public LocalAuthExecution(LocalAuthStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作增删改使用的构造器
     * @param stateEnum
     * @param localAuth
     */
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, LocalAuth localAuth){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuth = localAuth;
    }

    /**
     * 操作查询使用的构造器
     * @param stateEnum
     * @param localAuthList
     */
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, List<LocalAuth> localAuthList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuthList = localAuthList;
    }

    //----------------------------------------getset--------------------------------------------------

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

    public LocalAuth getLocalAuth() {
        return localAuth;
    }

    public void setLocalAuth(LocalAuth localAuth) {
        this.localAuth = localAuth;
    }

    public List<LocalAuth> getLocalAuthList() {
        return localAuthList;
    }

    public void setLocalAuthList(List<LocalAuth> localAuthList) {
        this.localAuthList = localAuthList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
