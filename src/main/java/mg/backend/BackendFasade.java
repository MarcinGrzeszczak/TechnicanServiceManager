package mg.backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import mg.backend.database.DatabaseConnection;
import mg.backend.entities.ClientEntity;
import mg.backend.entities.CostEntity;
import mg.backend.entities.DeviceEntity;
import mg.backend.entities.HistoryEntity;
import mg.backend.factories.ClientFactory;
import mg.backend.factories.CostFactory;
import mg.backend.factories.DeviceFactory;
import mg.backend.factories.EntityListFactory;
import mg.backend.factories.HistoryFactory;

public class BackendFasade {
    final String config = "resources/config.properties";

    private DatabaseConnection dbConn;

    private EntityListFactory<ClientFactory, ClientEntity> clientsFactory;
    private EntityListFactory<DeviceFactory, DeviceEntity> devicesFactory;
    private EntityListFactory<HistoryFactory, HistoryEntity> historyFactory;
    private EntityListFactory<CostFactory, CostEntity> costsFactory;
    
    private List<ClientEntity> clients;
    private List<DeviceEntity> devices;
    private List<HistoryEntity> history;
    private List<CostEntity> costs;

    public BackendFasade() {

        this.clientsFactory = 
            new EntityListFactory<>(new ClientFactory(), dbConn);
        
        this.devicesFactory = 
            new EntityListFactory<DeviceFactory,DeviceEntity>(new DeviceFactory(), dbConn);

        this.historyFactory =
            new EntityListFactory<HistoryFactory,HistoryEntity>(new HistoryFactory(), dbConn);
        
        this.costsFactory =
            new EntityListFactory<CostFactory,CostEntity>(new CostFactory(), dbConn);

    }

    public void initConnection() throws IOException, SQLException {
        dbConn = new DatabaseConnection(this.config);

        

        System.out.println(clients.stream()
            .map((data) -> data.show())
            .collect(Collectors.joining("\n")));
    }

    public void loadClients() throws SQLException {
        this.clients = this.clientsFactory.createList();
    }
}