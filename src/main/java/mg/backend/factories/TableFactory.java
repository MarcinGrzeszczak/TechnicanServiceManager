package mg.backend.factories;

import java.util.Map;

import mg.backend.database.DatabaseContract;
import mg.backend.datastructure.Hierarchy;
import mg.backend.entities.Entity;

public abstract class TableFactory<T extends Entity, H extends Hierarchy<?,?>> 
    implements DatabaseContract {
    
    private String tableName;
    protected T entity;

    public TableFactory(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public T getEntity() {
        return this.entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public abstract T createEmpty(); 

    public abstract void deserializeMap(Map<String, String> data);

    public abstract H getHierarchy();
}