package com.al.o2o.web.frontend;

import com.al.o2o.dto.ProductExecution;
import com.al.o2o.entity.Product;
import com.al.o2o.entity.ProductCategory;
import com.al.o2o.entity.Shop;
import com.al.o2o.service.ProductCategoryService;
import com.al.o2o.service.ProductService;
import com.al.o2o.service.ShopService;
import com.al.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.frontend
 * @ClassName:ShopDetailController
 * @Description
 * @date2021/6/20 19:08
 */
@RequestMapping(value = "/frontend")
@Controller
public class ShopDetailController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/listshopdetailpageinfo",method= RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopDetailPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //从前端获取shopId
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList = null;
        if (shopId != -1){
            //获取店铺Id为shopId的信息
            shop = shopService.getByShopId(shopId);
            //获取店铺下的商品类别列表
            productCategoryList = productCategoryService.getProductCategoryByShopId(shopId);
            modelMap.put("shop",shop);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty shopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/listproductsbyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductsByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //获取index和size
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if ((pageIndex > -1) && (pageSize > -1)){
            long productCategoryId = HttpServletRequestUtil.getLong(request,"productCategoryId");
            String productName = HttpServletRequestUtil.getString(request,"productName");
            Product productCondition = compactProductCondition4Search(shopId,productCategoryId,productName);
            ProductExecution se = productService.getProductList(productCondition,pageIndex,pageSize);
            modelMap.put("productList",se.getProductList());
            modelMap.put("count",se.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }
        return modelMap;
    }

    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName){
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (productName != null){
            productCondition.setProductName(productName);
        }
        productCondition.setEnableStatus(1);
        return productCondition;
    }
}
