package com.al.o2o.dao;

import com.al.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:ShopDao
 * @Description 店铺Repository接口
 * @date2021/9/19 10:29
 */
public interface ShopDao {
    /**
     * 返回queryShopList总数
     *
     * @param shopCondition 店铺信息
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 分页查询，可输入的条件有：店铺名（模糊）店铺状态，店铺类别，区域Id，
     *
     * @param shopCondition 店铺信息
     * @param rowIndex      从第owner几行开始取数据
     * @param pageSize      返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 新增店铺
     *
     * @param shop 店铺信息
     * @return int返回值为新增店铺返回的行数  1表示成功插入一条数据，-1表示插入数据失败
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop 店铺信息
     * @return 返回执行成功的行数
     */
    int updateShop(Shop shop);

    /**
     * 查询店铺信息
     *
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);
}
