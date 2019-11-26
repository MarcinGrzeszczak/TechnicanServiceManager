package mg.backend.datastructure;

import java.util.List;
import java.util.stream.Collectors;

import mg.backend.entities.HistoryEntity;

public class HistoryHierarchy extends Hierarchy<HistoryEntity, CostsHierarchy> {

    public HistoryHierarchy(HistoryEntity data, List<CostsHierarchy> childs) {
        super(data, childs);
    }

}
