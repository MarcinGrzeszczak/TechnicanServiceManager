package mg.backend.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mg.backend.database.DatabaseConnection;

public class EntityListFactory<T extends Entity> {
    private Class<T> clazz;
    private T obj;
    private DatabaseConnection databaseConnection;

    EntityListFactory(Class<T> clazz, DatabaseConnection databaseConnection) {
        this.clazz = clazz;
        this.databaseConnection = databaseConnection;

        this.obj = this.createObject();
        
    }

    List<T> createList() throws SQLException {

        List<T> entityList = new ArrayList<>();

        this.databaseConnection.sendQuery("SELECT * FROM" + obj.getTableName(), (data) -> {
            this.obj.deserialize(data);
            entityList.add(obj);
            this.obj = this.createObject();
        });
        return entityList;
    }

    private T createObject() {
        try {
            T obj = this.clazz.newInstance();
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}