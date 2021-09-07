package com.work10.jdbc;

import com.work10.hikari.Student;

import java.sql.*;
import java.util.*;

/**
 * insert query用的preparedStatement
 * update用的原生接口
 */
public class MysqlJdbc {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement;

    //连接
    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "bleach");
            if (connection != null) {
                System.out.println("connection success");
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //用prepareStatement方式
    public boolean insert(String table, String column, List<Object> values) {
        try {
            String insertTemplate = getInsertSql(table, column, values.size());
            preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i = 1; i < values.size() + 1; i++) {
                preparedStatement.setObject(i, values.get(i - 1));
            }
            System.out.println(preparedStatement.toString());

            preparedStatement.execute();
            System.out.println("insert success");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getInsertSql(String table, String column, int valueAmount) {
        StringBuilder valueStr = new StringBuilder();
        for (int i = 0; i < valueAmount - 1; i++) {
            valueStr.append(",?");
        }
        return "insert into " + table + " " + column + " values (?" + valueStr.toString() + ")";
    }

    //用原生的update
    private void updateStudentByName(String name, String column, String value) throws SQLException {
        String sql = "update student set " + column + "= \'" + value + "\' where name =\'" + name + "\'";
        statement.executeUpdate(sql);
    }

    public List<Map<String, Object>> query(String table, Map<String, Object> values, String condition) {
        try {
            String sqlTemplate = buildQuerySqlTemplate(table, values, condition);

            preparedStatement = connection.prepareStatement(sqlTemplate);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                for (String key : values.keySet()) {
                    values.put(key, resultSet.getObject(key));
                }
                list.add(new HashMap<>(values));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String buildQuerySqlTemplate(String table, Map<String, Object> values, String condition) {
        String sqlTemplate = "select " + values.keySet().toString().substring(1,
                values.keySet().toString().length() - 1) + " from " + table;
        if (condition != null) {
            sqlTemplate += " where " + condition;
        }
        return sqlTemplate;
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("mysql connection close");
    }

    public static void main(String[] args) {
        Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("id", 0);
        valuesMap.put("name", "张三");
        valuesMap.put("address", "北京市");
        valuesMap.put("phoneNumber", "13552222222");
        valuesMap.put("klass", "1");
        System.out.println(valuesMap.keySet().toString().substring(1, valuesMap.keySet().toString().length() - 1));

        try {
            MysqlJdbc mysqlJdbc = new MysqlJdbc();
            mysqlJdbc.createConnection();

            String table = "student";
            String columns = "(name, address, phoneNumber, klass)";
            List<Object> values = Arrays.asList("张三", "北京市", "13552222222", 1);

            mysqlJdbc.insert(table, columns, values);

            List<Map<String, Object>> results = mysqlJdbc.query(table, valuesMap, null);
            Student student = null;
            for (Map<String, Object> map : results) {
                System.out.println("改之前");
                student = new Student(map);
                System.out.println(student.getInfo());
            }

            if (student != null) {
                if ("北京市".equals(student.getAddress())) {
                    mysqlJdbc.updateStudentByName("张三", "address", "上海");
                } else {
                    mysqlJdbc.updateStudentByName("张三", "address", "北京市");
                }
            }

            results = mysqlJdbc.query(table, valuesMap, null);
            for (Map<String, Object> map : results) {
                System.out.println("改之后");
                student = new Student(map);
                System.out.println(student.getInfo());
            }

            mysqlJdbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
