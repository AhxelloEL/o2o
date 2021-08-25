package com.al.o2o.dao;

import com.al.o2o.entity.ProductImg;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:ProductImageDao
 * @Description 商品详情图
 * @date2021/5/21 17:43
 */
public interface ProductImageDao {
    /**
     * 根据商品ID显示该商品的预览图
     * @param productId 商品ID
     * @return 返回该商品的所有详情图片
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加详情图片
     * @param productImgList 商品图片列表
     * @return 返回受影响行数
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 清空预览图
     * @param productId
     * @return 返回0：清空失败 1：清空成功
     */
    int deleteProductImgByProductId(long productId);
}
