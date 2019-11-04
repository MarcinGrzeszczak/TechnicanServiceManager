package mg.backend.entities;

public abstract class Entity {

    protected String name;
    protected long id;
    protected long parentId;
    protected String description;
    
    public Entity() {
        this.name = "Empty name";
        this.description = "Empty description";
        this.id = -1;
    }

    public Entity(long id, long parentId, String name, String description) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getParentId() {
        return parentId;
    }
    
    public abstract String show();

}