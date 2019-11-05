package mg.backend.datastructure;

import mg.backend.entities.HistoryEntity;

import java.util.List;

public class HistoryHierarchy extends Hierarchy<HistoryEntity, CostsHierarchy> {

    public HistoryHierarchy(HistoryEntity data, List<CostsHierarchy> childs) {
        super(data, childs);
    }
}
