package com.loobo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(DevelopmentProfileConfig.class);

        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

        List<Map<String, Object>> results = jdbcTemplate.queryForList("select * from employee");

        for (Map<String, Object> result : results) {
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                System.out.println("key: " + entry.getKey() + "   value: " + entry.getValue());
            }
        }
    }
}

