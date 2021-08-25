package com.al.o2o.service;

import com.al.o2o.dto.ProductCategoryExecution;
import com.al.o2o.entity.ProductCategory;
import com.al.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:ProductCategoryService
 * @Description
 * @date2021/5/17 21:23
 */
public interface ProductCategoryService {
    /**
     * queryProductCategory
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryByShopId(Long shopId);

    /**
     * delete
     * @param productCategoryId
     * @param shopId
     * @return
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;

    /**
     * 批量添加
     * @param productCategoryList
     * @return
     */
    ProductCategoryExecution batchInsertAdd(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}
