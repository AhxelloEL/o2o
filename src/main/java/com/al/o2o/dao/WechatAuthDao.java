package com.al.o2o.dao;

import com.al.o2o.entity.WechatAuth;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @ClassName:WechatAuthDao
 * @Description 微信用户
 * @date2021/8/2 16:52
 */
public interface WechatAuthDao {
    /**
     * 通过openId查询对应本平台的微信账号
     * @param openId
     * @return
     */
    WechatAuth queryWechatAuthById(String openId);

    /**
     * 添加对应本平台的微信账号
     * @param wechatAuth
     * @return
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}
