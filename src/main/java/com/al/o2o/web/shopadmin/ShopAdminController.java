package com.al.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        //转发至店铺列表页面
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        //转发至店铺管理页面
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
    private String productCategoryManage() {
        // 转发至商品类别管理页面
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManage() {
        //转发至商品管理页面
        return "shop/productmanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }

    @RequestMapping(value = "/shopauthmanagement")
    public String shopAuthManagement() {
        // 转发至店铺授权页面
        return "shop/shopauthmanagement";
    }

    @RequestMapping(value = "/shopauthedit")
    public String shopAuthEdit() {
        // 转发至授权信息修改页面
        return "shop/shopauthedit";
    }
    @RequestMapping(value = "/operationsuccess", method = RequestMethod.GET)
    private String operationSuccess() {
        // 转发至操作失败的页面
        return "shop/operationsuccess";
    }

    @RequestMapping(value = "/operationfail", method = RequestMethod.GET)
    private String operationFail() {
        // 转发至操作成功的页面
        return "shop/operationfail";
    }

    @RequestMapping(value = "/productbuycheck", method = RequestMethod.GET)
    private String productBuyCheck() {
        // 转发至店铺的消费记录的页面
        return "shop/productbuycheck";
    }



}
