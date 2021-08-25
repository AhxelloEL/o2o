package com.al.o2o.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.config.service
 * @ClassName:TransactionManagementConfiguration
 * @Description
 * @date2021/8/13 17:18
 */
@Configuration
@EnableTransactionManagement
// 首先使用注解 @EnableTransactionManagement 开启事务支持后
// 在Service方法上添加注解 @Transactional 便可
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {
    @Autowired
    private DataSource dataSource;

    /**
     * 关于事务管理，需要返回PlatformTransactionManager的实现
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}
