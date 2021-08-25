package com.al.o2o.web.shopadmin;

import com.al.o2o.dto.AwardExecution;
import com.al.o2o.entity.Award;
import com.al.o2o.entity.Product;
import com.al.o2o.entity.ProductCategory;
import com.al.o2o.entity.Shop;
import com.al.o2o.service.AwardService;
import com.al.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.shopadmin
 * @ClassName:AwardManagementController
 * @Description
 * @date2021/8/20 15:31
 */
@Controller
@RequestMapping("/shopadmin")
public class AwardManagementController {
    @Resource
    private AwardService awardService;

    @RequestMapping(value = "/listawardsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listAwardsByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        // 获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        // 从session里获取shopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 空值校验
        if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)){
            // 判断查询条件里面是否传入奖品名，有则模糊查询
            String awardName =HttpServletRequestUtil.getString(request,"awardName");
            // 拼接查询条件
            Award awardCondition = compactAwardCondition4Search(currentShop.getShopId(),awardName);
            // 根据查询条件分页获取奖品列表即总数
            AwardExecution ae = awardService.getAwawrdList(awardCondition,pageIndex,pageSize);
            modelMap.put("success",true);
            modelMap.put("awardList",ae.getAwardList());
            modelMap.put("count",ae.getCount());
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or PageIndex or shopId");
        }
        return modelMap;
    }

    /**
     * 通过商品id获取奖品信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getawardbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getAwardById(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        // 从request里边获取前端传递过来的awardId
        long awardId = HttpServletRequestUtil.getLong(request,"awardId");
        // 空值判断
        if (awardId > -1){
            Award award = awardService.getByAwardId(awardId);
            modelMap.put("award",award);
            modelMap.put("success",true);
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty awardId");
        }
        return modelMap;
    }

    /**
     * 查询条件封装
     * @param shopId
     * @param awardName
     * @return
     */
    private Award compactAwardCondition4Search(long shopId, String awardName){
        Award awardCondition = new Award();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        awardCondition.setShop(shop);
        if (awardName != null){
            awardCondition.setAwardName(awardName);
        }
        awardCondition.setEnableStatus(1);
        return awardCondition;
    }
}
