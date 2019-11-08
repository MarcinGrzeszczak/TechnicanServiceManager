package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import mg.backend.datastructure.DeviceHierarchy;
import mg.backend.entities.DeviceEntity;

public class DeviceFactory extends TableFactory<DeviceEntity, DeviceHierarchy> {
    private static final String TABLE_NAME = "devices";

    public DeviceFactory() {
        super(TABLE_NAME);
    }

    @Override
    public void deserialize(ResultSet data) throws SQLException {
        super.entity = DeviceEntity.builder()
            .setId(data.getInt(1))
            .setParentId(data.getInt(2))
            .setName(data.getString(3))
            .setSerialNumber(data.getString(4))
            .setDescription(data.getString(5))
            .build();
    }

    @Override
    public DeviceHierarchy getHierarchy() {
        return new DeviceHierarchy(super.entity, null);
    }

    @Override
    public Map<String, String> serialize() {
        Map<String, String> data = new LinkedHashMap<>();

        data.put("parentID", String.valueOf(super.entity.getParentId()));
        data.put("name", super.entity.getName());
        data.put("serialNumber", super.entity.getSerialNumber());
        data.put("description", super.entity.getDescription());

        return data;
    }

    @Override
    public void deserializeMap(Map<String, String> data) {
        super.entity = DeviceEntity.builder()
            .setParentId(Long.parseLong(data.get("parentID")))
            .setName(data.get("name"))
            .setSerialNumber(data.get("serialNumber"))
            .setDescription(data.get("description"))
            .build();

    }

    @Override
    public DeviceEntity createEmpty() {
        super.entity = new DeviceEntity();
        return super.entity;
    }
}