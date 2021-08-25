package com.al.o2o.web.frontend;

import com.al.o2o.dto.ShopExecution;
import com.al.o2o.entity.Area;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.ShopCategory;
import com.al.o2o.service.AreaService;
import com.al.o2o.service.ShopCategoryService;
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
 * @ClassName:ShopListController
 * @Description
 * @date2021/6/16 19:00
 */
@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    /**
     * 返回商品列表页里的shopcategory列表（二级或一级），以及区域信息列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopspageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //从前端获取请求中的prentId
        long parentId = HttpServletRequestUtil.getLong(request,"parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1 ){
            //如果parentId存在，则取出该一级shopcategory下的二级shopcategory列表
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            try {
                //如果parentId不存在，则取出所有一级shopcategory
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }

        }
        modelMap.put("shopCategoryList",shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/listshops",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShops(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        //非空判断
        if ((pageIndex > -1 )&&(pageSize > -1)){
            //获取一级类别
            long parentId = HttpServletRequestUtil.getLong(request,"parentId");
            //获取二级类别
            long shopCategoryId = HttpServletRequestUtil.getLong(request,"shopCategoryId");
            //获取区域信息
            int areaId = HttpServletRequestUtil.getInt(request,"areaId");
            //获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request,"shopName");
            //获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);
            ShopExecution se = shopService.getShopList(shopCondition,pageIndex,pageSize);
            modelMap.put("shopList",se.getShopList());
            modelMap.put("count",se.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or PageIndex");
        }
        return modelMap;
    }

    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName){
        Shop shopCondition = new Shop();
        if (parentId != -1L){
            //查询某个一级列表shopcategory下面所有的二级列表shopcategory里面的店铺列表
            ShopCategory childCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId != -1L){
            //查询某个二级shopcategory下面的店铺列表
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1){
            //查询位于某个区域Id下的店铺列表
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if (shopName != null){
            shopCondition.setShopName(shopName);
        }
        //筛选展示的店铺都是审核通过的状态
        shopCondition.setEnableStatus(1);
        return shopCondition;
    }
}
