package com.work10.shardingsphere;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Component
public class ShardingMasterSlaveDataSource {
    @Autowired
    private Environment environment;

    DataSource createDataSource() throws SQLException {
        // 根据名字获取，根据前缀加库名，统一获取主从配置
        String[] dbNames = Objects.requireNonNull(environment.getProperty("sharding.jdbc.datasource.names")).split(",");
        System.out.println("dbNames : " + Arrays.toString(dbNames));

        // 第一个为主，其余的为从
        ReplicaQueryDataSourceRuleConfiguration dataSourceRule =
                new ReplicaQueryDataSourceRuleConfiguration
                        ("mast-slave", dbNames[0], Arrays.asList(Arrays.copyOfRange(dbNames, 1, dbNames.length)), "ROUND_ROBIN");

        Map<String, ShardingSphereAlgorithmConfiguration> shardingAlgorithms = new HashMap<>();
        shardingAlgorithms.put("", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration queryRule = new ReplicaQueryRuleConfiguration(Collections.singleton(dataSourceRule), shardingAlgorithms);

        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        return ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(dbNames), Collections.singleton(queryRule), properties);
    }

    /**
     * 返回DataSource列表
     */
    private Map<String, DataSource> createDataSourceMap(String[] dbs) {
        Map<String, DataSource> result = new HashMap<>(dbs.length);
        for (String db : dbs) {
            result.put(db, createDataSource("sharding.jdbc.datasource.ds-" + db));
            System.out.println("create dataSource : " + db);
        }
        return result;
    }

    private DataSource createDataSource(String prefix) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty(prefix + ".driver-class-name"));
        dataSource.setJdbcUrl(environment.getProperty(prefix + ".url"));
        dataSource.setUsername(environment.getProperty(prefix + ".username"));
        dataSource.setPassword(environment.getProperty(prefix + ".password"));
        return dataSource;
    }
}
