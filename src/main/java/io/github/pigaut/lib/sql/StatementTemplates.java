package io.github.pigaut.lib.sql;

public class StatementTemplates {

    private StatementTemplates() {}

    public static final String CREATE_TABLE = "CREATE TABLE %s (%s);";
    public static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS %s (%s);";
    public static final String DROP_TABLE = "DROP TABLE %s;";
    public static final String RENAME_TABLE = "ALTER TABLE %s RENAME TO %s;";
    public static final String ADD_COLUMN = "ALTER TABLE %s ADD %s;";
    public static final String RENAME_COLUMN = "ALTER TABLE %s RENAME COLUMN %s TO %s";

    public static final String INSERT = "INSERT INTO %s %s;";
    public static final String INSERT_COLUMNS = "INSERT INTO %s (%s) VALUES (%s);";
    public static final String INSERT_ALL = "INSERT INTO %s VALUES (%s);";
    public static final String SET = "UPDATE %s SET %s";
    public static final String SET_EACH = "UPDATE %s SET %s %s";
    public static final String DELETE = "DELETE FROM %s %s;";
    public static final String DELETE_ALL = "DELETE FROM %s;";
    public static final String SELECT = "SELECT %s FROM %s %s;";
    public static final String SELECT_ALL = "SELECT * FROM %s %s;";

}
