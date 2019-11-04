package mg.backend.factories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mg.backend.database.DatabaseConnection;
import mg.backend.entities.Entity;

public class EntityListFactory<T extends TableFactory, E extends Entity> {
    private DatabaseConnection databaseConnection;
    private T entityFactory;

    public EntityListFactory(T factory, DatabaseConnection databaseConnection) {
        this.entityFactory = factory;
        this.databaseConnection = databaseConnection;        
    }
    
    public List<E> createList() throws SQLException {
        
        List<E> entityList = new ArrayList<>();

        this.databaseConnection.sendQuery(
            "SELECT * FROM" + this.entityFactory.getTableName(), (data) -> {
                try {
                    this.entityFactory.deserialize(data);
                    entityList.add((E) this.entityFactory.getEntity());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
            });
        return entityList;
    }
}