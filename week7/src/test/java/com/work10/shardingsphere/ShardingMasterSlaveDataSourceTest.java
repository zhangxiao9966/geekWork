package com.work10.shardingsphere;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingMasterSlaveDataSourceTest {

    @Autowired
    ShardingMasterSlaveDataSource shardingMasterSlaveDataSource;

    @Test
    @Transactional
    public void test() throws SQLException {
        //生成主从数据源
        DataSource dataSource = shardingMasterSlaveDataSource.createDataSource();
        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();

        String sql = "select * from student where id>1";
        statement.execute(sql);
        statement.execute(sql);
        sql = "insert into student values (" + new Random().nextInt() + ",\"张三三\",\"银河系\",\"1231564879\",2);";
        statement.execute(sql);

        sql = "select * from student where id>2";
        statement.execute(sql);

    }
}
