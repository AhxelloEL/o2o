package com.al.o2o.service.impl;

import com.al.o2o.cache.JedisUtil;
import com.al.o2o.dao.HeadLineDao;
import com.al.o2o.entity.HeadLine;
import com.al.o2o.service.HeadLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:HeadLineServiceImpl
 * @Description
 * @date2021/6/2 9:42
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 头条展示
     * @param headLineCondition
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<HeadLine> queryHeadLineList(HeadLine headLineCondition) {
        //设置Key
        String key = HEADLINEKEY;
        List<HeadLine> headLineList = null;
        //定义json转换类
        ObjectMapper mapper = new ObjectMapper();
        //根据不同的判断条件，拼接Key
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        //如果在缓存中找不到对应的key，则去数据库里取数据
        if (!jedisKeys.exists(key)){
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            String jsonString;
            try {
                //将list转换成string类型
                jsonString =mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            //保存到缓存中
            jedisStrings.set(key,jsonString);
            //设置缓存过期时间，默认60000
            jedisUtil.expire(key);
        }else {
            //如果有对应的key，则直接从缓存中取出
            String jsonString = jedisStrings.get(key);
            //将String转换成list
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try {
                headLineList = mapper.readValue(jsonString,javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //释放资源
                jedisUtil.jedisClose();
            }
        }
        return headLineList;
    }

    /**
     * 根据头条Id返回唯一信息
     * @param lineId
     * @return
     */
    @Override
    public HeadLine getByLineId(long lineId) {
        return headLineDao.queryHeadLineById(lineId);
    }
}
