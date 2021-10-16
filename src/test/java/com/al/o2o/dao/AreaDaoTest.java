package com.al.o2o.dao;



import com.al.o2o.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest  {
    @Autowired
    private AreaDao areaDao;

    /**
     * 测试areaDao_QueryAreaList
     * （repository接口）
     */
    @Test
    @Ignore
    public void test1(){
       List<Area> optionalArea = areaDao.queryArea();
        assertEquals(2,optionalArea.size());
    }

    /**
     * 测试areaDao_InsertArea
     * (repository接口)
     */
    @Test
    public void testInsertArea(){
        Area area = new Area();
        area.setAreaName("南苑");
        area.setPriority(1);
        area.setCreateTime(new Date());
        int effectNum = areaDao.insertArea(area);
        assertEquals(1,effectNum);
    }

    /**
     * 测试areaDao_UpdateArea
     * (repository接口)
     */
    @Test
    public void testUpdateArea(){
        Area area = new Area();
        area.setAreaId(3);
        area.setPriority(4);
        area.setLastEditTime(new Date());
        int effectNum = areaDao.updateArea(area);
        assertEquals(1,effectNum);
    }

    /**
     * 测试areaDao_DeleteArea
     * (repository接口)
     */
    @Test
    public void testDeleteArea(){
        long areaId = 3l;
        int effectNum = areaDao.deleteArea(areaId);
        assertEquals(1,effectNum);
    }

    /**
     * 测试areaDao_batchDeleteArea
     * (repository接口)
     */
    @Test
    public void batchDeleteArea(){
        //TODO
    }


}
