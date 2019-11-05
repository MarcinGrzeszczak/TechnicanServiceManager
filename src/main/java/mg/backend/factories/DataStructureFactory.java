package mg.backend.factories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mg.backend.database.DatabaseConnection;
import mg.backend.datastructure.Hierarchy;
import mg.backend.entities.Entity;

public class DataStructureFactory<T extends TableFactory<?,H>, H extends Hierarchy<?,?>> {
    private DatabaseConnection databaseConnection;
    private T entityFactory;
    private List<H> hierarchyList;
    
    public DataStructureFactory(T factory, DatabaseConnection databaseConnection) {
        this.entityFactory = factory;
        this.databaseConnection = databaseConnection;        
    }

    public List<H> generateEntities() throws SQLException {
        
        this.hierarchyList = new ArrayList<>();

        this.databaseConnection.sendQuery(
            "SELECT * FROM" + this.entityFactory.getTableName(), (data) -> {
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