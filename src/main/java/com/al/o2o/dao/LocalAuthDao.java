package com.al.o2o.dao;

import com.al.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dao
 * @InterfaceName:LocalAuth
 * @Description 关联微信账号
 * @date2021/8/4 16:32
 */
public interface LocalAuthDao {
    /**
     * 登入操作
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 通过userId查询本地账号信息
     * @param userId 用户iD
     * @return
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    /**
     * 关联账号
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * 修改密码
     * @param userId 用户ID
     * @param username 用户名
     * @param password 用户密码
     * @param newPassword 新密码
     * @param lastEditTime 更新时间
     * @return
     */
    int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
