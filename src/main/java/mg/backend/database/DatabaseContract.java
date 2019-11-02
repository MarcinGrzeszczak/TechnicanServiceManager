package mg.backend.database;

import java.sql.ResultSet;

public interface DatabaseContract {
    
    public void serialize();

    void deserialize(ResultSet data);
}