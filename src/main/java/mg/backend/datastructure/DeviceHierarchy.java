package mg.backend.datastructure;

import java.util.List;

import mg.backend.entities.DeviceEntity;

public class DeviceHierarchy extends Hierarchy<DeviceEntity, HistoryHierarchy> {

    public DeviceHierarchy(DeviceEntity data, List<HistoryHierarchy> childs) {
        super(data, childs);
    }
}
