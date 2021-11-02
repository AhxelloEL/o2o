package com.al.o2o.dao;

import com.al.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:ProductDao
 * @Description 商品repository接口
 * @date2021/5/16 19:52
 */
public interface ProductDao {
    /**
     * 查询商品总数
     *
     * @param productCondition 商品信息
     * @return 返回该商品数量
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 商品分页查询
     *
     * @param productCondition 商品信息
     * @param rowIndex         从第几行开始
     * @param pageSize         返回多少条数据
     * @return 返回分页后的商品列表
     */
    List<Product> productList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 根据商品Id查询
     *
     * @param productId 商品ID
     * @return 返回商品详情
     */
    Product queryByProductId(long productId);

    /**
     * 在删除商品类别前先置空商品
     *
     * @param productCategoryId
     * @return 先将商品类型下的商品清空才能够删除商品类型
     */
    int updateProductCategoryToNull(long productCategoryId);

    /**
     * 增加商品
     *
     * @param product 商品信息
     * @return 返回0：添加失败 1：添加成功
     */
    int insertProduct(Product product);

    /**
     * 修改商品信息
     *
     * @param product 商品信息
     * @return 返回0：更新失败 1：更新成功
     */

    int updateProduct(Product product);

    /**
     * 根据商品Id删除指定商品
     *
     * @param productId 商品ID
     * @return 返回0：删除失败 1：删除成功
     */
    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
}
