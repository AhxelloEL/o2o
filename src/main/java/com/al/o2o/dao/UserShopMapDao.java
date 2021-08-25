package com.al.o2o.dao;

import com.al.o2o.entity.UserShopMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @Interface:UserShopMapDao
 * @Description 顾客店铺积分
 * @date2021/8/24 12:30
 */
public interface UserShopMapDao {
    /**
     *
     * @param userShopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<UserShopMap> queryUserShopMapList(@Param("userShopCondition") UserShopMap userShopCondition,
                                           @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

    /**
     *
     * @param userShopCondition
     * @return
     */
    int queryUserShopCount(@Param("userShopCondition")UserShopMap userShopCondition);

    /**
     * 根据传入的用户Id和店铺Id查询该用户在某个店铺下的积分信息
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 积分信息
     */
    UserShopMap queryUserShopMap(Long userId,Long shopId);

    /**
     *
     * @param userShopMap
     * @return
     */
    int insertUserShopMap(UserShopMap userShopMap);

    /**
     *
     * @param userShopMap
     * @return
     */
    int updateUserShopMapPoint(UserShopMap userShopMap);


}
