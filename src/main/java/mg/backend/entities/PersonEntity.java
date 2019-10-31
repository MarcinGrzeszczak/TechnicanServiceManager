package mg.backend.entities;

public class PersonEntity extends Entity {

    private String address;
    
    public static Builder builder() {
        return new Builder();
    }

    public PersonEntity() {
        super();
        String emptyAddress = "empty Address";
        this.init(emptyAddress);
    }

    private PersonEntity(long id, String name, String address, String description) {
        super(id, name, description);
        this.init(address);
    }

    private void init(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    String show() {
        // TODO Auto-generated method stub
        return null;
    }


    public static class Builder {
        private long id; 
        private String name;
        private String address; 
        private String description;
        
        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public PersonEntity build() {
            return new PersonEntity(this.id, this.name, this.address, this.description);
        }
    }
}