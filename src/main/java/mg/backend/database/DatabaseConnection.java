package mg.backend.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

    private String host;
    private String username;
    private String password;
    private String database;

    private Connection connection;

    private void loadProperties(String pathToProperties) throws IOException {

        try (InputStream input = new FileInputStream(pathToProperties)) {
            Properties props = new Properties();

            props.load(input);

            this.host = props.getProperty("db.host");
            this.username = props.getProperty("db.username");
            this.password = props.getProperty("db.password");
            this.database = props.getProperty("db.dbName");
            
        }
    }

    private void connectToDB() throws SQLException {
        String url = "jdbc:postgresql://"
            + this.host + "/" + this.database 
            + "?user=" + this.username 
            + "&password=" + this.password 
            + "&ssl=true";

        this.connection = DriverManager.getConnection(url);
    }

    private void closeDB() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public void sendQuery(String query,DatabaseCallback callback) throws SQLException {
        Statement st = this.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM clients");
        while (rs.next()) {
            callback.run(rs);
        }
        
        rs.close();
        st.close();

    }

    public DatabaseConnection(String propertiesFile) throws IOException, SQLException {
        this.loadProperties(propertiesFile);
        this.connectToDB();
    }
}