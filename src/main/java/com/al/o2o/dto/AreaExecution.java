package com.al.o2o.dto;

import com.al.o2o.entity.Area;
import com.al.o2o.enums.AreaStateEnum;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dto
 * @ClassName:AreaExecution
 * @Description 区域实体信息类增强
 * @date2021/10/12 1:32
 */
public class AreaExecution {
    /**
     * 结果状态
     */
    private int state;
    /**
     * 状态标识
     */
    private String stateInfo;
    /**
     * 区域增删改
     */
    private Area area;
    /**
     * 查询
     */
    private List<Area> areaList;
    /**
     * 区域数量
     */
    private int count;


    /**
     * 区域信息操作失败使用的构造器
     *
     * @param stateEnum
     */
    public AreaExecution(AreaStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 区域信息操作成功使用的构造器（增删改）
     *
     * @param stateEnum
     * @param area
     */
    public AreaExecution(AreaStateEnum stateEnum, Area area) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.area = area;
    }

    /**
     * 区域信息操作成功使用的构造器
     *
     * @param stateEnum
     * @param areaList
     */
    public AreaExecution(AreaStateEnum stateEnum, List<Area> areaList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.areaList = areaList;
    }

    public AreaExecution() {
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
