package com.al.o2o.dao;

import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.ShopAuthMap;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @ClassName:ShopAuthDaoTest
 * @Description
 * @date2021/8/27 17:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopAuthDaoTest {
    @Resource
    private ShopAuthMapDao shopAuthMapDao;

    @Test
    @Ignore
    public void testAInsertShopAuth(){
        PersonInfo employee = new PersonInfo();
        Shop shop = new Shop();
        ShopAuthMap shopAuth = new ShopAuthMap();
        employee.setUserId(8l);
        shop.setShopId(28l);
        shopAuth.setShop(shop);
        shopAuth.setEmployee(employee);
        shopAuth.setTitle("资深员工");
        shopAuth.setTitleFlag(1);
        shopAuth.setCreateTime(new Date());
        shopAuth.setEnableStatus(1);

        int effectNum = shopAuthMapDao.insertShopAuthMap(shopAuth);
        assertEquals(1,effectNum);
    }
}
