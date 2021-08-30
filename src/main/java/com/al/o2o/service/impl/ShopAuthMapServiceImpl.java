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
 * @Description
 * @date2021/8/25 14:09
 */
@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {
    @Resource
    private ShopAuthMapDao shopAuthMapDao;
    @Override
    public ShopAuthMapExecution getShopAuthMapListByShopId(Long shopId, int pageIndex, int pageSize) {
        if (shopId != null && pageIndex > -1 && pageSize > -1) {
            int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapByShopId(shopId, rowIndex, pageSize);
            int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
            ShopAuthMapExecution same = new ShopAuthMapExecution();
            same.setShopAuthMapList(shopAuthMapList);
            same.setCount(count);
            return same;
        } else {
            return null;
        }
    }

    @Override
    public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
        return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
    }

    @Override
    @Transactional(rollbackFor = ShopAuthMapOperationException.class)
    public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopOperationException {
        if (shopAuthMap != null && shopAuthMap.getShop().getShopId() != null
            && shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null){
            shopAuthMap.setCreateTime(new Date());
            shopAuthMap.setLastEditTime(new Date());
            shopAuthMap.setEnableStatus(1);
            shopAuthMap.setTitleFlag(0);
            try {
                int effectNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
                if (effectNum <= 0){
                    throw new ShopOperationException("添加授权失败");
                }
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS);
            }catch (Exception e){
                throw new ShopOperationException("添加授权失败:"+e.getMessage());
            }
        }else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
        }
    }

    @Override
    public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopOperationException {
        if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
            int effectNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
            if (effectNum < 0){
                throw new ShopOperationException("更新授权失败");
            }
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS);
        }else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
        }
    }
}
