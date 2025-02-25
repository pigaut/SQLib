package io.github.pigaut.sql.database.table;

import io.github.pigaut.sql.*;

public abstract class AbstractDataTable extends GenericDataTable {

    public AbstractDataTable(Database database, String tableName) {
        super(tableName, database);
    }

}
