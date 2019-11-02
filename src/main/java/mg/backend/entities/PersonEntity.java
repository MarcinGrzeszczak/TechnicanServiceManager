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

    private PersonEntity() {
        super();
        String emptyAddress = "empty Address";
        this.init(emptyAddress);
    }

    public PersonEntity(long id, String name, String address, String description) {
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

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) {
        // TODO Auto-generated method stub

    }
}