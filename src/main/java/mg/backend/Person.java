package mg.backend;

import java.util.ArrayList;
import java.util.List;

class Person extends Entity {

    private String address;
    private List<Device> devices;
    
    Person() {
        super();
        String emptyAddress = "empty Address";
        this.init(emptyAddress);
    }

    Person(long id, String name, String address, String description) {
        super(id, name, description);
        this.init(address);
    }

    private void init(String address) {
        this.address = address;
        this.devices = new ArrayList<Device>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    @Override
    public void load() {
        // TODO Auto-generated method stub

    }

    @Override
    String show() {
        // TODO Auto-generated method stub
        return null;
    }

}