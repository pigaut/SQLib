package com.github.pigaut.lib.sql;

import com.github.pigaut.lib.sql.database.statement.*;

import javax.sql.*;

/**
 * The Database interface represents a database connection and provides methods
 * to interact with the database, such as executing SQL queries and updates,
 * accessing data sources, and retrieving specific DataTable instances.
 */
public interface Database {

    /**
     * Retrieves the name of the database.
     *
     * @return The name of the database.
     */
    String getName();

    /**
     * Retrieves the DataSource associated with this database connection.
     *
     * @return The DataSource instance used for database connections.
     */
    DataSource getDataSource();

    /**
     * Retrieves a DataTable instance associated with the specified table name.
     *
     * @param name The name of the table.
     * @return A DataTable instance representing the specified table.
     */
    DataTable tableOf(String name);

    /**
     * Executes a SQL statement against the database.
     *
     * @param sql The SQL statement to execute.
     * @return true if the execution was successful; false otherwise.
     */
    boolean execute(String sql);

    /**
     * Executes an SQL statement that may modify the database (such as INSERT, UPDATE, DELETE).
     *
     * @param sql The SQL statement to execute.
     * @return The number of rows affected by the statement.
     */
    int executeUpdate(String sql);

    /**
     * Executes a large update SQL statement that may modify the database.
     *
     * @param sql The SQL statement to execute.
     * @return The number of rows affected by the statement as a long value.
     */
    long executeLargeUpdate(String sql);

    /**
     * Executes an SQL query and processes the results using a QueryReader.
     *
     * @param sql    The SQL query to execute.
     * @param reader The QueryReader implementation to process the query results.
     */
    void executeQuery(String sql, QueryReader reader);

}
