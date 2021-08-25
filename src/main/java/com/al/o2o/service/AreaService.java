package com.al.o2o.service;

import com.al.o2o.entity.Area;

import java.util.List;

/**
 * @author Xiahuicheng
 */
public interface AreaService {
    public static final String AREALISTKEY = "areaList";
    /**
     * 查询区域列表
     * @return
     */
    List<Area> getAreaList();
}
