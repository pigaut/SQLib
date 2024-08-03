package io.github.pigaut.lib.sql;

import io.github.pigaut.lib.sql.database.statement.*;

public interface DatabaseStatement {

    /**
     * Replaces a placeholder in the SQL statement with a specific value.
     *
     * @param placeholder the placeholder to replace
     * @param value the value to replace with
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement replace(String placeholder, String value);

    /**
     * Adds an option to the statement.
     *
     * @param option the statement option to add
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement addOption(StatementOption option);

    /**
     * Removes all options for this statement.
     */
    void clearOptions();

    /**
     * Adds a batch operation to the statement.
     *
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement addBatch();

    /**
     * Sets a parameter in the statement as NULL.
     *
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withNullParameter();

    /**
     * Sets a parameter in the statement with a specified object.
     *
     * @param object the object to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(Object object);

    /**
     * Sets a parameter in the statement with a specified object and SQL type.
     *
     * @param object the object to set as the parameter
     * @param sqlType the SQL type of the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(Object object, int sqlType);

    /**
     * Sets a parameter in the statement as a boolean value.
     *
     * @param value the boolean value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(boolean value);

    /**
     * Sets a parameter in the statement as a string value.
     *
     * @param value the string value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(String value);

    /**
     * Sets a parameter in the statement as an integer value.
     *
     * @param value the integer value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(int value);

    /**
     * Sets a parameter in the statement as a long value.
     *
     * @param value the long value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(long value);

    /**
     * Sets a parameter in the statement as a short value.
     *
     * @param value the short value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(short value);

    /**
     * Sets a parameter in the statement as a byte value.
     *
     * @param value the byte value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(byte value);

    /**
     * Sets a parameter in the statement as a float value.
     *
     * @param value the float value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(float value);

    /**
     * Sets a parameter in the statement as a double value.
     *
     * @param value the double value to set as the parameter
     * @return the updated SimpleDatabaseStatement object
     */
    DatabaseStatement withParameter(double value);

    /**
     * Executes the SQL statement.
     *
     * @return true if the execution was successful, false otherwise
     */
    boolean execute();

    /**
     * Executes an update SQL statement.
     *
     * @return the number of rows affected
     */
    int executeUpdate();

    /**
     * Executes a large update SQL statement.
     *
     * @return the number of rows affected
     */
    long executeLargeUpdate();

    /**
     * Executes a query SQL statement and processes the results with a QueryReader.
     *
     * @param reader the QueryReader to process the query results
     */
    void executeQuery(QueryReader reader);

    /**
     * Fetches the first row from the result set.
     *
     * @param reader the QueryReader to process the first row
     */
    void fetchRow(QueryReader reader);

    /**
     * Fetches all rows from the result set.
     *
     * @param reader the QueryReader to process all rows
     */
    void fetchAllRows(QueryReader reader);

    /**
     * Executes a batch of SQL statements.
     *
     * @return an array of update counts indicating the success of each command
     */
    int[] executeBatch();

    /**
     * Executes a large batch of SQL statements.
     *
     * @return an array of update counts indicating the success of each command
     */
    long[] executeLargeBatch();

}
