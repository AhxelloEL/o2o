package com.al.o2o.service.impl;

import com.al.o2o.dao.ShopAuthMapDao;
import com.al.o2o.dto.ShopAuthMapExecution;
import com.al.o2o.entity.ShopAuthMap;
import com.al.o2o.enums.ShopAuthMapStateEnum;
import com.al.o2o.exceptions.ShopAuthMapOperationException;
import com.al.o2o.exceptions.ShopOperationException;
import com.al.o2o.service.ShopAuthMapService;
import com.al.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:ShopAuthMapServiceImpl
 * @Description 店铺授权业务实现类
 * @date2021/8/25 14:09
 */
@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {
    @Resource
    private ShopAuthMapDao shopAuthMapDao;

    /**
     * 获取店铺授权列表
     *
     * @param shopId    店铺Id
     * @param pageIndex 从第几页开始
     * @param pageSize  返回的行数
     * @return
     */
    @Override
    public ShopAuthMapExecution getShopAuthMapListByShopId(Long shopId, int pageIndex, int pageSize) {
        //非空判断
        if (shopId != null && pageIndex > -1 && pageSize > -1) {
            //页转行
            int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            //根据店铺Id查询店铺授权列表
            List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapByShopId(shopId, rowIndex, pageSize);
            //返回总数
            int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
            //定义返回状态
            ShopAuthMapExecution same = new ShopAuthMapExecution();
            //将查询结果存入增强类中
            same.setShopAuthMapList(shopAuthMapList);
            same.setCount(count);
            return same;
        } else {
            return null;
        }
    }

    /**
     * 根据店铺授权Id查询
     *
     * @param shopAuthId 店铺授权ID
     * @return
     */
    @Override
    public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
        return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
    }

    /**
     * 授权操作
     *
     * @param shopAuthMap 店铺授权信息
     * @return
     * @throws ShopOperationException
     */
    @Override
    @Transactional(rollbackFor = ShopAuthMapOperationException.class)
    public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
        //非空判断，主要是对店铺ID和操作员工ID判断
        if (shopAuthMap != null && shopAuthMap.getShop().getShopId() != null
                && shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null) {
            //赋初始值
            shopAuthMap.setCreateTime(new Date());
            shopAuthMap.setLastEditTime(new Date());
            shopAuthMap.setEnableStatus(1);
            //shopAuthMap.setTitleFlag(0);
            try {
                //授权
                int effectNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
                if (effectNum <= 0) {
                    throw new ShopOperationException("添加授权失败");
                }
                //授权成功返回结果状态
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new ShopOperationException("添加授权失败:" + e.getMessage());
            }
        } else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
        }
    }

    /**
     * 更新授权信息
     *
     * @param shopAuthMap 店铺授权表
     * @return
     * @throws ShopAuthMapOperationException
     */
    @Override
    @Transactional(rollbackFor = ShopAuthMapOperationException.class)
    public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
        if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
            shopAuthMap.setLastEditTime(new Date());
            int effectNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
            if (effectNum <= 0) {
                throw new ShopOperationException("更新授权失败");
            }
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS);
        } else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
        }
    }
}
