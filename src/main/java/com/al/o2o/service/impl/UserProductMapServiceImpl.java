package com.al.o2o.service.impl;

import com.al.o2o.dao.UserProductMapDao;
import com.al.o2o.dto.UserProductMapExecution;
import com.al.o2o.entity.UserProductMap;
import com.al.o2o.service.UserProductMapService;
import com.al.o2o.util.PageCalculator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:UserProductMapServiceImpl
 * @Description
 * @date2021/9/8 10:11
 */
@Service
public class UserProductMapServiceImpl implements UserProductMapService {
    @Resource
    private UserProductMapDao userProductMapDao;

    @Override
    public UserProductMapExecution getUserProductMapList(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize) {
        if (userProductCondition != null && pageIndex != null && pageSize != null){
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
            List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductCondition,beginIndex,pageSize);
            //按照同等查询条件获取总数
            int count = userProductMapDao.queryUserProductCount(userProductCondition);
            UserProductMapExecution ume = new UserProductMapExecution();
            ume.setUserProductMapList(userProductMapList);
            ume.setCount(count);
            return ume;
        }else {
            return null;
        }
    }

    @Override
    public UserProductMapExecution addUserProductMap(UserProductMap userProductMap) {
        return null;
    }

    @Override
    public UserProductMapExecution modifyUserProductMapPoint(UserProductMap userProductCondition) {
        return null;
    }
}
