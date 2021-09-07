package com.work10.hikari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

@SpringBootApplication(exclude = {SolrAutoConfiguration.class})
public class HikariApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HikariApplication.class);
        app.run(args);
    }
}