package io.github.pigaut.lib.sql.database.statement;

import java.sql.*;

@FunctionalInterface
public interface RowQueryReader extends QueryReader {

    @Override
    default void read(ResultSet results) throws SQLException {
        while (results.next()) {
            readNext(results);
        }
    }

    void readNext(ResultSet nextResult) throws SQLException;

}
