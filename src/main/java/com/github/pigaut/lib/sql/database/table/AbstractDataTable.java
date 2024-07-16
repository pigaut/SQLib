package com.github.pigaut.lib.sql.database.table;

import com.github.pigaut.lib.sql.*;

public abstract class AbstractDataTable<T> extends GenericDataTable {

    public AbstractDataTable(Database database, String tableName) {
        super(database, tableName);
    }

}
