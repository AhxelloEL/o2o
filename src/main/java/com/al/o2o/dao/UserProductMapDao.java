package com.al.o2o.dao;

import com.al.o2o.entity.UserProductMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品销量统计
 * @author yunSun
 */
public interface UserProductMapDao {

    /**
     * 分页查询顾客店铺积分
     * @param userProductCondition 顾客店铺积分信息
     * @param pageIndex 从第几行开始
     * @param pageSize 返回多少行数据
     * @return 返回积分信息
     */
    List<UserProductMap> queryUserProductMapList(@Param("userProductCondition") UserProductMap userProductCondition,
                                                 @Param("pageIndex")int pageIndex, @Param("pageSize") int pageSize);

    /**
     * 统计顾客总积分
     * @param userProductCondition
     * @return
     */
    int queryUserProductCount(@Param("userProductCondition")UserProductMap userProductCondition);

    /**
     * 顾客消费
     * @param userProductMap
     * @return
     */
    int insertUserProductMap(UserProductMap userProductMap);

    /**
     * 更新顾客积分
     * @param userProductMap
     * @return
     */
    int updateUserProductMapPoint(UserProductMap userProductMap);


}
