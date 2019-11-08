package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import mg.backend.datastructure.ClientHierarchy;
import mg.backend.entities.ClientEntity;

public class ClientFactory extends TableFactory<ClientEntity, ClientHierarchy> {
    private static final String TABLE_NAME = "clients";
    
    public ClientFactory() {
        super(TABLE_NAME);
    }

    @Override
    public void deserialize(ResultSet data) throws SQLException {
        super.entity = ClientEntity.builder()
            .setId(data.getInt(1))
            .setFirstName(data.getString(2))
            .setSurname(data.getString(3))
            .setAddress(data.getString(4))
            .setEmail(data.getString(5))
            .setPhone(data.getString(6))
            .setDescription(data.getString(7))
            .build();
    }

    @Override
    public void deserializeMap(Map<String, String> data) {
        super.entity = ClientEntity.builder()
            .setFirstName(data.get("name"))
            .setSurname(data.get("surname"))
            .setAddress(data.get("address"))
            .setEmail(data.get("email"))
            .setPhone(data.get("phone"))
            .setDescription(data.get("description"))
            .build();
    }

    @Override
    public  Map<String, String> serialize() {
        Map<String, String> objMap = new LinkedHashMap<String, String>();
        objMap.put("name", super.entity.getFirstName());
        objMap.put("surname", super.entity.getSurname());
        objMap.put("address", super.entity.getAddress());
        objMap.put("email", super.entity.getEmail());
        objMap.put("phone", super.entity.getPhone());
        objMap.put("description", super.entity.getDescription());

        return objMap;
    }

    @Override
    public ClientHierarchy getHierarchy() {
        return new ClientHierarchy(super.entity, null);
    }

    @Override
    public ClientEntity createEmpty() {
        super.entity = new ClientEntity();
        return super.entity;
    }


}
