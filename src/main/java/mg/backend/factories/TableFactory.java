package mg.backend.factories;

import mg.backend.database.DatabaseContract;

public abstract class TableFactory<T> implements DatabaseContract {
    
    private String tableName;

    public TableFactory(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
    
    public abstract T getEntity();
}