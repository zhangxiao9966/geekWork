package com.work9.service.impl;


import com.work9.annotation.ReadAction;
import com.work9.service.SqlService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("SqlService")
public class SqlServiceImpl implements SqlService {

    @SneakyThrows
    @Override
    public void insert(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            System.out.println(connection.getMetaData().getURL());
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    @ReadAction
    public List<Map<String, Object>> query(Connection connection, String sql) {


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            System.out.println(connection.getMetaData().getURL());

            List<Map<String, Object>> entities = new ArrayList<>();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("id", resultSet.getInt("id"));
                data.put("address", resultSet.getString("address"));
                data.put("phoneNumber", resultSet.getString("phoneNumber"));
                data.put("name", resultSet.getString("name"));
                entities.add(data);
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
}
