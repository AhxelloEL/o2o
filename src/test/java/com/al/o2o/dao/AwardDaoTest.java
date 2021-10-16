package com.al.o2o.dao;

import com.al.o2o.entity.Award;
import com.al.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @ClassName:AwardDao
 * @Description
 * @date2021/8/20 11:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {
    @Resource
    private AwardDao awardDao;
    @Test
    @Ignore
    public void testAInsertAwawrd(){
        Shop shop = new Shop();
        Award award = new Award();
        shop.setShopId(28L);
        award.setAwardName("美心流心月饼");
        award.setAwardDesc("中国香港 美心（Meixin）流心奶黄 港式中秋月饼礼盒 360g 8枚装");
        award.setAwardImg("test");
        award.setPoint(99);
        award.setPriority(5);
        award.setCreateTime(new Date());
        award.setEnableStatus(1);
        award.setShop(shop);
        int effectNum = awardDao.insertAward(award);
        assertEquals(1,effectNum);
    }

    @Test
    @Ignore
    public void testBQueryAwardList(){
        Shop shopCondition = new Shop();
        Award awardCondition = new Award();
        //shopCondition.setShopId(28l);
        //awardCondition.setShop(shopCondition);
        awardCondition.setAwardName("美心");
        List<Award> awardList = awardDao.queryAwardList(awardCondition,0,3);
        int count = awardDao.queryAwardCount(awardCondition);
        System.out.println("奖品列表的大小：" + awardList.size());
        System.out.println("奖品总数：" + count);
    }

    @Test
    @Ignore
    public void testCQueryAwardByAwardId(){
        Long awardId = 25l;
        Award effectNum = awardDao.queryAwardByAwardId(awardId);
        System.out.println(effectNum.getShop().getShopName());
        assertEquals("美心流心月饼",effectNum.getAwardName());
    }

    @Test
    @Ignore
    public void testDUpdateAward(){
        Award award = new Award();
        award.setAwardId(25l);
        award.setPoint(98);
        award.setLastEditTime(new Date());
        int effectNum = awardDao.updateAward(award);
        assertEquals(1,effectNum);
    }

    @Test
    @Ignore
    public void testEDeleteAward(){
        Award awardCondition = new Award();
        awardCondition.setAwardName("美心流心月饼");
        // 查询出所有测试奖品并删除
        List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 2);
        assertEquals(1, awardList.size());
        for (Award award : awardList) {
            int effectedNum = awardDao.deleteAward(award.getAwardId(), award.getShop().getShopId());
            assertEquals(1, effectedNum);
        }
    }
}
