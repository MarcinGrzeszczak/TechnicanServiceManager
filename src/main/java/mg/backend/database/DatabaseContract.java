package mg.backend.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseContract {
    
    public void serialize();

    void deserialize(ResultSet data) throws SQLException;
}