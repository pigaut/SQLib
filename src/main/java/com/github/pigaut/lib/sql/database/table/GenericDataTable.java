package com.github.pigaut.lib.sql.database.table;

import com.github.pigaut.lib.sql.*;
import com.github.pigaut.lib.sql.database.statement.*;

import java.util.*;

/**
 * The DataTable class provides methods to interact with a specific table
 * in a database, enabling operations such as table management, data manipulation,
 * and querying.
 */
public class GenericDataTable implements DataTable {

    private final Database database;
    private String tableName;

    /**
     * Constructs a DataTable object.
     *
     * @param database  the Database object to interact with.
     * @param tableName the name of the table to manage.
     */
    public GenericDataTable(Database database, String tableName) {
        this.database = database;
        this.tableName = tableName;
    }

    /**
     * Gets the associated Database object.
     *
     * @return the Database object.
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Gets the name of the table.
     *
     * @return the name of the table.
     */
    public String getName() {
        return tableName;
    }

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
    public void create(String... columns) {
        String columnsDef = String.join(", ", columns);
        String createTableSQL = String.format(StatementTemplates.CREATE_TABLE, "", tableName, columnsDef);
        database.execute(createTableSQL);
    }

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
    public void createIfNotExists(String... columns) {
        String columnsDef = String.join(", ", columns);
        String createTableSQL = String.format(StatementTemplates.CREATE_TABLE_IF_NOT_EXISTS, tableName, columnsDef);
        database.execute(createTableSQL);
    }

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
    public void drop() {
        String dropTableSQL = String.format(StatementTemplates.DROP_TABLE, tableName);
        database.execute(dropTableSQL);
    }

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
    public void rename(String newName) {
        String renameTableSQL = String.format(StatementTemplates.RENAME_TABLE, tableName, newName);
        database.execute(renameTableSQL);
        tableName = newName;
    }

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
    public void clear() {
        String deleteFromSQL = String.format(StatementTemplates.DELETE_ALL, tableName);
        database.executeUpdate(deleteFromSQL);
    }

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
    public void addColumn(String columnDefinition) {
        String addColumnStmt = String.format(StatementTemplates.ADD_COLUMN, tableName, columnDefinition);
        database.execute(addColumnStmt);
    }

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
    public void dropColumn(String column) {
        String dropColumnStmt = String.format(StatementTemplates.DROP_COLUMN, tableName, column);
        database.execute(dropColumnStmt);
    }

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
    public void renameColumn(String oldName, String newName) {
        String renameColumnStmt = String.format(StatementTemplates.RENAME_COLUMN, tableName, oldName, newName);
        database.execute(renameColumnStmt);
    }

