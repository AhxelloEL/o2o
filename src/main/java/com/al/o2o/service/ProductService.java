package com.al.o2o.service;

import com.al.o2o.dto.ImageHolder;
import com.al.o2o.dto.ProductExecution;
import com.al.o2o.entity.Product;
import com.al.o2o.exceptions.ProductOperationException;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:ProductService
 * @Description 商品列表
 * @date2021/5/16 22:27
 */
public interface ProductService {
    /**
     * 分页查询商品
     * @param product 商品数据
     * @param pageIndex 从第几页开始
     * @param pageSize 返回的行数
     * @return
     */
    ProductExecution getProductList(Product product, int pageIndex, int pageSize);

    /**
     * 根据productId查询商品信息
     * @param productId 商品Id
     *
     */
    Product getByProductId(Long productId);

    /**
     * 增加商品
     * @param product
     * @param thumbnail
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws  ProductOperationException;
}
