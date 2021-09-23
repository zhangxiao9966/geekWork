package com.work9;


import com.WorkApplication;
import com.work9.datasource.ChooseDataSource;
import com.work9.service.impl.SqlServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WorkApplication.class})
public class SqlServiceImplTest {

    @Autowired
    ChooseDataSource dataSource;
    @Autowired
    SqlServiceImpl sqlService;

    @Test
    @Transactional
    public void testSql() throws SQLException {

        String sql = "insert into student values (" + new Random().nextInt() + ",\"张三三\",\"银河系\",\"1231564879\",2);";
        // 使用主库
        sqlService.insert(dataSource.getConnection(), sql);

        sql = "select * from student;";
        for (int i = 0; i < 7; i++) {
            //2个从负载均衡
            List<Map<String, Object>> mapList = sqlService.query(dataSource.getConnection(), sql);
            for (Map map : mapList) {
                System.out.println(map.toString());
            }
        }
    }
}
