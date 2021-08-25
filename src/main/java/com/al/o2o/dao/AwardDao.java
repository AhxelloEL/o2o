package com.al.o2o.dao;

import com.al.o2o.entity.Award;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:AwardDao
 * @Description 奖品
 * @date2021/8/19 10:29
 */
public interface AwardDao {
    /**
     * 分页查询奖品信息
     * @param awardCondition 奖品信息
     * @param rowIndex 从第几页开始
     * @param pageSize 查询多少条数据
     * @return 返回分页后的奖品信息
     */
    List<Award> queryAwardList(@Param("awardCondition") Award awardCondition, @Param("rowIndex") int rowIndex,
                               @Param("pageSize") int pageSize );

    /**
     * 查询总数量
     * @param awardCondition 奖品信息
     * @return 返回奖品的总数量
     */
    int queryAwardCount(@Param("awardCondition") Award awardCondition);

    /**
     * 根据奖品ID查询对应的奖品信息
     * @param awardId 奖品ID
     * @return 返回对应的奖品信息
     */
    Award queryAwardByAwardId(Long awardId);

    /**
     * 添加奖品
     * @param award 奖品信息
     * @return 返回结果 1：成功 0：失败
     */
    int insertAward(Award award);

    /**
     * 更新奖品
     * @param award 奖品信息
     * @return 返回结果 1：成功 0：失败
     */
    int updateAward(Award award);

    /**
     * 下架奖品
     * @param awardId 奖品信息
     * @param shopId
     * @return 返回结果 1：成功 0：失败
     */
    int deleteAward(@Param("awardId") long awardId, @Param("shopId") long shopId);
}
