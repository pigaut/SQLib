package io.github.pigaut.sql;

import com.zaxxer.hikari.*;
import io.github.pigaut.sql.database.*;

import javax.sql.*;
import java.io.*;

public class SQLib {

    private SQLib() {}

    /**
     * Creates a HikariDataSource configured to connect to a SQLite database file.
     *
     * @param h2DatabaseFile The SQLite database file.
     * @return A DataSource configured to connect to the specified SQLite database file.
     */
    public static HikariDataSource createDataSource(File h2DatabaseFile) {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.setConnectionTestQuery("VALUES 1");
        config.addDataSourceProperty("URL", "jdbc:h2:file:" + h2DatabaseFile.getAbsolutePath() + ";TRACE_LEVEL_FILE=0;DB_CLOSE_ON_EXIT=FALSE");
        return new HikariDataSource(config);
    }

    /**
     * Creates a HikariDataSource configured to connect to a database using provided parameters.
     *
     * @param database     The name of the database.
     * @param host         The address of the database server.
     * @param port         The port number of the database server.
     * @param username     The username for authentication.
     * @param password     The password for authentication.
     * @return A DataSource configured to connect to the specified database.
     */
    public static HikariDataSource createDataSource(String database, String host, String port, String username, String password) {
        HikariConfig config = new HikariConfig();
        String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

    /**
     * Creates a SimpleDatabase instance for a database file.
     *
     * @param file The database file.
     * @return A SimpleDatabase instance connected to the specified database file.
     */
    public static Database createDatabase(File file) {
        HikariDataSource dataSource = createDataSource(file);
        return new SimpleDatabase(removeExtension(file.getName()), dataSource);
    }

    /**
     * Creates a SimpleDatabase instance for a database file located in a specific parent directory.
     *
     * @param parent The parent directory.
     * @param name   The name of the database file.
     * @return A SimpleDatabase instance connected to the specified database file.
     */
    public static Database createDatabase(File parent, String name) {
        File dbFile = new File(parent, name);
        HikariDataSource dataSource = createDataSource(dbFile);
        return new SimpleDatabase(name, dataSource);
    }

    /**
     * Creates a SimpleDatabase instance for a database using provided connection parameters.
     *
     * @param database     The name of the database.
     * @param host         The address of the database server.
     * @param port         The port number of the database server.
     * @param username     The username for authentication.
     * @param password     The password for authentication.
     * @return A SimpleDatabase instance connected to the specified database.
     */
    public static Database createDatabase(String database, String host, String port, String username, String password) {
        HikariDataSource dataSource = createDataSource(database, host, port, username, password);
        return new SimpleDatabase(database, dataSource);
    }

    private static String removeExtension(String fileName) {
        if (fileName == null) return null;
        int pos = fileName.lastIndexOf(".");
        return pos == -1 ? fileName : fileName.substring(0, pos);
    }

}
