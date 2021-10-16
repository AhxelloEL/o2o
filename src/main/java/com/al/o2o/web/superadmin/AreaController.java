package com.al.o2o.web.superadmin;

import com.al.o2o.dto.AreaExecution;
import com.al.o2o.entity.Area;
import com.al.o2o.enums.AreaStateEnum;
import com.al.o2o.service.AreaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.superadmin
 * @ClassName:AreaController
 * @Description 管理员区域信息操作
 * @date2021/8/25 15:31
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listArea() {
        logger.info("---start----");
        Long startTime = System.currentTimeMillis();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //定义一个list集合，存放area数据
        List<Area> list = new ArrayList<Area>();
        try {
            //将查询到的区域列表，存放到list中
            list = areaService.getAreaList();
            //数据放到map集合中，通过key-value调用
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        logger.error("test error!");
        Long endTime = System.currentTimeMillis();
        logger.debug("costTime:[{}ms]", endTime - startTime);
        logger.info("---end----");
        //返回结果集
        return modelMap;
    }

    @RequestMapping(value = "/addarea", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addArea(HttpServletRequest request, String areaStr) {
        Map<String, Object> modelMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Area area = null;
        try {
            area = mapper.readValue(areaStr, Area.class);
            area.setAreaName((area.getAreaName() == null) ? null : URLDecoder.decode(area.getAreaName(), "UTF-8"));
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        //空值判断
        if (area != null && area.getAreaName() != null) {
            try {
                AreaExecution ae = areaService.addArea(area);
                if (ae.getState() == AreaStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", ae.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入区域信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "modifyarea", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyArea(HttpServletRequest request, String areaStr) {
        Map<String, Object> modelMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Area area = null;
        try {
            area = mapper.readValue(areaStr, Area.class);
            area.setAreaName((area.getAreaName() == null) ? null : URLDecoder.decode(area.getAreaName(), "UTF-8"));
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        //空值判断
        if (area != null && area.getAreaId() != null) {
            try {
                AreaExecution ae = areaService.modifyArea(area);
                if (ae.getState() == AreaStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", ae.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入区域信息");
        }
        return modelMap;

    }
}
