package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;

import mg.backend.entities.DeviceEntity;

public class DeviceFactory extends TableFactory<DeviceEntity> {
    private static final String TABLE_NAME = "devices";

    public DeviceFactory() {
        super(TABLE_NAME);
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) throws SQLException {
        super.entity = DeviceEntity.builder()
            .setId(data.getInt(0))
            .setParentId(data.getInt(1))
            .setName(data.getString(2))
            .setSerialNumber(data.getString(3))
            .setDescription(data.getString(4))
            .build();
    }
    
}