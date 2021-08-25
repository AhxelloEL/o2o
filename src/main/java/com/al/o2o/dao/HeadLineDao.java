package com.al.o2o.dao;

import com.al.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:HeadLink
 * @Description
 * @date2021/6/1 15:02
 */
public interface HeadLineDao {
    /**
     * 根据传入的查询条件（头条名查询头条）
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);

    /**
     * 根据头条Id返回唯一的头条信息
     * @param lineId 头条ID
     * @return 返回头条详情信息
     */
    HeadLine queryHeadLineById(long lineId);

    /**
     * 插入头条
     * @param headLine 头条信息
     * @return 返回0：插入失败 1：插入成功
     */
    int insertHeadLine(HeadLine headLine);

    /**
     * 更新头条信息
     * @param headLine 头条信息
     * @return 返回0：更新失败 1：更新成功
     */
    int updateHeadLine(HeadLine headLine);

    /**
     * 根据headlineId删除头条
     * @param headLineId 头条Id
     * @return 返回0：删除失败 1：删除成功
     */
    int deletHeadLine(long headLineId);

    /**
     * 批量删除
     * @param lineIdList
     * @return
     */
    int batchDeletHeadLine(List<Long> lineIdList);
}

