package mg.backend.datastructure;

import java.util.List;
import mg.backend.entities.ClientEntity;

public class ClientHierarchy extends Hierarchy<ClientEntity, DeviceHierarchy> {
    public ClientHierarchy(ClientEntity data, List<DeviceHierarchy> childs) {
        super(data, childs);
    }
}
