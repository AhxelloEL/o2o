package com.al.o2o.dto;

import com.al.o2o.entity.HeadLine;
import com.al.o2o.enums.HeadLineStateEnum;
import java.util.List;

/**
 * 头条展示列表返回值封装
 *
 * @author Xiahuicheng
 */
public class HeadLineExecution {
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
     * 操作的headline（增删改的时候用）
     */
    private HeadLine headLine;

    /**
     * 获取的headline列表(查询列表的时候用)
     */
    private List<HeadLine> headLineList;

    public HeadLineExecution() {
    }

    /**
     * 失败时使用的构造器
     *
     * @param stateEnum
     */
    public HeadLineExecution(HeadLineStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 成功的构造器
     *
     * @param stateEnum
     * @param headLine
     */
    public HeadLineExecution(HeadLineStateEnum stateEnum, HeadLine headLine) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.headLine = headLine;
    }

    /**
     * 成功的构造器
     *
     * @param stateEnum
     * @param headLineList
     */
    public HeadLineExecution(HeadLineStateEnum stateEnum, List<HeadLine> headLineList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.headLineList = headLineList;
    }

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

    public HeadLine getHeadLine() {
        return headLine;
    }

    public void setHeadLine(HeadLine headLine) {
        this.headLine = headLine;
    }

    public List<HeadLine> getHeadLineList() {
        return headLineList;
    }

    public void setHeadLineList(List<HeadLine> headLineList) {
        this.headLineList = headLineList;
    }

}
