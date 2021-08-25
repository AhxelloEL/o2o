package com.al.o2o.dao;

import com.al.o2o.entity.PersonInfo;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:PersonInfoDao
 * @Description 用户类
 * @date2021/8/2 16:47
 */
public interface PersonInfoDao {
    /***
     * 通过用户ID查询用户信息
     * @param userId 用户Id
     * @return 返回用户详情信息
     */
    PersonInfo queryPersonInfoById(Long userId);

    /**
     * 添加用户信息
     * @param personInfo 用户表
     * @return 返回0：添加失败 1：添加成功
     */
    int insertPersonInfo(PersonInfo personInfo);
}