    /**
     * Creates an insert TableStatement with the specified clause.
     *
     * @param clause the clause for the insert statement.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insert("VALUES (1, 'John')");
     * // SQL: "INSERT INTO table_name VALUES (1, 'John');"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement insert(String clause) {
        String sql = String.format(StatementTemplates.INSERT, tableName, clause);
        return new SimpleDatabaseStatement(database, sql);
    }

    /**
     * Creates an insert TableStatement with the specified columns.
     *
     * @param columns the columns to insert into.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insertInto("id", "name");
     * // SQL: "INSERT INTO table_name (id, name) VALUES (?, ?);"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement insertInto(String... columns) {
        String columnsDef = String.join(", ", columns);
        String values = String.join(", ", Collections.nCopies(columns.length, "?"));
        String insertIntoSQL = String.format(StatementTemplates.INSERT_WITH_COLUMNS, tableName, columnsDef, values);
        return new SimpleDatabaseStatement(database, insertIntoSQL);
    }

    /**
     * Creates an insert TableStatement with the specified values.
     *
     * @param values the values to insert.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insertAll("1", "'John'");
     * // SQL: "INSERT INTO table_name VALUES (1, 'John');"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement insertAll(String... values) {
        String valuesDef = String.join(", ", values);
        String insertIntoSQL = String.format(StatementTemplates.INSERT_ALL, tableName, valuesDef);
        return new SimpleDatabaseStatement(database, insertIntoSQL);
    }

    /**
     * Creates an insert TableStatement with a specified number of parameter placeholders.
     *
     * @param parameters the number of parameter placeholders.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.insertAll(2);
     * // SQL: "INSERT INTO table_name VALUES (?, ?);"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement insertAll(int parameters) {
        String values = String.join(", ", Collections.nCopies(parameters, "?"));
        String insertIntoSQL = String.format(StatementTemplates.INSERT_ALL, tableName, values);
        return new SimpleDatabaseStatement(database, insertIntoSQL);
    }

    /**
     * Creates an update TableStatement with the specified clause and modifications.
     *
     * @param clause       the clause for the update statement.
     * @param modifications the modifications to be made.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.set("WHERE id = 1", "name = 'John'");
     * // SQL: "UPDATE table_name SET name = 'John' WHERE id = 1;"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement set(String clause, String... modifications) {
        String modificationsDef = String.join(", ", modifications);
        String updateSQL = String.format(StatementTemplates.SET_EACH, tableName, modificationsDef, clause);
        return new SimpleDatabaseStatement(database, updateSQL);
    }

    /**
     * Creates an update TableStatement with the specified clause.
     *
     * @param clause the clause for the update statement.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.set("SET name = 'John' WHERE id = 1");
     * // SQL: "UPDATE table_name SET name = 'John' WHERE id = 1;"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement set(String clause) {
        String updateSQL = String.format(StatementTemplates.SET, tableName, clause);
        return new SimpleDatabaseStatement(database, updateSQL);
    }

    /**
     * Creates a delete TableStatement with the specified clause.
     *
     * @param clause the clause for the delete statement.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.delete("WHERE id = 1");
     * // SQL: "DELETE FROM table_name WHERE id = 1;"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement delete(String clause) {
        String deleteFromSQL = String.format(StatementTemplates.DELETE, tableName, clause);
        return new SimpleDatabaseStatement(database, deleteFromSQL);
    }

    /**
     * Creates a select TableStatement with the specified clause.
     *
     * @param clause the clause for the select statement.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.select("WHERE id = 1");
     * // SQL: "SELECT * FROM table_name WHERE id = 1;"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement select(String clause) {
        String selectSQL = String.format(StatementTemplates.SELECT_ALL, tableName, clause);
        return new SimpleDatabaseStatement(database, selectSQL);
    }

    /**
     * Creates a select TableStatement with the specified columns and clause.
     *
     * @param clause  the clause for the select statement.
     * @param columns the columns to select.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.select("WHERE id = 1", "id", "name");
     * // SQL: "SELECT id, name FROM table_name WHERE id = 1;"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement select(String clause, String... columns) {
        String columnsDef = String.join(", ", columns);
        String selectSQL = String.format(StatementTemplates.SELECT, columnsDef, tableName, clause);
        return new SimpleDatabaseStatement(database, selectSQL);
    }

    /**
     * Creates a select TableStatement to retrieve all rows from the table.
     *
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.selectAll();
     * // SQL: "SELECT * FROM table_name;"
     * }
     * </pre>
     */
    public SimpleDatabaseStatement selectAll() {
        String selectSQL = String.format(StatementTemplates.SELECT_ALL, tableName, "");
        return new SimpleDatabaseStatement(database, selectSQL);
    }

    /**
     * Creates a select TableStatement to retrieve all rows from the table with specified columns.
     *
     * @param columns the columns to select.
     * @return a new TableStatement object.
     *
     * Example:
     * <pre>
     * {@code
     * dataTable.selectAll("id", "name");
     * // SQL: "SELECT id, name FROM table_name;"
     * }
     * </pre>
     */
    public DatabaseStatement selectAll(String... columns) {
        String columnsDef = String.join(", ", columns);
        String selectSQL = String.format(StatementTemplates.SELECT, columnsDef, tableName, "");
        return new SimpleDatabaseStatement(database, selectSQL);
    }

}