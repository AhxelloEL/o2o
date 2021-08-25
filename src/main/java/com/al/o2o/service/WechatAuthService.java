package com.al.o2o.service;

import com.al.o2o.dto.WechatAuthExecution;
import com.al.o2o.entity.WechatAuth;
import com.al.o2o.exceptions.WechatAuthOperationException;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:WechatAuthService
 * @Description
 * @date2021/8/3 12:20
 */
public interface WechatAuthService {
    /**
     * 通过OpenID查找平台对应的微信账号
     * @param OpenId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String OpenId);

    /**
     * 注册微信用户
     * @param wechatAuth
     * @return
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
