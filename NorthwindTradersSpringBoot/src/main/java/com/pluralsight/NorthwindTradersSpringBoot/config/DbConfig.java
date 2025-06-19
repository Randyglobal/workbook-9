package com.pluralsight.NorthwindTradersSpringBoot.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    private final BasicDataSource basicDataSource;

    public DbConfig(@Value("${db.url}") String url,
                    @Value("${db.username}") String username,
                    @Value("${db.password}") String password
    ) {
        this.basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    @Bean
    public DataSource dataSource(){
        return basicDataSource;
    }

}
