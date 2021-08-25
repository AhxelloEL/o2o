package com.al.o2o.web.frontend;

import com.al.o2o.entity.Product;
import com.al.o2o.service.ProductService;
import com.al.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.frontend
 * @ClassName:ProductDetailController
 * @Description
 * @date2021/6/20 19:53
 */
@Controller
@RequestMapping(value = "frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "listproductdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductDetailPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //获取productId
        long productId = HttpServletRequestUtil.getLong(request,"productId");
        Product product = null;
        if (productId != -1 ){
            product = productService.getByProductId(productId);
            modelMap.put("product",product);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }
        return modelMap;
    }

}
