package mg.backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import mg.backend.database.DatabaseConnection;
import mg.backend.entities.ClientEntity;
import mg.backend.factories.ClientFactory;
import mg.backend.factories.EntityListFactory;

public class BackendFasade {
    final String config = "resources/config.properties";

    private DatabaseConnection dbConn;

    EntityListFactory<ClientFactory, ClientEntity> clientFactory;
    List<ClientEntity> clients;

    public BackendFasade() {
        this.clientFactory = new EntityListFactory<>(new ClientFactory(), dbConn);
        
    }

    public void initConnection() throws IOException, SQLException {
        dbConn = new DatabaseConnection(this.config);

        

        System.out.println(clients.stream()
            .map((data) -> data.show())
            .collect(Collectors.joining("\n")));
    }

    public void loadClients() throws SQLException {
        this.clients = (List<ClientEntity>) this.clientFactory.createList();
    }
}