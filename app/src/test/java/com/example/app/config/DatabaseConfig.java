//package com.example.app.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//    @Value("Recipes")
//    private String databaseName;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    public DataSource dataSource() {
//        PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
//                .withDatabaseName(databaseName)
//                .withUsername(username)
//                .withPassword(password);
//
//        postgreSQLContainer.start();
//
//        HikariDataSource dataSource = new HikariDataSource();
//
//        dataSource.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
//        dataSource.setUsername(postgreSQLContainer.getUsername());
//        dataSource.setPassword(postgreSQLContainer.getPassword());
//
//        return dataSource;
//    }
//
//}
