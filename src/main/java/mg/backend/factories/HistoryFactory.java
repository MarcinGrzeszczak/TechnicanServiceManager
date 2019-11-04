package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;

import mg.backend.entities.HistoryEntity;

public class HistoryFactory extends TableFactory<HistoryEntity> {
    private static final String TABLE_NAME = "history";

    public HistoryFactory() {
        super(TABLE_NAME);
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) throws SQLException {
        super.entity = HistoryEntity.builder()
            .setId(data.getInt(1))
            .setParentId(data.getInt(2))
            .setName(data.getString(3))
            .setAcceptanceDate(data.getDate(4))
            .setDueDate(data.getDate(5))
            .setDescription(data.getString(6))
            .build();
        
    }

}