package com.al.o2o.service;

import com.al.o2o.dto.UserProductMapExecution;
import com.al.o2o.entity.UserProductMap;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @InterFaceName:UserProductMapService
 * @Description
 * @date2021/9/8 9:56
 */
public interface UserProductMapService {
    /**
     * 分页查询
     * @param userProductCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    UserProductMapExecution getUserProductMapList(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize);

    /**
     * 消费记录
     * @param userProductMap
     * @return
     */
    UserProductMapExecution addUserProductMap(UserProductMap userProductMap);

    /**
     * 更新顾客积分
     * @param userProductCondition
     * @return
     */
    UserProductMapExecution modifyUserProductMapPoint(UserProductMap userProductCondition);
}
