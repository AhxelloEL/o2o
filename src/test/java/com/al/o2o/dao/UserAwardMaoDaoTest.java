package com.al.o2o.dao;

import com.al.o2o.entity.Award;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.Shop;
import com.al.o2o.entity.UserAwardMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @ClassName:UserAwardMaoDaoTest
 * @Description
 * @date2021/8/23 18:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAwardMaoDaoTest {
    @Resource
    private UserAwardMapDao userAwardMapDao;

    UserAwardMap userAwardMap = new UserAwardMap();
    PersonInfo user = new PersonInfo();
    Shop shop = new Shop();
    Award award = new Award();
    @Test
    public void testAUserAwardList(){
        user.setName("测");
        userAwardMap.setUser(user);
        List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap,0,3);
        int count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
        System.out.println("奖品列表的大小：" + userAwardMapList.size());
        System.out.println("奖品总数：" + count);
    }

    @Test
    public void testBUserAwardById(){
        userAwardMap.setUserAwardId(1l);
        UserAwardMap effectNum = userAwardMapDao.queryUserAwardMapById(userAwardMap.getUserAwardId());
        System.out.println(effectNum.getAward().getAwardName());
    }

    @Test
    public void testCUserAwardInsert(){
        award.setAwardId(20l);
        shop.setShopId(29l);
        user.setUserId(1l);
        userAwardMap.setUser(user);
        userAwardMap.setOperator(user);
        userAwardMap.setShop(shop);
        userAwardMap.setAward(award);
        userAwardMap.setCreateTime(new Date());
        userAwardMap.setUsedStatus(1);
        userAwardMap.setPoint(87);
        int effectNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
        assertEquals(1,effectNum);
    }

    @Test
    public void testDUserAwardUpdate() throws  Exception{
        // 按用户名模糊查询
        user.setName("测试");
        userAwardMap.setUser(user);
        List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 1);
        assertTrue("Error, 积分不一致！", 0 == userAwardMapList.get(0).getUsedStatus());
        userAwardMapList.get(0).setUsedStatus(1);
        int effectedNum = userAwardMapDao.updateUserAwardMap(userAwardMapList.get(0));
        assertEquals(1, effectedNum);
    }
}
