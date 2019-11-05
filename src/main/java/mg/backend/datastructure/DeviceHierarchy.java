package mg.backend.datastructure;

import mg.backend.entities.DeviceEntity;

import java.util.List;

public class DeviceHierarchy extends Hierarchy<DeviceEntity, HistoryHierarchy> {

    public DeviceHierarchy(DeviceEntity data, List<HistoryHierarchy> childs) {
        super(data, childs);
    }
}
