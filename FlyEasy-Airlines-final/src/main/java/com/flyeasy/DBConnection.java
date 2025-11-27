package com.flyeasy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection manages the JDBC connection to the MySQL database.
 * <p>
 * Update USER and PASS to match your local MySQL credentials before running.
 */
public class DBConnection {
    // JDBC URL for local MySQL (database 'flyeasy')
    private static final String URL = "jdbc:mysql://localhost:3306/flyeasy?useSSL=false&allowPublicKeyRetrieval=true";
    // TODO: change these to your own DB user/password
    private static final String USER = "root";
    private static final String PASS = "S02R11K@65here";

    /**
     * Obtain a new JDBC connection.
     *
     * @return a new Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
