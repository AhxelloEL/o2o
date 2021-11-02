package com.al.o2o.web.shopadmin;

import com.al.o2o.dto.UserShopMapExecution;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.UserShopMap;
import com.al.o2o.service.UserShopMapService;
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
 * @ClassName:UserShopManagementController
 * @Description 用户店铺积分
 * @date2021/10/10 14:12
 */
@RestController
@RequestMapping("/shopadmin")
public class UserShopManagementController {
    @Autowired
    private UserShopMapService userShopMapService;

    /**
     * 获取该用户的积分
     *
     * @param request
     * @return
     */
    @GetMapping("/listusershopmapsbyshop")
    private Map<String, Object> listUserShopMapsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 从session中获取当前店铺的信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 空值判断
        if ((currentShop != null) && (currentShop.getShopId() != null) &&
                (pageIndex > -1) && (pageSize > -1)) {
            // 传入查询条件
            UserShopMap userShopMap = new UserShopMap();
            userShopMap.setShop(currentShop);
            String userName = HttpServletRequestUtil.getString(request, "userName");

            if (userName != null) {
                // 若传入顾客名，则按照顾客名模糊查询
                PersonInfo customer = new PersonInfo();
                customer.setName(userName);
                userShopMap.setUser(customer);
            }
            // 分页获取该店铺下的顾客积分列表
            UserShopMapExecution ue = userShopMapService
                    .listUserShopMap(userShopMap, pageIndex, pageSize);
            modelMap.put("userShopMapList", ue.getUserShopMapList());
            modelMap.put("count", ue.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }
}
