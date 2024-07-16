package com.github.pigaut.lib.sql;

/**
 * The DataTable interface provides methods to interact with a specific table
 * in a database, enabling operations such as table management, data manipulation,
 * and querying.
 */
public interface DataTable {

    /**
     * Gets the database this table belongs to.
     *
     * @return the Database object.
     */
    Database getDatabase();

    /**
     * Gets the name of the table.
     *
     * @return the name of the table.
     */
    String getName();

    /**
     * Creates the table with the specified columns.
     *
     * @param columns Column definitions.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.create("id INT PRIMARY KEY", "name VARCHAR(255)");
     * // SQL: "CREATE TABLE table_name (id INT PRIMARY KEY, name VARCHAR(255));"
     * }
     * </pre>
     */
    void create(String... columns);

    /**
     * Creates the table if it does not already exist, with the specified columns.
     *
     * @param columns Column definitions.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.createIfNotExists("id INT PRIMARY KEY", "name VARCHAR(255)");
     * // SQL: "CREATE TABLE IF NOT EXISTS table_name (id INT PRIMARY KEY, name VARCHAR(255));"
     * }
     * </pre>
     */
    void createIfNotExists(String... columns);

    /**
     * Drops the table.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.drop();
     * // SQL: "DROP TABLE table_name;"
     * }
     * </pre>
     */
    void drop();

    /**
     * Renames the table to the specified new name.
     *
     * @param newName the new name of the table.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.rename("new_table_name");
     * // SQL: "ALTER TABLE table_name RENAME TO new_table_name;"
     * }
     * </pre>
     */
    void rename(String newName);

    /**
     * Clears all data from the table.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.clear();
     * // SQL: "DELETE FROM table_name;"
     * }
     * </pre>
     */
    void clear();

    /**
     * Adds a new column to the table.
     *
     * @param columnDefinition the column definition.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.addColumn("age INT");
     * // SQL: "ALTER TABLE table_name ADD age INT;"
     * }
     * </pre>
     */
    void addColumn(String columnDefinition);

    /**
     * Drops a column from the table.
     *
     * @param column the name of the column to drop.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.dropColumn("age");
     * // SQL: "ALTER TABLE table_name DROP age;"
     * }
     * </pre>
     */
    void dropColumn(String column);

    /**
     * Renames a column in the table.
     *
     * @param oldName the current name of the column.
     * @param newName the new name of the column.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.renameColumn("name", "full_name");
     * // SQL: "ALTER TABLE table_name RENAME COLUMN name TO full_name;"
     * }
     * </pre>
     */
    void renameColumn(String oldName, String newName);

    /**
     * Creates an insert DatabaseStatement with the specified clause.
     *
     * @param clause the clause for the insert statement.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insert("VALUES (1, 'John')");
     * // SQL: "INSERT INTO table_name VALUES (1, 'John');"
     * }
     * </pre>
     */
    DatabaseStatement insert(String clause);

    /**
     * Creates an insert DatabaseStatement with the specified columns.
     *
     * @param columns the columns to insert into.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insertInto("id", "name");
     * // SQL: "INSERT INTO table_name (id, name) VALUES (?, ?);"
     * }
     * </pre>
     */
    DatabaseStatement insertInto(String... columns);

    /**
     * Creates an insert DatabaseStatement with the specified values.
     *
     * @param values the values to insert.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insertAll("1", "'John'");
     * // SQL: "INSERT INTO table_name VALUES (1, 'John');"
     * }
     * </pre>
     */
    DatabaseStatement insertAll(String... values);

    /**
     * Creates an insert DatabaseStatement with a specified number of parameter placeholders.
     *
     * @param parameters the number of parameter placeholders.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insertAll(2);
     * // SQL: "INSERT INTO table_name VALUES (?, ?);"
     * }
     * </pre>
     */
    DatabaseStatement insertAll(int parameters);

    /**
     * Creates an update DatabaseStatement with the specified clause and modifications.
     *
     * @param clause       the clause for the update statement.
     * @param modifications the modifications to be made.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.set("WHERE id = 1", "name = 'John'");
     * // SQL: "UPDATE table_name SET name = 'John' WHERE id = 1;"
     * }
     * </pre>
     */
    DatabaseStatement set(String clause, String... modifications);

    /**
     * Creates an update DatabaseStatement with the specified clause.
     *
     * @param clause the clause for the update statement.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.set("SET name = 'John' WHERE id = 1");
     * // SQL: "UPDATE table_name SET name = 'John' WHERE id = 1;"
     * }
     * </pre>
     */
    DatabaseStatement set(String clause);

    /**
     * Creates a delete DatabaseStatement with the specified clause.
     *
     * @param clause the clause for the delete statement.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.delete("WHERE id = 1");
     * // SQL: "DELETE FROM table_name WHERE id = 1;"
     * }
     * </pre>
     */
    DatabaseStatement delete(String clause);

    /**
     * Creates a select DatabaseStatement with the specified clause.
     *
     * @param clause the clause for the select statement.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.select("WHERE id = 1");
     * // SQL: "SELECT * FROM table_name WHERE id = 1;"
     * }
     * </pre>
     */
    DatabaseStatement select(String clause);

    /**
     * Creates a select DatabaseStatement with the specified columns and clause.
     *
     * @param clause  the clause for the select statement.
     * @param columns the columns to select.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.select("WHERE id = 1", "id", "name");
     * // SQL: "SELECT id, name FROM table_name WHERE id = 1;"
     * }
     * </pre>
     */
    DatabaseStatement select(String clause, String... columns);

    /**
     * Creates a select DatabaseStatement to retrieve all rows from the table.
     *
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.selectAll();
     * // SQL: "SELECT * FROM table_name;"
     * }
     * </pre>
     */
    DatabaseStatement selectAll();

    /**
     * Creates a select DatabaseStatement to retrieve all rows from the table with specified columns.
     *
     * @param columns the columns to select.
     * @return a new DatabaseStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.selectAll("id", "name");
     * // SQL: "SELECT id, name FROM table_name;"
     * }
     * </pre>
     */
    DatabaseStatement selectAll(String... columns);

}
