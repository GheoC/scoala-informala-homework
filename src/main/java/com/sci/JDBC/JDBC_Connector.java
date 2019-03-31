package com.sci.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Connector class to the database "school"
 *
 */
public class JDBC_Connector {

    public Connection getConnection() throws SQLException {

        DriverManager.setLoginTimeout(60);
        String url = new StringBuilder()
                .append("jdbc:")
                .append("mysql")
                .append("://")
                .append("localhost:")
                .append("3306")
                .append("/")
                .append("school")
                .append("?user=")
                .append("root")
                .append("&password=")
                .append("password").toString();

        return DriverManager.getConnection(url);
    }
}
