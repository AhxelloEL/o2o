package com.al.o2o.service;

import com.al.o2o.entity.PersonInfo;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:PersonInfoService
 * @Description
 * @date2021/8/3 14:26
 */
public interface PersonInfoService {
    /**
     * 通过userId查询用户信息
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);
}
