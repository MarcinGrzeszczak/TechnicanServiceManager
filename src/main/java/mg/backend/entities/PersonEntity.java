package mg.backend.entities;

class PersonEntity extends Entity {

    private String address;
    
    PersonEntity() {
        super();
        String emptyAddress = "empty Address";
        this.init(emptyAddress);
    }

    PersonEntity(long id, String name, String address, String description) {
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
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize() {
        // TODO Auto-generated method stub

    }

    @Override
    String show() {
        // TODO Auto-generated method stub
        return null;
    }

}