package com.online.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DataSource {
    private static final Logger LOG = Logger.getLogger(DataSource.class.getName());
    private String url;
    private String driverClass;
    private String userName;
    private String password;

    public Connection getConnection() {
        try {
            Class.forName(getDriverClass());
            LOG.info("Connection opened: ");
            return DriverManager.getConnection(getUrl(), getUserName(), getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
