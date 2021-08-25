package com.al.o2o.config.dao;

import com.al.o2o.util.DESUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.config
 * @ClassName:DataSourceConfiguration
 * @Description 配置datasource到ioc容器里面
 * @date2021/8/13 13:15
 */
@Configuration
@MapperScan("com.al.o2o.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * 生成与spring-dao.xml对应的bean dataSource
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name = "dataSource")
    public HikariDataSource createDataSource() throws PropertyVetoException {
        // 生成datasource实例
        HikariDataSource dataSource = new HikariDataSource();
        // 数据源的相关配置
       dataSource.setDriverClassName(jdbcDriver);
       dataSource.setJdbcUrl(jdbcUrl);
       dataSource.setUsername(DESUtil.getDecryptString(jdbcUsername));
       dataSource.setPassword(DESUtil.getDecryptString(jdbcPassword));
       // 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQ
       dataSource.setConnectionTimeout(30000);
       // 最小连接数
       dataSource.setMinimumIdle(5);
       // 最大连接数
       dataSource.setMaximumPoolSize(20);
       // 自动提交
       dataSource.setAutoCommit(true);
       // 连接超时的最大时长（毫秒），超时则被释放（retired），默认:10分钟
       dataSource.setIdleTimeout(600000);
       //连接池名字
       dataSource.setPoolName("DateSourceHikariCP");
       //连接的生命时长（毫秒），超时而且没被使用则被释放（retired），默认:30分钟
       dataSource.setMaxLifetime(1800000);
       dataSource.setConnectionTestQuery("SELECT 1");
       return dataSource;
    }
}
