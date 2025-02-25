package io.github.pigaut.sql.database.table;

import io.github.pigaut.sql.*;
import io.github.pigaut.sql.database.statement.*;

import java.util.*;

public class GenericDataTable implements DataTable {

    private String tableName;
    private final Database database;

    public GenericDataTable(String tableName, Database database) {
        this.tableName = tableName;
        this.database = database;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public String getName() {
        return tableName;
    }

    @Override
    public void create(String... columns) {
        String columnsDef = String.join(", ", columns);
        String createTableSQL = String.format(StatementTemplate.CREATE_TABLE, "", tableName, columnsDef);
        database.execute(createTableSQL);
    }

    @Override
    public void createIfNotExists(String... columns) {
        String columnsDef = String.join(", ", columns);
        String createTableSQL = String.format(StatementTemplate.CREATE_TABLE_IF_NOT_EXISTS, tableName, columnsDef);
        database.execute(createTableSQL);
    }

    @Override
    public void drop() {
        String dropTableSQL = String.format(StatementTemplate.DROP_TABLE, tableName);
        database.execute(dropTableSQL);
    }

    @Override
    public void rename(String newName) {
        String renameTableSQL = String.format(StatementTemplate.RENAME_TABLE, tableName, newName);
        database.execute(renameTableSQL);
        tableName = newName;
    }

    @Override
    public void clear() {
        String deleteFromSQL = String.format(StatementTemplate.DELETE_ALL, tableName);
        database.executeUpdate(deleteFromSQL);
    }

    @Override
    public void addColumn(String columnDefinition) {
        String addColumnStmt = String.format(StatementTemplate.ADD_COLUMN, tableName, columnDefinition);
        database.execute(addColumnStmt);
    }

    @Override
    public void renameColumn(String oldName, String newName) {
        String renameColumnStmt = String.format(StatementTemplate.RENAME_COLUMN, tableName, oldName, newName);
        database.execute(renameColumnStmt);
    }

    @Override
    public DatabaseStatement insert(String clause) {
        String sql = String.format(StatementTemplate.INSERT, tableName, clause);
        return new GenericDatabaseStatement(database, sql);
    }

    @Override
    public DatabaseStatement insertInto(String... columns) {
        String columnsDef = String.join(", ", columns);
        String values = String.join(", ", Collections.nCopies(columns.length, "?"));
        String insertIntoSQL = String.format(StatementTemplate.INSERT_COLUMNS, tableName, columnsDef, values);
        return new GenericDatabaseStatement(database, insertIntoSQL);
    }

    @Override
    public DatabaseStatement insertAll(String... values) {
        String valuesDef = String.join(", ", values);
        String insertIntoSQL = String.format(StatementTemplate.INSERT_ALL, tableName, valuesDef);
        return new GenericDatabaseStatement(database, insertIntoSQL);
    }

    @Override
    public DatabaseStatement insertAll(int parameters) {
        String values = String.join(", ", Collections.nCopies(parameters, "?"));
        String insertIntoSQL = String.format(StatementTemplate.INSERT_ALL, tableName, values);
        return new GenericDatabaseStatement(database, insertIntoSQL);
    }

    @Override
    public DatabaseStatement set(String clause, String... modifications) {
        String modificationsDef = String.join(", ", modifications);
        String updateSQL = String.format(StatementTemplate.SET_EACH, tableName, modificationsDef, clause);
        return new GenericDatabaseStatement(database, updateSQL);
    }

    @Override
    public DatabaseStatement set(String clause) {
        String updateSQL = String.format(StatementTemplate.SET, tableName, clause);
        return new GenericDatabaseStatement(database, updateSQL);
    }

    @Override
    public DatabaseStatement delete(String clause) {
        String deleteFromSQL = String.format(StatementTemplate.DELETE, tableName, clause);
        return new GenericDatabaseStatement(database, deleteFromSQL);
    }

    @Override
    public DatabaseStatement select(String clause) {
        String selectSQL = String.format(StatementTemplate.SELECT_ALL, tableName, clause);
        return new GenericDatabaseStatement(database, selectSQL);
    }

    @Override
    public DatabaseStatement select(String clause, String... columns) {
        String columnsDef = String.join(", ", columns);
        String selectSQL = String.format(StatementTemplate.SELECT, columnsDef, tableName, clause);
        return new GenericDatabaseStatement(database, selectSQL);
    }

    @Override
    public DatabaseStatement selectAll() {
        String selectSQL = String.format(StatementTemplate.SELECT_ALL, tableName, "");
        return new GenericDatabaseStatement(database, selectSQL);
    }

    @Override
    public DatabaseStatement selectAll(String... columns) {
        String columnsDef = String.join(", ", columns);
        String selectSQL = String.format(StatementTemplate.SELECT, columnsDef, tableName, "");
        return new GenericDatabaseStatement(database, selectSQL);
    }

}