package mg.backend.entities;

import java.sql.ResultSet;

public class DeviceEntity extends Entity {
    private static final String TABLE_NAME = "devices";

    private String serialNumber;

    DeviceEntity() {
        super(TABLE_NAME);
        String emptySerialNumber = "Empty serialnumber";
        this.init(emptySerialNumber);
    }

    DeviceEntity(long id, String name, String serialNumber, String description) {
        super(TABLE_NAME, id, name, description);
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