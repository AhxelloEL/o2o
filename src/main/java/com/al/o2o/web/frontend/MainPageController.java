package com.al.o2o.web.frontend;

import com.al.o2o.entity.HeadLine;
import com.al.o2o.entity.ShopCategory;
import com.al.o2o.service.HeadLineService;
import com.al.o2o.service.ShopCategoryService;
import com.al.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.frontend
 * @ClassName:MainPageController
 * @Description
 * @date2021/6/2 11:29
 */
@Controller
@RequestMapping(value = "/frontend")
public class MainPageController {
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "listmainpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listMainPageInfo(){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        try {
            //获取一级列表
            shopCategoryList= shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        List<HeadLine> headLineList = new ArrayList<HeadLine>();
        //获取头条展示
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            headLineList = headLineService.queryHeadLineList(headLine);
            modelMap.put("headLineList",headLineList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        modelMap.put("success",true);
        return modelMap;
    }

    @RequestMapping(value = "getheadlinelist",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getHeadLineList(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        Long lineId = HttpServletRequestUtil.getLong(request,"lineId");
        if (lineId > -1){
            try {
                //获取头条信息
                HeadLine headLine = headLineService.getByLineId(lineId);
                modelMap.put("success",true);
                modelMap.put("headLine",headLine);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","lineId为空 获取头条信息失败");
        }
        return modelMap;
    }
}
