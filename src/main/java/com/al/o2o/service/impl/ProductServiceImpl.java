package com.al.o2o.service.impl;

import com.al.o2o.dao.ProductDao;
import com.al.o2o.dao.ProductImageDao;
import com.al.o2o.dto.ImageHolder;
import com.al.o2o.dto.ProductExecution;
import com.al.o2o.entity.Product;
import com.al.o2o.entity.ProductImg;
import com.al.o2o.enums.ProductStateEnum;
import com.al.o2o.exceptions.ProductOperationException;
import com.al.o2o.service.ProductService;
import com.al.o2o.util.ImageUtil;
import com.al.o2o.util.PageCalculator;
import com.al.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:ProductServiceImpl
 * @Description
 * @date2021/5/16 22:35
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public ProductExecution getProductList(Product product, int pageIndex, int pageSize) {
        //1.转化rowIndex
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        //2.分页查询
        List<Product> productList = productDao.productList(product,rowIndex,pageSize);
        //3.查询总数
        int productCount = productDao.queryProductCount(product);
        //返回状态标识
        ProductExecution pe = new ProductExecution();
        //4.判断查询是否成功
        if (productList != null){
            pe.setProductList(productList);
            pe.setCount(productCount);
        }else {
            pe.setState(ProductStateEnum.OFFLINE.getState());
        }

        return pe;
    }

    /**
     * 查询商品列表信息
     * @param productId 商品Id
     * @return
     */
    @Override
    public Product getByProductId(Long productId) {
        return productDao.queryByProductId(productId);
    }

    /**
     * 添加商品
     * @param product
     * @param thumbnail
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException {
        // 空值判断
        if (product !=null && product.getShop() != null && product.getShop().getShopId() > 0 ){
            // 给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            // 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null){
                addThumbnail(product,thumbnail);
            }
            try {
                //添加商品信息
                int effectNum = productDao.insertProduct(product);
                if (effectNum <= 0){
                    throw new ProductOperationException("Product creation failed !");
                }
            }catch (Exception e){
                throw new ProductOperationException("Product creation failed :" +e.getMessage());
            }
            //若商品详情图不为空则添加
            if (productImgHolderList != null && productImgHolderList.size() > 0 ){
                batchAddProductImg(product,productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 更新店铺操作
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
        // 1.若缩略图参数有值，则处理缩略图，
        if (product != null && product.getShop() !=null && product.getShop().getShopId() !=null){
            product.setLastEditTime(new Date());
            if (thumbnail != null){
                //先获取原来的信息
                Product tempProduct = productDao.queryByProductId(product.getProductId());
                if (tempProduct.getImgAddr() != null){
                    ImageUtil.deleteFileOrPath(product.getImgAddr());
                }
                addThumbnail(product,thumbnail);
            }
            // 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
            if (productImgList != null && productImgList.size() > 0){
                deleteProductImg(product.getProductId());
                batchAddProductImg(product,productImgList);
            }
            try {
                //更新商品信息
                int effectNum = productDao.updateProduct(product);
                if (effectNum <= 0){
                    throw new ProductOperationException("更新失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败"+e.toString());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }


    /**
     * 添加缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAdd = ImageUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(thumbnailAdd);

    }
    /**
     * 批量添加图片操作
     * @param product
     * @param productImgHolderList
     */
    private void batchAddProductImg(Product product, List<ImageHolder> productImgHolderList){
        //获取图片存放路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        for (ImageHolder productImgHolder: productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setCreateTime(new Date());
            productImg.setProductId(product.getProductId());
            productImg.setImgAddr(imgAddr);
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int effectNum = productImageDao.batchInsertProductImg(productImgList);
                if (effectNum <= 0) {
                    throw new ProductOperationException("添加详情图失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("Failed to add detail diagram :"+e.getMessage());
            }
        }
    }

    /**
     * 删除某个商品下的详情图
     * @param productId
     */
    public void deleteProductImg(long productId){
       //根据productId获取原来的图片
       List<ProductImg> productImgList = productImageDao.queryProductImgList(productId);
        for (ProductImg productImg:productImgList) {
            //删除原来的图片
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库里原有图片的信息
        productImageDao.deleteProductImgByProductId(productId);
    }






















}

