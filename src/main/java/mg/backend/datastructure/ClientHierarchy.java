package mg.backend.datastructure;

import mg.backend.entities.ClientEntity;

import java.util.List;

public class ClientHierarchy extends Hierarchy<ClientEntity, DeviceHierarchy> {
    public ClientHierarchy(ClientEntity data, List<DeviceHierarchy> childs) {
        super(data, childs);
    }
}
