package com.al.o2o.service;

import com.al.o2o.dto.AreaExecution;
import com.al.o2o.entity.Area;

import java.util.List;

/**
 * @author Xiahuicheng
 */
public interface AreaService {
    public static final String AREALISTKEY = "areaList";

    /**
     * 查询区域列表,优先从缓存中获取
     *
     * @return 所有区域信息
     */
    List<Area> getAreaList();

    /**
     * 增加区域信息
     *
     * @param area 区域信息
     * @return 状态标识
     */
    AreaExecution addArea(Area area);

    /**
     * 修改区域信息
     *
     * @param area 区域信息
     * @return 状态标识
     */
    AreaExecution modifyArea(Area area);


}
