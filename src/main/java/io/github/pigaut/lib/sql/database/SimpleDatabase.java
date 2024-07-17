package io.github.pigaut.lib.sql.database;

import io.github.pigaut.lib.sql.*;
import io.github.pigaut.lib.sql.database.statement.*;
import io.github.pigaut.lib.sql.database.table.*;

import javax.sql.*;
import java.sql.*;

public class SimpleDatabase implements Database {

    private final String name;
    private final DataSource dataSource;

    public SimpleDatabase(String name, DataSource dataSource) {
        this.name = name;
        this.dataSource = dataSource;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GenericDataTable tableOf(String name) {
        return new GenericDataTable(this, name);
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    private <T> T executeStatement(StatementExecutor<T> executor) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try {
                    return executor.execute(statement);
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FunctionalInterface
    private interface StatementExecutor<T> {
        T execute(Statement statement) throws SQLException;
    }

    @Override
    public void execute(String sql) {
        executeStatement(statement -> statement.execute(sql));
    }

    @Override
    public int executeUpdate(String sql) {
        return executeStatement(statement -> statement.executeUpdate(sql));
    }

    @Override
    public long executeLargeUpdate(String sql) {
        return executeStatement(statement -> statement.executeLargeUpdate(sql));
    }

    @Override
    public void executeQuery(String sql, QueryReader reader) {
        executeStatement(statement -> {
            try (ResultSet results = statement.executeQuery(sql)) {
                reader.read(results);
            }
            return null;
        });
    }

}