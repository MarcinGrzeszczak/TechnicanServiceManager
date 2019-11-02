package mg.backend;

import java.io.IOException;
import java.sql.SQLException;

import mg.backend.database.DatabaseConnection;

public class BackendFasade {
    final String config = "resources/config.properties";

    private DatabaseConnection dbConn;

    public BackendFasade() {

    }

    public void initConnection() throws IOException, SQLException {
        dbConn = new DatabaseConnection(this.config);
        dbConn.requestQuery();
    }
}