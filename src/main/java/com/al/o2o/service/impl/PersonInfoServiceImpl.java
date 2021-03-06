package com.al.o2o.service.impl;

import com.al.o2o.dao.PersonInfoDao;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:PersonInfoServiceImpl
 * @Description
 * @date2021/8/3 14:27
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoDao personInfoDao;
    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }
}
