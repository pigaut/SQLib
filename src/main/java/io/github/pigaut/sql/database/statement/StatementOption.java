package io.github.pigaut.sql.database.statement;

import java.sql.*;

@FunctionalInterface
public interface StatementOption {

    void apply(PreparedStatement statement) throws SQLException;

}
