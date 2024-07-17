package io.github.pigaut.lib.sql.database.table;

import io.github.pigaut.lib.sql.*;

public abstract class AbstractDataTable extends GenericDataTable {

    public AbstractDataTable(Database database, String tableName) {
        super(database, tableName);
    }

}
