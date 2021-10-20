package com.al.o2o.service.impl;

import com.al.o2o.dao.ShopDao;
import com.al.o2o.dto.ImageHolder;
import com.al.o2o.dto.ShopExecution;
import com.al.o2o.entity.Shop;
import com.al.o2o.enums.ShopStateEnum;
import com.al.o2o.exceptions.ShopOperationException;
import com.al.o2o.service.ShopService;
import com.al.o2o.util.ImageUtil;
import com.al.o2o.util.PageCalculator;
import com.al.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:ShopServiceImpl
 * @Description 商品业务接口实现类
 * @d ate2021/
 */

@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    private ShopDao shopDao;


    /**
     * 店铺列表分页查询
     * dao层只认识行数row service只认页数 page 因此在service要做一个转换
     *
     * @param shopCondition 店铺信息
     * @param pageIndex     从第几行开始查询
     * @param pageSize      返回的行数
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        //rowIndex转化
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        //分页查询
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        //查询总数量
        int count = shopDao.queryShopCount(shopCondition);
        //返回状态标识
        ShopExecution se = new ShopExecution();
        //非空判断
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        //返回结果
        return se;
    }

    /**
     * 店铺查询
     *
     * @param shopId
     * @return
     */
    @Override
    public Shop getByShopId(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    /**
     * 更新店铺信息
     *
     * @param shop 店铺名
     * @param thumbnail 图片
     * @return
     * @throws ShopOperationException
     */
    @Override
    @Transactional(rollbackFor = ShopOperationException.class)
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            // 1.判断是否需要处理图片
            try {
                if (thumbnail != null && thumbnail.getImage() != null && thumbnail.getImageName() != null
                        && !"".equals(thumbnail.getImageName())) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, thumbnail);
                }
                // 2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    /**
     * 店铺注册
     *
     * @param shop 店铺信息
     * @param thumbnail 店铺缩略图
     * @return
     * @throws ShopOperationException
     */
    @Override
    @Transactional(rollbackFor = ShopOperationException.class)
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        // 空值判断
        if (shop == null && shop.getShopName() != null && !"".equals(shop.getShopName())) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // 给店铺信息赋初始值,0代表未审核
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // 添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (thumbnail.getImage() != null) {
                    // 存储图片
                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // 更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);

    }

    /**
     * 添加店铺图片
     *
     * @param shop
     * @param thumbnail
     */
    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }

}
