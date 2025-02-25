package io.github.pigaut.sql;

import io.github.pigaut.sql.database.statement.*;

import javax.sql.*;

public interface Database {

    /**
     * Retrieves the name of the database.
     *
     * @return The name of the database.
     */
    String getName();

    /**
     * Retrieves the DataSource associated with this database.
     *
     * @return The DataSource instance used for database connections.
     */
    DataSource getDataSource();

    /**
     * Creates a DataTable with the specified table name (may not exist in the database).
     *
     * @param name The name of the table.
     * @return A DataTable instance representing the specified table.
     */
    DataTable tableOf(String name);

    /**
     * Creates a statement for this database with the given sql
     *
     * @param sql the sql statement
     * @return a new DatabaseStatement
     */
    DatabaseStatement createStatement(String sql);

    /**
     * Executes a SQL statement against the database.
     *
     * @param sql The SQL statement to execute.
     */
    void execute(String sql);

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
     * @return The number of rows affected by the statement.
     */
    long executeLargeUpdate(String sql);

    /**
     * Executes an SQL query and processes the results using a QueryReader.
     *
     * @param sql    The SQL query to execute.
     * @param reader The QueryReader implementation to process the query results.
     */
    void executeQuery(String sql, QueryReader reader);

    void closeConnection();

}
