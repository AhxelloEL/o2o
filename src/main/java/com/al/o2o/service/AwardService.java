package com.al.o2o.service;

import com.al.o2o.dto.AwardExecution;
import com.al.o2o.dto.ImageHolder;
import com.al.o2o.entity.Award;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:AwardService
 * @Description 奖品
 * @date2021/8/20 13:54
 */
public interface AwardService {
    /**
     * 根据传入的条件分页返回奖品列表，并返回查询条件下的总数
     * @param awardCondition 奖品信息
     * @param pageIndex 从第几页开始查询
     * @param pageSize 返回的行数
     * @return 返回总数
     */
     AwardExecution getAwawrdList(Award awardCondition,int pageIndex,int pageSize);

    /**
     * 查询奖品信息
     * @param awardId 奖品ID
     * @return 返回单条结果
     */
     Award getByAwardId(long awardId);

    /**
     * 添加奖品
     * @param award
     * @param thumbnail
     * @return
     */
     AwardExecution addAward(Award award, ImageHolder thumbnail);

    /**
     * 下架奖品
     * @param award 奖品信息
     * @param thumbnail
     * @return 返回更新的状态标识
     */
     AwardExecution modifyAward(Award award,ImageHolder thumbnail);



}
