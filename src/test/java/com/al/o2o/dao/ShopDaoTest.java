package com.al.o2o.dao;


import com.al.o2o.entity.Area;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopDaoTest  {
    @Resource
    private ShopDao shopDao;

    /**
     * 新增店铺测试
     */
    @Test
    @Ignore
    public void test1(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("alex");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    /**
     * 修改店铺信息测试
     */
    @Test
    @Ignore
    public void test2(){
        Shop shop = new Shop();
        shop.setShopId(92L);
        shop.setShopName("肯德基");
        shop.setShopDesc("冰淇淋");
        shop.setShopAddr("海雅缤纷城3F-324");
        shop.setLastEditTime(new Date());
        int effectNum = shopDao.updateShop(shop);
        assertEquals(1,effectNum);
    }

    @Test
    @Ignore
    public void test3(){
        long shopId = 28;
        Shop shop =  shopDao.queryByShopId(shopId);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getShopName());

    }

    @Test
    @Ignore
    public void test4(){
        Shop shopCodition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCodition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCodition,0,5);
        int shopCount = shopDao.queryShopCount(shopCodition);
        System.out.println("店铺列表大小:"+shopList.size());
        System.out.println("店铺总数；"+shopCount);
    }

    /**
     * 根据Id查询信息
     */
    @Test
    @Ignore
    public void test5(){
        Shop shop = new Shop();
        shop.setShopId(28L);
        Shop shop1 = shopDao.queryByShopId(shop.getShopId());
        System.out.println(shop1.getArea().getAreaId());
        System.out.println(shop1.getArea().getAreaName());
        System.out.println(shop1.getShopName());
    }


    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();
        ShopCategory childCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(12L);
        childCategory.setParent(parentCategory);
        shopCondition.setShopCategory(childCategory);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表大小："+shopList.size());
        System.out.println("店铺总数："+count);
    }
}
