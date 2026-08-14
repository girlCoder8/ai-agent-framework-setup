package com.acme.framework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Thin JDBC wrapper for Oracle DB connectivity (ojdbc11), mirroring the
 * WebDriverFactory / MobileDriverFactory pattern already used elsewhere
 * in this framework.
 * <p>
 * Required environment variables (set as CI secrets):
 *   DB_URL e.g., jdbc:oracle:thin:@host:1521/servicename
 *   DB_USER
 *   DB_PASSWORD
 * <p>
 * Connection is opened explicitly via connect() and must be closed via
 * close() by the caller (step defs handle this in their @Then/cleanup).
 */
public class DatabaseClient {

    private Connection connection;

    public void connect() {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException(
                    "DB_URL, DB_USER, and DB_PASSWORD environment variables must all be set");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database: " + e.getMessage(), e);
        }
    }

    /**
     * Executes a read-only query and returns true if it produced at
     * least one row. Intended for lightweight connectivity/validation
     * checks, not for retrieving actual result data.
     */
    public boolean executeValidationQuery(String query) {
        if (connection == null) {
            throw new IllegalStateException("connect() must be called before executing a query");
        }
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Validation query failed: " + e.getMessage(), e);
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // non-fatal cleanup failure
            } finally {
                connection = null;
            }
        }
    }
}
