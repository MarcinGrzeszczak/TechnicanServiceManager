package mg.backend.entities;

import mg.backend.database.DatabaseContract;

abstract class Entity implements DatabaseContract {
    protected String name;
    protected int id;
    protected String description;
    
    public String tableName;

    Entity() {
        this.name = "Empty name";
        this.description = "Empty description";
        this.id = -1;
    }

    Entity(int id, String name, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
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

    public void setId(int id) {
        this.id = id;
    }

    abstract String show();


}