package mg.backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import mg.backend.database.DatabaseConnection;
import mg.backend.datastructure.ClientHierarchy;
import mg.backend.datastructure.CostsHierarchy;
import mg.backend.datastructure.DeviceHierarchy;
import mg.backend.datastructure.Hierarchy;
import mg.backend.datastructure.HistoryHierarchy;
import mg.backend.datastructure.RootHierarchy;
import mg.backend.entities.ClientEntity;
import mg.backend.entities.CostEntity;
import mg.backend.entities.DeviceEntity;
import mg.backend.entities.HistoryEntity;
import mg.backend.factories.ClientFactory;
import mg.backend.factories.CostFactory;
import mg.backend.factories.DataStructureFactory;
import mg.backend.factories.DeviceFactory;
import mg.backend.factories.HistoryFactory;

public class BackendFasade {
    final String config = "resources/config.properties";

    private DataStructureFactory<ClientFactory, ClientHierarchy> clientsFactory;
    private DataStructureFactory<DeviceFactory, DeviceHierarchy> devicesFactory;
    private DataStructureFactory<HistoryFactory, HistoryHierarchy> historyFactory;
    private DataStructureFactory<CostFactory, CostsHierarchy> costsFactory;

    private RootHierarchy structureRoot;
    private DatabaseConnection databaseConnection;

    public BackendFasade() throws IOException, SQLException {

        this.initConnection();

        this.structureRoot = new RootHierarchy(null, null);

        this.clientsFactory = 
            new DataStructureFactory<>(new ClientFactory(), this.databaseConnection);

        this.devicesFactory = 
            new DataStructureFactory<>(new DeviceFactory(), this.databaseConnection);

        this.historyFactory = 
            new DataStructureFactory<>(new HistoryFactory(), this.databaseConnection);

        this.costsFactory = 
            new DataStructureFactory<>(new CostFactory(), this.databaseConnection);
    }

    private void initConnection() throws IOException, SQLException {
        this.databaseConnection = new DatabaseConnection(this.config);
    }

    public List<ClientEntity> loadAllClients() throws SQLException {
        if (this.structureRoot.getChilds() == null) {
            this.structureRoot
                    .setChilds(this.clientsFactory.generateEntities());
        }

        return this.structureRoot.getChilds().stream()
                .map(Hierarchy::getData)
                .collect(Collectors.toList());
    }

    public List<DeviceEntity> loadDevices(long clientId) throws SQLException {
        if (this.structureRoot.getChildByID(clientId).getChilds() == null) {
            this.structureRoot.getChildByID(clientId)
                .setChilds(this.devicesFactory.generateEntities());
        }

        return this.structureRoot.getChildByID(clientId).getChilds().stream()
                .map(Hierarchy::getData)
                .collect(Collectors.toList());
    }


    public List<HistoryEntity> loadHistory(long clientId, long deviceId) throws SQLException {
        if (this.structureRoot.getChildByID(clientId).getChildByID(deviceId).getChilds() == null) {
            this.structureRoot.getChildByID(clientId).getChildByID(deviceId)
                    .setChilds(this.historyFactory.generateEntities());
        }

        return this.structureRoot.getChildByID(clientId).getChildByID(deviceId).getChilds().stream()
                .map(Hierarchy::getData)
                .collect(Collectors.toList());
    }

    public List<CostEntity> loadCosts(long clientId, 
        long deviceId, long historyId) throws SQLException {

        if (this.structureRoot
            .getChildByID(clientId)
            .getChildByID(deviceId)
            .getChildByID(historyId)
            .getChilds() == null) {

            this.structureRoot.getChildByID(clientId).getChildByID(deviceId).getChildByID(historyId)
                    .setChilds(this.costsFactory.generateEntities());
        }

        return this.structureRoot
            .getChildByID(clientId)
            .getChildByID(deviceId)
            .getChildByID(historyId)
            .getChilds().stream()
                .map(Hierarchy::getData)
                .collect(Collectors.toList());
    }
}