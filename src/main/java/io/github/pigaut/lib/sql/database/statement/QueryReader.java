package io.github.pigaut.lib.sql.database.statement;

import java.sql.*;

@FunctionalInterface
public interface QueryReader {

    void read(ResultSet results) throws SQLException;

}
