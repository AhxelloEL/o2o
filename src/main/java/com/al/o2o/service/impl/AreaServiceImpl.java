package com.al.o2o.service.impl;

import com.al.o2o.cache.JedisUtil;
import com.al.o2o.dao.AreaDao;
import com.al.o2o.dto.AreaExecution;
import com.al.o2o.entity.Area;
import com.al.o2o.enums.AreaStateEnum;
import com.al.o2o.exceptions.AreaOperationException;
import com.al.o2o.service.AreaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:AreaServiceImpl
 * @Description 区域业务接口实现类
 * @date2021/8/10 11:12
 */

@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil.Lists jedisLists;
    @Autowired
    private JedisUtil jedisUtil;


    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    @Transactional(rollbackFor = AreaOperationException.class)
    public List<Area> getAreaList() {
        //设置redis的Key
        String key = AREALISTKEY;
        List<Area> areaList = null;
        //json转换类
        ObjectMapper mapper = new ObjectMapper();
        //判断传入的key，在redis中是否为空
        if (!jedisKeys.exists(key)) {
            //如果为空，则先从数据库中取出数据
            areaList = areaDao.queryArea();
            String jsonString;
            try {
                //将取出的数据转换成String
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.toString());
                throw new AreaOperationException(e.getMessage());
            }
            //将取出的数据存入redis中
            jedisStrings.set(key, jsonString);
            //设置缓存过期时间，默认60000
            jedisUtil.expire(key);
        } else {
            //如果不为空，则直接从redis中取出数据
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.toString());
                throw new AreaOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //释放资源
                jedisUtil.jedisClose();
            }
        }
        return areaList;
    }

    @Override
    @Transactional
    public AreaExecution addArea(Area area) {
        if (area != null && area.getAreaName() != null) {
            area.setCreateTime(new Date());
            try {
                int effectNum = areaDao.insertArea(area);
                if (effectNum <= 0) {
                    return new AreaExecution(AreaStateEnum.INNER_ERROR, area);
                }
                return new AreaExecution(AreaStateEnum.SUCCESS, area);
            } catch (Exception e) {
                throw new RuntimeException("添加区域信息失败:" + e.getMessage());
            }
        } else {
            return new AreaExecution(AreaStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public AreaExecution modifyArea(Area area) {
        if (area != null && area.getAreaId() != null && area.getAreaId() > 0) {
            area.setLastEditTime(new Date());
            try {
                int effectNum = areaDao.updateArea(area);
                if (effectNum <= 0) {
                    return new AreaExecution(AreaStateEnum.INNER_ERROR, area);
                }
                return new AreaExecution(AreaStateEnum.SUCCESS, area);
            } catch (Exception e) {
                throw new RuntimeException("更新区域信息失败:" + e.getMessage());
            }
        } else {
            return new AreaExecution(AreaStateEnum.EMPTY);
        }
    }
}
