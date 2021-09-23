package com.work2;

import java.sql.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MysqlJdbc {

    private Connection connection = null;
    private Connection connection2 = null;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=UTF-8&useSSL=false&rewriteBatchedStatements=true", "root", "bleach");
//            connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/visits", "root", "bleach");
            if (connection != null) {
                System.out.println("connection success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean insert1(int times, CountDownLatch latch, Random random) throws SQLException {
        MysqlJdbc mysqlJdbc = new MysqlJdbc();
        PreparedStatement statement = null;
        try {
            mysqlJdbc.createConnection();
            statement = mysqlJdbc.connection.prepareStatement("insert into test2 values (?,?)");
            for (int i = 0; i < times; i++) {
                statement.setObject(1, random.nextInt());
                statement.setObject(2, random.nextInt());
                statement.addBatch();
            }
            statement.executeBatch();
            System.out.println("insert success");
            latch.countDown();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            mysqlJdbc.close();
        }
        return false;
    }

    /**
     * 拼一个长sql，一堆values
     */
    public static boolean insert2(int times, CountDownLatch latch, Random random) throws SQLException {
        MysqlJdbc mysqlJdbc = new MysqlJdbc();
        Statement statement = null;
        try {

            mysqlJdbc.createConnection();
            statement = mysqlJdbc.connection.createStatement();
            String sqlBase = "insert into test2 values ";
            String sql = "";
            int loopTimes = times / 500;
            int surplus = times % 500;
            for (int i = 0; i < loopTimes; i++) {
                sql = sqlBase;
                for (int j = 0; j < 500; j++) {
                    sql += "(" + random.nextInt() + "," + random.nextInt() + ")";
                    if (j != 499) {
                        sql += ",";
                    }
                }
                statement.execute(sql);
            }

            if (surplus != 0) {
                sql += ",";
            }


            for (int i = 0; i < surplus; i++) {
                sql += "(" + random.nextInt() + "," + random.nextInt() + ")";
                if (i != surplus - 1) {
                    sql += ",";
                }
            }
            statement.execute(sql);

            System.out.println("insert success");
            latch.countDown();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            mysqlJdbc.close();
        }
        return false;
    }


    private void close() throws SQLException {
        connection.close();
        System.out.println("mysql connection close");
    }

    public static void main(String[] args) {
        try {

//            CountDownLatch latch = new CountDownLatch(1);
//            long start = System.currentTimeMillis();
//            //--------------------------方法1 addBatch 6846毫秒------------------------------------
////            MysqlJdbc.insert1(1000000, latch, new Random());
//            //--------------------------方法2 500个一组 insert into table values(),().() 13728毫秒------------------------------------
//            MysqlJdbc.insert2(1000000, latch, new Random());
//            long finish = System.currentTimeMillis();
//            latch.await();
//            System.out.println("totalTime:" + (finish - start));

//--------------------------方法2 8075毫秒------------------------------------
            CountDownLatch latch = new CountDownLatch(2);
            long start = System.currentTimeMillis();
            new Thread(() -> {
                try {
                    MysqlJdbc.insert1(500000, latch, new Random());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    MysqlJdbc.insert1(500000, latch, new Random());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();
//
//            MysqlJdbc.insert1(500000, latch, new Random());
            latch.await();
            long finish = System.currentTimeMillis();
            System.out.println("totalTime:" + (finish - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
