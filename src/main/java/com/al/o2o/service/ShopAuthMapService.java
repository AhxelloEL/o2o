package com.al.o2o.service;

import com.al.o2o.dto.ShopAuthMapExecution;
import com.al.o2o.entity.ShopAuthMap;
import com.al.o2o.exceptions.ShopOperationException;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @InterFaceName:ShopAuthMapService
 * @Description 店铺授权业务接口
 * @date2021/8/25 11:02
 */
public interface ShopAuthMapService {
    /**
     * 根据店铺Id分页查询出授权信息
     * @param shopId 店铺Id
     * @param pageIndex 从第几页开始
     * @param pageSize 返回的行数
     * @return 授权列表
     */
    ShopAuthMapExecution getShopAuthMapListByShopId(Long shopId, int pageIndex, int pageSize);

    /**
     * 查询授权总数量
     * @param shopAuthCondition 店铺授权信息
     * @return 总数量
     */
    //ShopAuthMapExecution getShopAuthCountByShopId(ShopAuthMap shopAuthCondition);

    /**
     * 根据shopAuthId返回对应的授权信息
     * @param shopAuthId 店铺授权ID
     * @return 授权信息
     */
    ShopAuthMap getShopAuthMapById(Long shopAuthId);

    /**
     * 给员工授权
     * @param shopAuthMap 店铺授权信息
     * @return 0:失败 1：成功
     * @throws ShopOperationException
     */
    ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopOperationException;

    /**
     * 更新授权信息，包括职位，状态等
     * @param shopAuthMap 店铺授权表
     * @return 0:失败 1：成功
     * @throws ShopOperationException
     */
    ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopOperationException;


}
