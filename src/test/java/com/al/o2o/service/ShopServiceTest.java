package com.al.o2o.service;


import com.al.o2o.dto.ImageHolder;
import com.al.o2o.dto.ShopExecution;
import com.al.o2o.entity.Area;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.ShopCategory;
import com.al.o2o.enums.ShopStateEnum;
import com.al.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceTest  {
    @Autowired
    private ShopService shopService;

    /**
     * 测试店铺注册
     */
    @Test
    @Ignore
    public void test1() throws ShopOperationException,FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(29L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setPhone("1241512521");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:/Users/yunSun/Desktop/user/zZzZ.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
        ShopExecution se = shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }

    /**
     * 没过ut
     * @throws FileNotFoundException
     * @throws FileNotFoundException
     */
    @Test
    @Ignore
    public void test3() throws FileNotFoundException ,FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId(63L);
        shop.setShopName("更新店铺");
       // //File shopImg = new File("D:/projectdev/image/upload/images/item/shop/32/2017092601081463136.jpg");
      //  InputStream is = new FileInputStream(shopImg);
      //  ImageHolder imageHolder = new ImageHolder("dabai.jpg", is);
        ShopExecution shopExecution = shopService.modifyShop(shop,null);
        System.out.println("新的图片地址："+shopExecution.getShop().getShopImg());
    }

    @Test
    @Ignore
    public void test4(){
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(22L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition,2,5);
        System.out.println("店铺列表数为："+se.getShopList().size());
        System.out.println("店铺总数为:"+se.getCount());
    }

    @Test
    @Ignore
    public void test5(){
        Shop shopCondition = new Shop();
        shopCondition.setShopId(28l);
        Shop shopList = shopService.getByShopId(shopCondition.getShopId());
        System.out.println(shopList.getShopName());
        System.out.println(shopList.getArea().getAreaId());
        System.out.println(shopList.getArea().getAreaName());
    }
}
