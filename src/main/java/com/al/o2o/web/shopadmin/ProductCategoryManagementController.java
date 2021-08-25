package com.al.o2o.web.shopadmin;

import com.al.o2o.dto.ProductCategoryExecution;
import com.al.o2o.dto.Result;
import com.al.o2o.entity.ProductCategory;
import com.al.o2o.entity.Shop;
import com.al.o2o.enums.ProductCategoryStateEnum;
import com.al.o2o.exceptions.ProductCategoryOperationException;
import com.al.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.shopadmin
 * @ClassName:ProductCategoryManagementController
 * @Description
 * @date2021/5/17 21:27
 */
@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        //从前端获取shopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        //非空判断
        if ( currentShop != null && currentShop.getShopId() > 0){
            productCategoryList = productCategoryService.getProductCategoryByShopId(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,productCategoryList);
        }else {
            ProductCategoryStateEnum pce = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,pce.getState(),pce.getStateInfo());
        }
    }

    @RequestMapping(value = "/removeproductcategory",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> removeProductCategory(Long productCategoryId,HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                //从前台获取shopId
                //Long shopId = (Long) request.getSession().getAttribute("shopId");
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            }catch (ProductCategoryOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少现在一个类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addproductcategorys",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //ProductCategory productCategory = new ProductCategory();
        //1.从前台获取ShopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory pc: productCategoryList) {
            pc.setShopId(currentShop.getShopId());
        }
        //2.判断是否获取成功
        if (productCategoryList != null && productCategoryList.size() > 0){
            try{
                //3.批量添加，并给createTime赋初始值
               // productCategory.setCreateTime(new Date());
                ProductCategoryExecution pe = productCategoryService.batchInsertAdd(productCategoryList);
                //4.判断是否添加成功
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }
            }catch (ProductCategoryOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg" ,e.getMessage());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少添加一个商品类别");
        }
        return modelMap;
    }
}
