package com.al.o2o.service;

import com.al.o2o.dto.ImageHolder;
import com.al.o2o.dto.ShopExecution;
import com.al.o2o.entity.Shop;
import com.al.o2o.exceptions.ShopOperationException;

public interface ShopService {
     /**
      * 店铺分页查询
      * @param shopCodition
      * @param pageIndex 从第几页开始查询
      * @param pageSize 返回的行数
      * @return
      */
      ShopExecution getShopList(Shop shopCodition, int pageIndex, int pageSize);
     /**
      * 查询店铺信息
      * @param shopId
      * @return
      */
     Shop getByShopId(Long shopId);

     /**
      * 更新店铺
      * @param shop
      * @param thumbnail
      * @return
      * @throws ShopOperationException
      */
     ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
     /**
      * 注册店铺
      * @param thumbnail
      * @param shop
      * @param thumbnail
      * @return
      */
     ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
