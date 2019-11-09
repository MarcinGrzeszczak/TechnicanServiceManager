package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import mg.backend.datastructure.CostsHierarchy;
import mg.backend.entities.CostEntity;

public class CostFactory extends TableFactory<CostEntity, CostsHierarchy> {
    private static final String TABLE_NAME = "costs";

    public CostFactory() {
        super(TABLE_NAME);
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
            .setDescription(data.getString(7)).build();

    }

    @Override
    public CostsHierarchy getHierarchy() {
        return new CostsHierarchy(super.entity);
    }

    @Override
    public Map<String, String> serialize() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("parent_id", String.valueOf(super.entity.getParentId()));
        data.put("name", super.entity.getName());
        data.put("price", String.valueOf(super.entity.getPrice()));
        data.put("vat", String.valueOf(super.entity.getVat()));
        data.put("discount", String.valueOf(super.entity.getDiscount()));
        data.put("description", super.entity.getDescription());

        return data;
    }

    @Override
    public void deserializeMap(Map<String, String> data) {
        super.entity = CostEntity.builder()
            .setParentId(Long.parseLong(data.get("parent_id")))
            .setName(data.get("name"))
            .setPrice(Double.parseDouble(data.get("price")))
            .setVat(Double.parseDouble(data.get("vat")))
            .setDiscount(Double.parseDouble(data.get("discount")))
            .setDescription(data.get("description"))
            .build();

    }

    @Override
    public CostEntity createEmpty() {
        super.entity = new CostEntity();
        return super.entity;
    }
}