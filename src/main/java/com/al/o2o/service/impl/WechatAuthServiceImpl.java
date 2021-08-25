package com.al.o2o.service.impl;

import com.al.o2o.dao.PersonInfoDao;
import com.al.o2o.dao.WechatAuthDao;
import com.al.o2o.dto.WechatAuthExecution;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.WechatAuth;
import com.al.o2o.enums.WechatAuthStateEnum;
import com.al.o2o.exceptions.WechatAuthOperationException;
import com.al.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:WechatAuthServiceImpl
 * @Description
 * @date2021/8/3 13:45
 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);
    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Override
    public WechatAuth getWechatAuthByOpenId(String OpenId) {
        return wechatAuthDao.queryWechatAuthById(OpenId);
    }

    @Override
    @Transactional(rollbackFor = WechatAuthOperationException.class)
    public WechatAuthExecution register(WechatAuth wechatAuth) {
        //空值判断
        if (wechatAuth == null || wechatAuth.getOpenId() == null){
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            //赋值创建时间
            wechatAuth.setCreateTime(new Date());
            //判断用户信息，userId是否为空白
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null){
                try {
                    //true -- 赋值用户表创建时间，状态，将值赋值给personInfo,添加到数据库，将值set到wechatauth中，判断是否添加成功
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if (effectNum <= 0){
                        //false 抛出报错信息，回滚
                        throw new WechatAuthOperationException("添加用户信息失败！");
                    }
                }catch (Exception e){
                    log.debug("insertPersonInfo error:"+e.toString());
                    throw new WechatAuthOperationException("insertPersonInfo error:"+e.getMessage());
                }
            }
            //创建本地微信账号
            int effectNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if (effectNum <= 0 ){
                //失败回滚
                throw new WechatAuthOperationException("创建本地微信账号失败！");
            }else {
                //创建成功调用成功的构造器
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
            }
        }catch (Exception e){
            log.debug("insertWechatAuth error:"+e.toString());
            throw new WechatAuthOperationException("insertWechatAuth error:"+e.getMessage());
        }
    }
}
