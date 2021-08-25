package com.al.o2o.dto;

import com.al.o2o.entity.Award;
import com.al.o2o.enums.AwardStateEnum;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dto
 * @ClassName:AwardExecution
 * @Description
 * @date2021/8/20 13:58
 */
public class AwardExecution {
    /**
     * 结果状态
     */
    private int state;
    /**
     * 状态标识
     */
    private String stateInfo;
    /**
     * 奖品数量
     */
    private int count;
    /**
     * 增删改
     */
    private Award award;
    /**
     * 查询
     */
    private List<Award> awardList;

    //-----------------------------------GET/SET--------------------------------

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

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    public AwardExecution() {
    }

    /**
     * 失败时调用的构造器
     * @param stateEnum
     */
    public AwardExecution(AwardStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作增删改成功时调用的构造器
     * @param stateEnum
     * @param award
     */
    public AwardExecution(AwardStateEnum stateEnum, Award award){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.award = award;
    }

    /**
     * 列表查询成功时调用的构造器
     * @param stateEnum
     * @param awardList
     */
    public AwardExecution(AwardStateEnum stateEnum,List<Award> awardList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.awardList = awardList;
    }

}
