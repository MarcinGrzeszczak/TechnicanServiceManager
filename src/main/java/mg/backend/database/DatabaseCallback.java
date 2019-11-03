package mg.backend.database;

import java.sql.ResultSet;

public interface DatabaseCallback {
    public void run(ResultSet data);
}