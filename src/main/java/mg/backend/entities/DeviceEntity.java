package mg.backend.entities;

public class DeviceEntity extends Entity {

    private String serialNumber;
    
    DeviceEntity() {
        super();
        String emptySerialNumber = "Empty serialnumber";
        this.init(emptySerialNumber);
    }

    DeviceEntity(long id, String name, String serialNumber, String description) {
        super(id, name, description);
        this.init(serialNumber);
    }

    private void init(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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