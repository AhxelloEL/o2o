package com.al.o2o.service;

import com.al.o2o.dto.AwardExecution;
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
     * 分页查询
     * @param awardCondition 奖品信息
     * @param pageIndex 从第几页开始查询
     * @param pageSize 返回的行数
     * @return 返回结果
     */
     AwardExecution getAwawrdList(Award awardCondition,int pageIndex,int pageSize);

    /**
     * 查询奖品信息
     * @param awardId 奖品ID
     * @return 返回单条结果
     */
     Award getByAwardId(Long awardId);

    /**
     * 添加奖品
     * @param award 奖品信息
     * @return 返回添加成功状态标识
     */
     AwardExecution addAward(Award award);

    /**
     * 更新奖品
     * @param award 奖品信息
     * @return 返回更新的状态标识
     */
     AwardExecution modifyAward(Award award);

    /**
     * 下架奖品
     * @param awardId 奖品ID
     * @param shopId  店铺ID
     * @return
     */
     AwardExecution deleteAward(Long awardId,Long shopId);

}
