package mg.backend.entities;

import java.sql.ResultSet;

public class ClientEntity extends Entity {

    public static String TABLE_NAME = "clients";

    private String address;

    private ClientEntity() {
        super(TABLE_NAME);
        String emptyAddress = "empty Address";
        this.init(emptyAddress);
    }

    public ClientEntity(long id, String name, String address, String description) {
        super(TABLE_NAME, id, name, description);
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

    }

    @Override
    public void deserialize(ResultSet data) {
        
    }
}