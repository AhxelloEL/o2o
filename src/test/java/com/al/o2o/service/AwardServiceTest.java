package com.al.o2o.service;

import com.al.o2o.entity.Award;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:AwardServiceTest
 * @Description
 * @date2021/8/20 16:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardServiceTest {
    @Autowired
    private AwardService awardService;

    @Test
    public void testAGetAwardById(){
        long awardId = 20l;
        Award awardCondition = awardService.getByAwardId(awardId);
        System.out.println(awardCondition.getAwardName());
    }
}
