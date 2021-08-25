package com.al.o2o.service;

import com.al.o2o.dto.LocalAuthExecution;
import com.al.o2o.entity.LocalAuth;
import com.al.o2o.exceptions.LocalAuthOperationException;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:LocalAuthService
 * @Description 平台账号接口，用于登入、查询用户信息、绑定微信账号、更新密码
 * @date2021/8/4 17:50
 */
public interface LocalAuthService {
    /**
     * 用户登入
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    LocalAuth getLocalAuthByUserNameAndPwd(String username, String password);

    /**
     * 根据userId查询详情信息
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信账号
     * @param localAuth
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     * 修改密码
     * @param userId 平台账户ID
     * @param username 用户名
     * @param password 密码
     * @param newPassword 新密码
     * @throws LocalAuthOperationException
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password,
                                       String newPassword) throws LocalAuthOperationException;
}
