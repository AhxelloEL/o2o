package com.al.o2o.web.shopadmin;

import com.al.o2o.dto.ShopAuthMapExecution;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.ShopAuthMap;
import com.al.o2o.enums.ShopAuthMapStateEnum;
import com.al.o2o.service.ShopAuthMapService;
import com.al.o2o.util.CodeUtil;
import com.al.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.shopadmin
 * @ClassName:ShopAuthMapController
 * @Description
 * @date2021/8/25 14:49
 */
@RestController
@RequestMapping("/shopadmin")
public class ShopAuthMapController {
    @Autowired
    private ShopAuthMapService shopAuthMapService;

    @RequestMapping(value = "/listshopauthmapsbyshop",method = RequestMethod.GET)
    private Map<String,Object> listShopAuthMapsByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if ((pageIndex > -1 ) && (pageSize > -1)
                && (currentShop != null) && (currentShop.getShopId() != null)){
            ShopAuthMapExecution same = shopAuthMapService.getShopAuthMapListByShopId(currentShop.getShopId(),pageIndex,pageSize);
            modelMap.put("shopAuthMapList",same.getShopAuthMapList());
            modelMap.put("count",same.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex or ShopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshopauthmapbyid",method = RequestMethod.GET)
    private Map<String,Object> getShopAuthMapById(@RequestParam Long shopAuthId){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        if (shopAuthId != null && shopAuthId > -1){
            ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
            modelMap.put("shopAuthMap",shopAuthMap);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty shopAuthId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyshopauthmap", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShopAuthMap(String shopAuthMapStr, HttpServletRequest request) throws IOException {
        // 是授权编辑时候调用还是删除/恢复授权操作的时候调用
        // 若为前者则进行验证码判断，后者则跳过验证码判断
        Map<String,Object> modelMap = new HashMap<String, Object>();
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        // 验证码校验
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 将前台传入的字符串json转换成shopAuthMap实例
        ObjectMapper mapper = new ObjectMapper();
        ShopAuthMap shopAuthMap = null;
        try {
            shopAuthMap = mapper.readValue(shopAuthMapStr,ShopAuthMap.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        // 空值判断
        if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null){
            try {
                // 看看被操作的对方是否为店家本身，店家本身不支持修改
                if (!checkPermission(shopAuthMap.getShopAuthId())){
                   modelMap.put("success",false);
                   modelMap.put("errMsg","无法对店家本身权限做操作(已是店铺的最高权限");
                   return modelMap;
                }
                ShopAuthMapExecution same = shopAuthMapService.modifyShopAuthMap(shopAuthMap);
                if (same.getState() == ShopAuthMapStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",same.getStateInfo());
                }
            }catch (RuntimeException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入要修改的授权信息");
        }
        return modelMap;
    }

    /**
     * 检查被操作的对象是否可修改
     *
     * @param shopAuthId
     * @return
     */
    private boolean checkPermission(Long shopAuthId) {
        ShopAuthMap grantedPerson = shopAuthMapService.getShopAuthMapById(shopAuthId);
        if (grantedPerson.getTitleFlag() == 0) {
            // 若是店家本身，不能操作
            return false;
        } else {
            return true;
        }
    }
}
