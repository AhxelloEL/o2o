package com.al.o2o.service.impl;

import com.al.o2o.dao.LocalAuthDao;
import com.al.o2o.dto.LocalAuthExecution;
import com.al.o2o.entity.LocalAuth;
import com.al.o2o.enums.LocalAuthStateEnum;
import com.al.o2o.exceptions.LocalAuthOperationException;
import com.al.o2o.service.LocalAuthService;
import com.al.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:LocalAuthServiceImpl
 * @Description
 * @date2021/8/5 12:50
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    private LocalAuthDao localAuthDao;
    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMD5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = LocalAuthOperationException.class)
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        //空值判断
        if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null ||
            localAuth.getPersonInfo() == null && localAuth.getPersonInfo().getUserId() == null){
            return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            //查询此用户是否已经绑定过平台账户
            LocalAuth lauser = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
            if (lauser != null){
                return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
            }else {
                //如果没有绑定则创建一个平台账户与该用户绑定
                localAuth.setCreateTime(new Date());
                localAuth.setLastEditTime(new Date());
                //对密码进行md5加密
                localAuth.setPassword(MD5.getMD5(localAuth.getPassword()));
                int effectNum = localAuthDao.insertLocalAuth(localAuth);
                if (effectNum <= 0){
                    throw  new LocalAuthOperationException("账户绑定失败！");
                }else {
                    return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
                }
            }
        }catch (Exception e){
            throw new LocalAuthOperationException("绑定平台账户失败！:"+e.getMessage());
        }
    }



    @Override
    @Transactional(rollbackFor = LocalAuthOperationException.class)
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        //非空判断，传入的用户Id、用户名、新旧密码是否相同
        if (userId != null && username != null && password != null && !password.equals(newPassword)){
            try {
                //更新密码，并对新密码加密
                int effectNum = localAuthDao.updateLocalAuth(userId,username,MD5.getMD5(password),MD5.getMD5(newPassword),new Date());
                //判断是否修改成功
                if (effectNum > 0){
                    return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
                }else {
                    throw new LocalAuthOperationException("更新操作失败!");
                }
            }catch (Exception e){
                throw new LocalAuthOperationException("localAuthUpdate error :"+e.getMessage());
            }
        }else {
            //若不满足则返回错误信息
            throw new LocalAuthOperationException("更新失败！");
        }
    }
}
