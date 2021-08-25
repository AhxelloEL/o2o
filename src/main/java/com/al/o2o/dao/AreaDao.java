package com.al.o2o.dao;

import com.al.o2o.entity.Area;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:AreaDao
 * @Description 区域
 * @date2021/7/10 9:10
 */
//把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class="">

public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    public List<Area> queryArea();



}
