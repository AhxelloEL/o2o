package com.al.o2o.service;

import com.al.o2o.entity.HeadLine;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:HeadLineService
 * @Description
 * @date2021/6/2 9:21
 */
public interface HeadLineService {
    public static final String HEADLINEKEY = "headlinelist";
    /**
     * 头条轮播展示
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLineList (HeadLine headLineCondition);

    /**
     * 根据头条Id返回唯一信息
     * @param lineId
     * @return
     */
    HeadLine getByLineId(long lineId);
}
