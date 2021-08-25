package com.al.o2o.dao;

import com.al.o2o.entity.ShopAuthMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:ShopAuthMapDao
 * @Description 店铺授权
 * @date2021/8/24 12:39
 */
public interface ShopAuthMapDao {
    /**
     * 分页列出店铺下的授权信息
     * @param shopId 店铺ID
     * @param pageIndex 从第几行开始
     * @param pageSize  返回多少行数据
     * @return 返回店铺授权信息
     */
    List<ShopAuthMap> queryShopAuthMapByShopId(@Param("shopId")Long shopId,@Param("pageIndex")int pageIndex,
                                               @Param("pageSize")int pageSize);

    /**
     * 查询店铺授权总数量
     * @param shopId 店铺ID
     * @return 店铺授权数量
     */
    int queryShopAuthCountByShopId(@Param("shopId")Long shopId);

    /**
     * 授权
     * @param shopAuthMap
     * @return 返回0：授权失败 1：授权成功
     */
    int insertShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 更新授权信息
     * @param shopAuthMap
     * @return 返回0:更新失败 1：更新成功
     */
    int updateShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 对某个员工除权
     * @param shopAuthMap
     * @return 返回0：除权失败 1：除权成功
     */
    int deleteShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 根据shopAuthId查询店铺员工
     * @param shopAuthId 店铺授权主键
     * @return 返回店铺员工权限
     */
    ShopAuthMap queryShopAuthMapById(Long shopAuthId);

}
