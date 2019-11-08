package mg.backend.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DatabaseContract {
    
    public  Map<String, String> serialize();

    public void deserialize(ResultSet data) throws SQLException;
}