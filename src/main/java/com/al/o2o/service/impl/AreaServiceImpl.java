package com.al.o2o.service.impl;

import com.al.o2o.cache.JedisUtil;
import com.al.o2o.dao.AreaDao;
import com.al.o2o.entity.Area;
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
import java.util.List;

/**
 * @author yunSun
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
        if (!jedisKeys.exists(key)){
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
            jedisStrings.set(key,jsonString);
            //设置缓存过期时间，默认60000
            jedisUtil.expire(key);
        }else {
            //如果不为空，则直接从redis中取出数据
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString,javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.toString());
                throw new AreaOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //释放资源
                jedisUtil.jedisClose();
            }
        }
        return areaList;
    }
}
