package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;

import mg.backend.datastructure.CostsHierarchy;
import mg.backend.entities.CostEntity;

public class CostFactory extends TableFactory<CostEntity, CostsHierarchy> {
    private static final String TABLE_NAME = "costs";



    public CostFactory() {
        super(TABLE_NAME);
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) throws SQLException {
        super.entity = CostEntity.builder()
            .setId(data.getInt(1))
            .setParentId(data.getInt(2))
            .setName(data.getString(3))
            .setPrice(data.getDouble(4))
            .setVat(data.getDouble(5))
            .setDiscount(data.getDouble(6))
            .setDescription(data.getString(7))
            .build();

    }

    @Override
    public CostsHierarchy getHierarchy() {
        return new CostsHierarchy(super.entity);
    }
}