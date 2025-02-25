package io.github.pigaut.sql.database.statement;

import java.sql.*;

@FunctionalInterface
public interface QueryReader {

    void read(ResultSet results) throws SQLException;

}
