package mg.backend.factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import mg.backend.datastructure.HistoryHierarchy;
import mg.backend.entities.HistoryEntity;

public class HistoryFactory extends TableFactory<HistoryEntity, HistoryHierarchy> {
    private static final String TABLE_NAME = "history";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public HistoryFactory() {
        super(TABLE_NAME);
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

    @Override
    public HistoryHierarchy getHierarchy() {
        return new HistoryHierarchy(super.entity, null);
    }

    @Override
    public Map<String, String> serialize() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("parent_id", String.valueOf(super.entity.getParentId()));
        data.put("name", super.entity.getName());
        data.put("acceptance_date", super.entity.getAcceptanceDate().toString());
        data.put("due_date", super.entity.getDueDate().toString());
        data.put("description", super.entity.getDescription());

        return data;
    }

    @Override
    public void deserializeMap(Map<String, String> data) {
        try {
            super.entity = HistoryEntity.builder()
                .setParentId(Long.valueOf(data.get("parent_id")))
                .setName(data.get("name"))
                .setAcceptanceDate(dateFormat.parse(data.get("acceptance_date")))
                .setDueDate(dateFormat.parse(data.get("due_date")))
                .setDescription(data.get("description")).build();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public HistoryEntity createEmpty() {
        super.entity = new HistoryEntity();
        return super.entity;
    }
}