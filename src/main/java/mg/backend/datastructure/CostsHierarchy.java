package mg.backend.datastructure;

import mg.backend.entities.CostEntity;

public class CostsHierarchy extends Hierarchy<CostEntity, Hierarchy<?,?>> {

    public CostsHierarchy(CostEntity data) {
        super(data, null);
    }

    @Override
    public void removeChildById(long id) {

    }

    @Override
    public void removeChildByParentId(long parentId) {

    }
}
