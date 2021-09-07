package com.work10.hikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HikariRunner implements CommandLineRunner {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        String sql = "select * from student";
        List<Student> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Student.class));
        users.stream().forEach(user -> System.out.println(user.getId() + " " + user.getAddress() + " " + user.getKlass() + " " + user.getPhoneNumber()));
    }
}
