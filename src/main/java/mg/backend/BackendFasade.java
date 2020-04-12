package mg.backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mg.backend.database.DatabaseConnection;
import mg.backend.datastructure.*;
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

    private Long clientId;
    private Long deviceId;
    private Long historyId;
    private Long costsId;
    
    public BackendFasade() throws IOException, SQLException {

        this.initConnection();
        this.initIds();

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

    private void initIds() {
        this.clientId = null;
        this.deviceId = null;
        this.historyId = null;
        this.costsId = null;
    }

    public List<List<String>> loadAllClients() throws SQLException {
        if (this.structureRoot.getChilds() == null) {
            this.structureRoot
                    .setChilds(this.clientsFactory.generateEntities(null));
        }

        return this.structureRoot.getChilds().stream()
                .map((element) -> element.getData().show())
                .collect(Collectors.toList());
    }

    public List<List<String>> loadDevices() throws SQLException {
        ClientHierarchy clientHier = this.structureRoot.getChildByID(clientId);
        if (clientHier.getChilds() == null) {
            clientHier.setChilds(this.devicesFactory.generateEntities(this.clientId));
        }

        return clientHier.getChilds().stream()
                .map(element -> element.getData().show())
                .collect(Collectors.toList());
    }


    public List<List<String>> loadHistory() throws SQLException {

        DeviceHierarchy deviceHier = this.structureRoot
            .getChildByID(clientId)
            .getChildByID(deviceId);

        if (deviceHier.getChilds() == null) {
            deviceHier.setChilds(this.historyFactory.generateEntities(this.deviceId));
        }

        return deviceHier.getChilds().stream()
                .map(element -> element.getData().show())
                .collect(Collectors.toList());
    }

    public List<List<String>> loadCosts() throws SQLException {

        HistoryHierarchy historyHier = this.structureRoot
            .getChildByID(this.clientId)
            .getChildByID(this.deviceId)
            .getChildByID(this.historyId);

        if (historyHier.getChilds() == null) {
            historyHier.setChilds(this.costsFactory.generateEntities(this.historyId));
        }

        return historyHier.getChilds().stream()
            .map(element -> element.getData().show())
            .collect(Collectors.toList());
    }

    public void addClient(Map<String, String> data) throws SQLException {
        this.clientsFactory.getEntityFactory().deserializeMap(data);
        if(clientId != null)
            this.structureRoot.getChildByID(clientId).setData(this.clientsFactory.getEntityFactory().getEntity());
        else
            this.structureRoot.getChilds().add(this.clientsFactory.getEntityFactory().getHierarchy());

        this.clientsFactory.saveEntity(clientId);
    }

    public Map<String, String> getClientMap() {
        if (this.clientId == null) {
            this.clientsFactory.getEntityFactory().createEmpty();
        }
        else
            this.clientsFactory.getEntityFactory().setEntity(this.structureRoot.getChildByID(clientId).getData());

        return this.clientsFactory.getEntityFactory().serialize();
    }

    public void addDevice(Map<String, String> data) throws SQLException {
        this.devicesFactory.getEntityFactory().deserializeMap(data);
        if(this.deviceId != null)
            this.structureRoot.getChildByID(clientId).getChildByID(deviceId).setData(this.devicesFactory.getEntityFactory().getEntity());
        else
            this.structureRoot.getChildByID(this.clientId).getChilds()
                .add(this.devicesFactory.getEntityFactory().getHierarchy());
        
        this.devicesFactory.saveEntity(this.deviceId);
    }

    public Map<String, String> getDeviceMap() {
        if (this.deviceId == null) {
            this.devicesFactory.getEntityFactory().createEmpty();
        }
        else
            this.devicesFactory.getEntityFactory().setEntity(this.structureRoot
                    .getChildByID(clientId)
                    .getChildByID(deviceId)
                    .getData());

        return this.devicesFactory.getEntityFactory().serialize();
    }

    public void addHistory(Map<String, String> data) throws SQLException {
        this.historyFactory.getEntityFactory().deserializeMap(data);

        if(this.historyId != null)
            this.structureRoot.getChildByID(this.clientId).getChildByID(this.deviceId).getChildByID(historyId)
                .setData(this.historyFactory.getEntityFactory().getEntity());
        else
            this.structureRoot.getChildByID(this.clientId).getChildByID(this.deviceId).getChilds()
                .add(this.historyFactory.getEntityFactory().getHierarchy());
        
        this.historyFactory.saveEntity(this.historyId);
    }

    public Map<String, String> getHistoryMap() {
        if (this.historyId == null) {
            this.historyFactory.getEntityFactory().createEmpty();
        }
        else
            this.historyFactory.getEntityFactory().setEntity(this.structureRoot
                    .getChildByID(clientId)
                    .getChildByID(deviceId)
                    .getChildByID(historyId)
                    .getData());

        return this.historyFactory.getEntityFactory().serialize();
    }

    public void addCost(Map<String, String> data) throws SQLException {
        this.costsFactory.getEntityFactory().deserializeMap(data);

        if(this.costsId != null)
            this.structureRoot.getChildByID(this.clientId)
                    .getChildByID(this.deviceId).getChildByID(this.historyId).getChildByID(costsId)
                    .setData(this.costsFactory.getEntityFactory().getEntity());
        else
            this.structureRoot.getChildByID(this.clientId)
                .getChildByID(this.deviceId).getChildByID(this.historyId)
                .getChilds().add(this.costsFactory.getEntityFactory().getHierarchy());
        
        this.costsFactory.saveEntity(this.costsId);
    }

    public Map<String, String> getCostMap() {
        if (this.costsId == null) {
            this.costsFactory.getEntityFactory().createEmpty();
        }
        else
            this.costsFactory.getEntityFactory().setEntity(this.structureRoot
                .getChildByID(clientId)
                .getChildByID(deviceId)
                .getChildByID(historyId)
                .getChildByID(costsId)
                .getData());
    
        return this.costsFactory.getEntityFactory().serialize();
    }

    public void deleteClient(long id){
        this.clientsFactory.deleteEntity(id);

        this.structureRoot.removeChildById(id);
    }
    public void deleteDevice(long id){
        this.devicesFactory.deleteEntity(id);
        this.structureRoot.getChildByID(clientId).removeChildById(id);
    }
    public void deleteHistory(long id){
        this.historyFactory.deleteEntity(id);
        this.structureRoot.getChildByID(clientId).getChildByID(deviceId).removeChildById(id);
    }
    public void deleteCost(long id){
        this.costsFactory.deleteEntity(id);
        this.structureRoot.getChildByID(clientId).getChildByID(deviceId).getChildByID(historyId).removeChildById(id);
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public void setCostsId(Long costsId) {
        this.costsId = costsId;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public Long getCostsId() {
        return costsId;
    }
}