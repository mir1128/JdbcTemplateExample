package com.loobo;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DevelopmentProfileConfig {

    @Autowired
    private Environment env;

    @Bean
    public CDPlayer cdPlayer() {
        return new DevelopCDPlayer();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(env.getProperty("jdbc.driverClassName"));

        datasource.setUrl(env.getProperty("jdbc.url"));
        datasource.setUsername(env.getProperty("jdbc.username"));
        datasource.setPassword(env.getProperty("jdbc.password"));
        return datasource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}



