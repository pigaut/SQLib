package io.github.pigaut.sql.database.statement;

import io.github.pigaut.sql.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class GenericDatabaseStatement implements DatabaseStatement {

    private final Database database;
    private String sql;
    private final List<StatementOption> options = new ArrayList<>();
    private int currentParameter = 1;

    public GenericDatabaseStatement(Database database, String sql) {
        this.database = database;
        this.sql = sql;
    }

    @Override
    public DatabaseStatement replace(String placeholder, String value) {
        sql = sql.replaceAll(placeholder, value);
        return this;
    }

    @Override
    public DatabaseStatement addOption(StatementOption option) {
        options.add(option);
        return this;
    }

    @Override
    public void clearOptions() {
        options.clear();
        currentParameter = 1;
    }

    @Override
    public DatabaseStatement addBatch() {
        options.add(PreparedStatement::addBatch);
        currentParameter = 1;
        return this;
    }

    @Override
    public DatabaseStatement withNullParameter() {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setNull(parameterIndex, Types.NULL));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(Object object) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setObject(parameterIndex, object));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(Object object, int sqlType) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setObject(parameterIndex, object, sqlType));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(boolean value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setBoolean(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(String value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setString(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(int value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setInt(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(long value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setLong(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(short value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setShort(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(byte value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setByte(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(float value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setFloat(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(double value) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setDouble(parameterIndex, value));
        currentParameter++;
        return this;
    }

    @Override
    public DatabaseStatement withParameter(InputStream inputStream) {
        final int parameterIndex = currentParameter;
        options.add(stmt -> stmt.setBinaryStream(parameterIndex, inputStream));
        currentParameter++;
        return this;
    }

    @Override
    public boolean execute() {
        return executeStatement(PreparedStatement::execute);
    }

    @Override
    public int executeUpdate() {
        return executeStatement(PreparedStatement::executeUpdate);
    }

    @Override
    public long executeLargeUpdate() {
        return executeStatement(PreparedStatement::executeLargeUpdate);
    }

    @Override
    public void executeQuery(QueryReader reader) {
        executeStatement(preparedStatement -> {
            try (ResultSet results = preparedStatement.executeQuery()) {
                reader.read(results);
            }
            return null;
        });
    }

    @Override
    public void fetchRow(QueryReader reader) {
        executeStatement(preparedStatement -> {
            try (ResultSet results = preparedStatement.executeQuery()) {
                if (results.next()) {
                    reader.read(results);
                }
            }
            return null;
        });
    }

    @Override
    public void fetchAllRows(QueryReader reader) {
        executeStatement(preparedStatement -> {
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    reader.read(results);
                }
            }
            return null;
        });
    }

    @Override
    public int[] executeBatch() {
        return executeStatement(PreparedStatement::executeBatch);
    }

    @Override
    public long[] executeLargeBatch() {
        return executeStatement(PreparedStatement::executeLargeBatch);
    }

    private <T> T executeStatement(StatementExecutor<T> executor) {
        try (Connection connection = database.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (StatementOption option : options) {
                    option.apply(statement);
                }
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
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }
}
