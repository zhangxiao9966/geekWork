package com.work9.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface SqlService {

    void insert(Connection connection, String sql);
    List<Map<String, Object>> query(Connection connection, String sql);
}
