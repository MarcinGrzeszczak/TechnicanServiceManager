package mg.backend.entities;

abstract class Entity {
    protected String name;
    protected long id;
    protected String description;

    Entity() {
        this.name = "Empty name";
        this.description = "Empty description";
        this.id = -1;
    }

    Entity(long id, String name, String description) {
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

    public void setId(long id) {
        this.id = id;
    }

    abstract String show();


}