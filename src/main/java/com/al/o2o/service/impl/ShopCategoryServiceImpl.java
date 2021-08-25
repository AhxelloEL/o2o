package com.al.o2o.service.impl;

import com.al.o2o.cache.JedisUtil;
import com.al.o2o.dao.ShopCategoryDao;
import com.al.o2o.entity.ShopCategory;
import com.al.o2o.service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 店铺类型查询
     * @param shopCategoryCondition
     * @return
     */
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        String key = SHOPCATEGORYKEY;
        List<ShopCategory> shopCategoryList = null;
        ObjectMapper mapper = new ObjectMapper();
        // 拼接出redis的key
        if (shopCategoryCondition == null) {
            // 若查询条件为空，则列出所有首页大类，即parentId为空的店铺类别
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null
                && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            // 若parentId为非空，则列出该parentId下的所有子类别
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            // 列出所有子类别，不管其属于哪个类，都列出来
            key = key + "_allsecondlevel";
        }
        if (!jedisKeys.exists(key)){
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            jedisStrings.set(key,jsonString);
            //设置缓存过期时间，默认60000
            jedisUtil.expire(key);
        }else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                shopCategoryList = mapper.readValue(jsonString,javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }finally {
                jedisUtil.jedisClose();
            }
        }
        return shopCategoryList;
    }
}
