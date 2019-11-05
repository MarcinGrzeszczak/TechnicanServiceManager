package mg.backend.factories;

import mg.backend.database.DatabaseContract;

public abstract class TableFactory<T, H> implements DatabaseContract {
    
    private String tableName;
    protected T entity;

    public TableFactory(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
    
    public T getEntity() {
        return this.entity;
    }

    public abstract H getHierarchy();
}