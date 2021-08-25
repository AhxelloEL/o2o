package com.al.o2o.web.superadmin;

import com.al.o2o.entity.Area;
import com.al.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
   Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;
    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    //自动的转化为josn数据
    private Map<String,Object> listArea(){
        logger.info("---start----");
        Long startTime = System.currentTimeMillis();
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //定义一个list集合，存放area数据
        List<Area> list = new ArrayList<Area>();
        try {
            //将查询到的区域列表，存放到list中
            list = areaService.getAreaList();
            //数据放到map集合中，通过key-value调用
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        logger.error("test error!");
        Long endTime = System.currentTimeMillis();
        logger.debug("costTime:[{}ms]",endTime-startTime);
        logger.info("---end----");
        //返回结果集
        return modelMap;
    }
}
