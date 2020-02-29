package com.homework.goit.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {
    private final HikariDataSource ds;

    //todo: has to be static??
    public DatabaseConnector() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5547/go_it_homework");
        config.setUsername("postgres");
        config.setPassword("Sam@64hd!+4");
        this.ds = new HikariDataSource(config);
        ds.setMaximumPoolSize(5);
    }

    public Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("No connection to pool ", e);
        }
    }
}
