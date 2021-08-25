package com.al.o2o.service.impl;

import com.al.o2o.dao.ProductCategoryDao;
import com.al.o2o.dao.ProductDao;
import com.al.o2o.dto.ProductCategoryExecution;
import com.al.o2o.entity.ProductCategory;
import com.al.o2o.enums.ProductCategoryStateEnum;
import com.al.o2o.exceptions.ProductCategoryOperationException;
import com.al.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:ProductCategoryServiceImpl
 * @Description
 * @date2021/5/17 21:25
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryByShopId(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException {
        // 解除tb_product里的商品与该producategoryId的关联
        try {
                int pcId = productDao.updateProductCategoryToNull(productCategoryId);
            if (pcId < 0){
                throw new ProductCategoryOperationException("商品类别更新失败");
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("errMsg"+e.getMessage());
        }
        // 删除该productCategory
        try {
            int effectNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if (effectNum <= 0){
                throw new ProductCategoryOperationException("商品类别删除失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("errMsg"+e.getMessage());
        }
    }

    @Override
    public ProductCategoryExecution batchInsertAdd(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        //判断productCategoryList是否为空
        if (productCategoryList != null && productCategoryList.size() > 0){
            try {
                //不为空，则进行批量添加
                int effectNum = productCategoryDao.batchProductCategoryAdd(productCategoryList);
                //判断批量添加是否成功
                if (effectNum < 0){
                    throw new ProductCategoryOperationException("批量添加失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryOperationException("batchAdd error:"+e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
        }
    }


}
