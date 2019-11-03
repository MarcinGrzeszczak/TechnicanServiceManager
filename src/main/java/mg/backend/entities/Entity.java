package mg.backend.entities;

import mg.backend.database.DatabaseContract;

abstract class Entity implements DatabaseContract {

    protected String name;
    protected long id;
    protected String description;
    
    private String tableName;

    Entity(String tableName) {
        this.tableName = tableName;
        this.name = "Empty name";
        this.description = "Empty description";
        this.id = -1;
    }

    Entity(String tableName,long id, String name, String description) {
        this.tableName = tableName;
        this.name = name;
        this.id = id;
        this.description = description;
    }

    String getTableName() {
        return this.tableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    abstract String show();

}