package com.al.o2o.service.impl;

import com.al.o2o.dao.AwardDao;
import com.al.o2o.dto.AwardExecution;
import com.al.o2o.dto.ShopExecution;
import com.al.o2o.entity.Award;
import com.al.o2o.enums.AwardStateEnum;
import com.al.o2o.enums.ShopStateEnum;
import com.al.o2o.exceptions.AwardOperationException;
import com.al.o2o.service.AwardService;
import com.al.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:AwardServiceImpl
 * @Description
 * @date2021/8/20 15:01
 */
@Service
public class AwardServiceImpl implements AwardService {
    @Autowired
    private AwardDao awardDao;
    @Override
    public AwardExecution getAwawrdList(Award awardCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        //分页查询
        List<Award> awardList = awardDao.queryAwardList(awardCondition,rowIndex,pageSize);
        //查询总数量
        int count = awardDao.queryAwardCount(awardCondition);
        //返回状态标识
        AwardExecution awardExecution = new AwardExecution();
        //非空判断
        if (awardList != null){
            awardExecution.setAwardList(awardList);
            awardExecution.setCount(count);
        }
        return awardExecution;
    }

    @Override
    public Award getByAwardId(Long awardId)  {
        return awardDao.queryAwardByAwardId(awardId);
    }

    @Override
    public AwardExecution addAward(Award award) {
        //非空判断
        if (award != null && award.getAwardId() != null){
            try {
                //给奖品赋初始值
                award.setEnableStatus(1);
                award.setCreateTime(new Date());
                // 添加奖品信息
                int effectNum = awardDao.insertAward(award);
                if (effectNum <= 0 ){
                    return new AwardExecution(AwardStateEnum.INNER_ERROR);
                }
            }catch (Exception e){
                throw new AwardOperationException("add award ERROR:"+e.getMessage());
            }
        }else {
            return new AwardExecution(AwardStateEnum.EMPTY);
        }
        return new AwardExecution(AwardStateEnum.SUCCESS,award);
    }

    @Override
    public AwardExecution modifyAward(Award award) {
        if (award == null || award.getAwardId() == null){
            return new AwardExecution(AwardStateEnum.EMPTY);
        }
        try {
            //修改店铺
            award.setLastEditTime(new Date());
            int effectNum = awardDao.updateAward(award);
            if (effectNum <= 0){
                return new AwardExecution(AwardStateEnum.INNER_ERROR);
            }else {
                award = awardDao.queryAwardByAwardId(award.getAwardId());
                return new AwardExecution(AwardStateEnum.SUCCESS, award);
            }
        }catch (Exception e){
            throw  new AwardOperationException("UPDATE_AWARD ERROR:"+e.getMessage());
        }
    }

    @Override
    public AwardExecution deleteAward(Long awardId, Long shopId) {
        if (awardId == null || shopId == null){
            return new AwardExecution(AwardStateEnum.EMPTY);
        }
        try {
            int effectNum = awardDao.deleteAward(awardId,shopId);
            if (effectNum <= 0){
                return new AwardExecution(AwardStateEnum.INNER_ERROR);
            }else {
                return new AwardExecution(AwardStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new AwardOperationException("DELETE_AWARD ERROR:"+e.getMessage());
        }
    }
}
