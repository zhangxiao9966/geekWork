package com.work9.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiao -[Create on 2021/9/23]
 */
@Configuration
public class DynamicDataSourceConfig {
    @Autowired
    Environment env;

    @Bean
    //优先注入
    @Primary
    public ChooseDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);

        DriverManagerDataSource master = new DriverManagerDataSource();
        master.setDriverClassName(env.getProperty("master.datasource.driver-class-name"));
        master.setUrl(env.getProperty("master.datasource.url"));
        master.setUsername(env.getProperty("master.datasource.username"));
        master.setPassword(env.getProperty("master.datasource.password"));


        DriverManagerDataSource slave1 = new DriverManagerDataSource();
        slave1.setDriverClassName(env.getProperty("slave1.datasource.driver-class-name"));
        slave1.setUrl(env.getProperty("slave1.datasource.url"));
        slave1.setUsername(env.getProperty("slave1.datasource.username"));
        slave1.setPassword(env.getProperty("slave1.datasource.password"));


        DriverManagerDataSource slave2 = new DriverManagerDataSource();
        slave2.setDriverClassName(env.getProperty("slave2.datasource.driver-class-name"));
        slave2.setUrl(env.getProperty("slave2.datasource.url"));
        slave2.setUsername(env.getProperty("slave2.datasource.username"));
        slave2.setPassword(env.getProperty("slave2.datasource.password"));

        targetDataSources.put(DataSourceNames.MASTER, master);
        targetDataSources.put(DataSourceNames.SLAVE_ONE, slave1);
        targetDataSources.put(DataSourceNames.SLAVE_TWO, slave2);

        return new ChooseDataSource(master, targetDataSources);
    }
}
