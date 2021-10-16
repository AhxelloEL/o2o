package com.al.o2o.dao;

import com.al.o2o.entity.Area;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:AreaDao
 * @Description 区域Repository接口
 * @date2021/7/10 9:10
 */

public interface AreaDao {
    /**
     * 列出区域列表
     *
     * @return areaList
     */
    List<Area> queryArea();

    /**
     * 插入区域信息
     *
     * @param area
     * @return
     */
    int insertArea(Area area);

    /**
     * 更新区域信息
     *
     * @param area
     * @return
     */
    int updateArea(Area area);

    /**
     * 删除区域信息
     *
     * @param areaId
     * @return
     */
    int deleteArea(long areaId);

    /**
     * 批量删除区域列表信息
     *
     * @param areaList
     * @return
     */
    int batchDeleteArea(List<Long> areaList);
}
