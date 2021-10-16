package com.al.o2o.web.shopadmin;

import com.al.o2o.dto.UserProductMapExecution;
import com.al.o2o.entity.Product;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.UserProductMap;
import com.al.o2o.service.ProductSellDailyService;
import com.al.o2o.service.UserProductMapService;
import com.al.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.shopadmin
 * @ClassName:UserProductManagementController
 * @Description
 * @date2021/9/8 10:43
 */
@RestController
@RequestMapping("/shopadmin")
public class UserProductManagementController {
    @Autowired
    private UserProductMapService userProductMapService;
    @Autowired
    private ProductSellDailyService productSellDailyService;

    @GetMapping(value = "/listuserproductmapsbyshop")
    private Map<String,Object> listUserProductMapsByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        // 获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        // 获取当前的店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 空值校验，主要确保shopId不为空
        if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)){
            // 添加查询条件
            UserProductMap userProductMapCondition = new UserProductMap();
            userProductMapCondition.setShop(currentShop);
            String productName = HttpServletRequestUtil.getString(request, "productName");
            if (productName != null) {
                // 若前端想按照商品名模糊查询，则传入productName
                Product product = new Product();
                product.setProductName(productName);
                userProductMapCondition.setProduct(product);
            }
            // 根据传入的查询条件获取该店铺的商品销售情况
            UserProductMapExecution ue = userProductMapService.getUserProductMapList(userProductMapCondition, pageIndex,
                    pageSize);
            modelMap.put("userProductMapList", ue.getUserProductMapList());
            modelMap.put("count", ue.getCount());
            modelMap.put("success", true);
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }
}
