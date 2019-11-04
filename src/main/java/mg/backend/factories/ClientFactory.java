package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;

import mg.backend.entities.ClientEntity;

public class ClientFactory extends TableFactory<ClientEntity> {
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
            .build();
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub
    }
}
