package com.github.pigaut.lib.sql.database.table;

import com.github.pigaut.lib.sql.*;
import com.github.pigaut.lib.sql.database.statement.*;

import java.util.*;

public class GenericDataTable implements DataTable {

    private final Database database;
    private String tableName;

    public GenericDataTable(Database database, String tableName) {
        this.database = database;
        this.tableName = tableName;
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
        String createTableSQL = String.format(StatementTemplates.CREATE_TABLE, "", tableName, columnsDef);
        database.execute(createTableSQL);
    }

    @Override
    public void createIfNotExists(String... columns) {
        String columnsDef = String.join(", ", columns);
        String createTableSQL = String.format(StatementTemplates.CREATE_TABLE_IF_NOT_EXISTS, tableName, columnsDef);
        database.execute(createTableSQL);
    }

    @Override
    public void drop() {
        String dropTableSQL = String.format(StatementTemplates.DROP_TABLE, tableName);
        database.execute(dropTableSQL);
    }

    @Override
    public void rename(String newName) {
        String renameTableSQL = String.format(StatementTemplates.RENAME_TABLE, tableName, newName);
        database.execute(renameTableSQL);
        tableName = newName;
    }

    @Override
    public void clear() {
        String deleteFromSQL = String.format(StatementTemplates.DELETE_ALL, tableName);
        database.executeUpdate(deleteFromSQL);
    }

    @Override
    public void addColumn(String columnDefinition) {
        String addColumnStmt = String.format(StatementTemplates.ADD_COLUMN, tableName, columnDefinition);
        database.execute(addColumnStmt);
    }

    @Override
    public void renameColumn(String oldName, String newName) {
        String renameColumnStmt = String.format(StatementTemplates.RENAME_COLUMN, tableName, oldName, newName);
        database.execute(renameColumnStmt);
    }

    @Override
    public DatabaseStatement insert(String clause) {
        String sql = String.format(StatementTemplates.INSERT, tableName, clause);
        return new SimpleDatabaseStatement(database, sql);
    }

    @Override
    public DatabaseStatement insertInto(String... columns) {
        String columnsDef = String.join(", ", columns);
        String values = String.join(", ", Collections.nCopies(columns.length, "?"));
        String insertIntoSQL = String.format(StatementTemplates.INSERT_COLUMNS, tableName, columnsDef, values);
        return new SimpleDatabaseStatement(database, insertIntoSQL);
    }

    @Override
    public DatabaseStatement insertAll(String... values) {
        String valuesDef = String.join(", ", values);
        String insertIntoSQL = String.format(StatementTemplates.INSERT_ALL, tableName, valuesDef);
        return new SimpleDatabaseStatement(database, insertIntoSQL);
    }

    @Override
    public DatabaseStatement insertAll(int parameters) {
        String values = String.join(", ", Collections.nCopies(parameters, "?"));
        String insertIntoSQL = String.format(StatementTemplates.INSERT_ALL, tableName, values);
        return new SimpleDatabaseStatement(database, insertIntoSQL);
    }

    @Override
    public DatabaseStatement set(String clause, String... modifications) {
        String modificationsDef = String.join(", ", modifications);
        String updateSQL = String.format(StatementTemplates.SET_EACH, tableName, modificationsDef, clause);
        return new SimpleDatabaseStatement(database, updateSQL);
    }

    @Override
    public DatabaseStatement set(String clause) {
        String updateSQL = String.format(StatementTemplates.SET, tableName, clause);
        return new SimpleDatabaseStatement(database, updateSQL);
    }

    @Override
    public DatabaseStatement delete(String clause) {
        String deleteFromSQL = String.format(StatementTemplates.DELETE, tableName, clause);
        return new SimpleDatabaseStatement(database, deleteFromSQL);
    }

    @Override
    public DatabaseStatement select(String clause) {
        String selectSQL = String.format(StatementTemplates.SELECT_ALL, tableName, clause);
        return new SimpleDatabaseStatement(database, selectSQL);
    }

    @Override
    public DatabaseStatement select(String clause, String... columns) {
        String columnsDef = String.join(", ", columns);
        String selectSQL = String.format(StatementTemplates.SELECT, columnsDef, tableName, clause);
        return new SimpleDatabaseStatement(database, selectSQL);
    }

    @Override
    public DatabaseStatement selectAll() {
        String selectSQL = String.format(StatementTemplates.SELECT_ALL, tableName, "");
        return new SimpleDatabaseStatement(database, selectSQL);
    }

    @Override
    public DatabaseStatement selectAll(String... columns) {
        String columnsDef = String.join(", ", columns);
        String selectSQL = String.format(StatementTemplates.SELECT, columnsDef, tableName, "");
        return new SimpleDatabaseStatement(database, selectSQL);
    }

}