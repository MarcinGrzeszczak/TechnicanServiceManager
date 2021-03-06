package mg.backend.factories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mg.backend.database.DatabaseConnection;
import mg.backend.datastructure.Hierarchy;

public class DataStructureFactory<T extends TableFactory<?,H>, H extends Hierarchy<?,?>> {
    private DatabaseConnection databaseConnection;
    private T entityFactory;
    private List<H> hierarchyList;

    public DataStructureFactory(T factory, DatabaseConnection databaseConnection) {
        this.entityFactory = factory;
        this.databaseConnection = databaseConnection;        
    }

    public T getEntityFactory() {
        return this.entityFactory;
    }

    public void saveEntity(Long id) throws SQLException {
        Map<String, String> data = this.entityFactory.serialize();
        String query;
        String names = "( ";
        String values = "( ";
        for (String key : data.keySet()) {
            names += key + ", ";
            values += "'" + data.get(key) + "'" + ", ";
        }
        names = names.substring(0, names.length() - 2) + ")";
        values = values.substring(0, values.length() - 2) + ")";;
        if (id == null) {
            query = "INSERT INTO " 
                + this.entityFactory.getTableName()
                + names
                + " VALUES" + values
                + " RETURNING id";

        } else {
            query = "UPDATE "
                + this.entityFactory.getTableName()
                + " SET " + names
                + " = " + values
                + "WHERE id = " + (int) id.longValue()
                + " RETURNING id";
        }

        this.databaseConnection.sendQuery(query, (resultSet) -> {
            try {
                this.entityFactory.entity.setId(resultSet.getLong(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteEntity(Long id) {
        String query = "DELETE FROM " + this.entityFactory.getTableName() + " WHERE id = " + id + "RETURNING id;";

        try {
            this.databaseConnection.sendQuery(query, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<H> generateEntities(Long parentID) throws SQLException {
        
        this.hierarchyList = new ArrayList<>();

        String query =  "SELECT * FROM " + this.entityFactory.getTableName();

        if (parentID != null) {
            query += " WHERE parent_id=" + parentID.longValue();
        }

        this.databaseConnection.sendQuery(query, (data) -> {
            try {
                this.entityFactory.deserialize(data);
                this.hierarchyList.add(this.entityFactory.getHierarchy());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        });
        return this.hierarchyList;
    }
}