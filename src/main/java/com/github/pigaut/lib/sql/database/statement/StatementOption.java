package com.github.pigaut.lib.sql.database.statement;

import java.sql.*;

@FunctionalInterface
public interface StatementOption {

    void apply(PreparedStatement statement) throws SQLException;

}
