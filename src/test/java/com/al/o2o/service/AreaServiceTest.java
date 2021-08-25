package com.al.o2o.service;


import com.al.o2o.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest  {
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;


    @Test
    @Ignore
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("西苑", areaList.get(0).getAreaName());
        System.out.println(areaList.get(0).getAreaName());
       // cacheService.removeFromCache(areaService.AREALISTKEY);
      //  areaList = areaService.getAreaList();
    }
}
