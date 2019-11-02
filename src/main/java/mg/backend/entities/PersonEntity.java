package mg.backend.entities;

import java.sql.ResultSet;

public class PersonEntity extends Entity {
    
    public static String TABLE_NAME = "CLIENTS";
    private String address;

    public static PersonEntity build(ResultSet data) {
        PersonEntity newPerson = new PersonEntity();
        newPerson.deserialize(data);
        return newPerson;
    }

    public PersonEntity() {
        super();
        String emptyAddress = "empty Address";
        this.init(emptyAddress);
    }

    public PersonEntity(int id, String name, String address, String description) {
        super(id, name, description);
        this.init(address);
    }

    private void init(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    
    public void setId(Integer id){
        super.setId(id);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String show() {
        // TODO Auto-generated method stub
        return "id: " + this.id + "\n"
        + "name: " + this.name + "\n"
        + "address: "+ this.address + "\n";
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) {
        // TODO Auto-generated method stub

    }
}