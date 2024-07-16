package com.github.pigaut.lib.sql;

import com.github.pigaut.lib.sql.database.*;
import com.zaxxer.hikari.*;
import org.bukkit.plugin.*;

import javax.sql.*;
import java.io.*;

public class SQLib {

    private SQLib() {}

    /**
     * Retrieves a DataSource configured to connect to a SQLite database file.
     *
     * @param sqliteDatabaseFile The SQLite database file.
     * @return A DataSource configured to connect to the specified SQLite database file.
     */
    public static DataSource getDataSource(File sqliteDatabaseFile) {
        if (!sqliteDatabaseFile.exists()) {
            try {
                sqliteDatabaseFile.mkdirs();
                sqliteDatabaseFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not retrieve datasource because file creation failed.");
            }
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + sqliteDatabaseFile.getAbsolutePath());
        return new HikariDataSource(config);
    }

    /**
     * Retrieves a DataSource configured to connect to a database using provided parameters.
     *
     * @param databaseName The name of the database.
     * @param address      The address of the database server.
     * @param port         The port number of the database server.
     * @param username     The username for authentication.
     * @param password     The password for authentication.
     * @return A DataSource configured to connect to the specified database.
     */
    public static DataSource getDataSource(String databaseName, String address, String port, String username, String password) {
        HikariConfig config = new HikariConfig();
        String jdbcUrl = String.format("jdbc:%s://%s:%s/%s", databaseName, address, port, databaseName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

    /**
     * Creates a SimpleDatabase instance for a SQLite database found in the plugin folder
     *
     * @param plugin The Plugin instance.
     * @param name   The name of the SQLite database file.
     * @return A SimpleDatabase instance connected to the SQLite database.
     */
    public static Database createDatabase(Plugin plugin, String name) {
        File dbFile = new File(plugin.getDataFolder(), name);
        DataSource dataSource = getDataSource(new File(plugin.getDataFolder(), name));
        return new SimpleDatabase(removeExtension(name), dataSource);
    }

    /**
     * Creates a SimpleDatabase instance for a database file.
     *
     * @param file The database file.
     * @return A SimpleDatabase instance connected to the specified database file.
     */
    public static Database createDatabase(File file) {
        DataSource dataSource = getDataSource(file);
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
        DataSource dataSource = getDataSource(dbFile);
        return new SimpleDatabase(removeExtension(name), dataSource);
    }

    /**
     * Creates a SimpleDatabase instance for a database using provided connection parameters.
     *
     * @param databaseName The name of the database.
     * @param address      The address of the database server.
     * @param port         The port number of the database server.
     * @param username     The username for authentication.
     * @param password     The password for authentication.
     * @return A SimpleDatabase instance connected to the specified database.
     */
    public static Database createDatabase(String databaseName, String address, String port, String username, String password) {
        DataSource dataSource = getDataSource(databaseName, address, port, username, password);
        return new SimpleDatabase(databaseName, dataSource);
    }

    private static String removeExtension(String fileName) {
        if (fileName == null) return null;
        int pos = fileName.lastIndexOf(".");
        if (pos == -1) return fileName;
        return fileName.substring(0, pos);
    }

}
