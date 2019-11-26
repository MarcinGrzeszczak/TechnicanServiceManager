package mg.backend.datastructure;

import java.util.List;
import java.util.stream.Collectors;

import mg.backend.entities.Entity;

public class Hierarchy<T extends Entity,E extends Hierarchy<?,?>> {
    private T data;
    private List<E> childs;

    public Hierarchy(T data, List<E> childs) {
        this.data = data;
        this.childs = childs;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public List<E> getChilds() {
        return childs;
    }

    public void setChilds(List<E> childs) {
        this.childs = childs;
    }

    public void removeChildById(long id) {
        this.setChilds(this.getChilds().stream().filter(entity-> entity.getData().getId() != id).collect(Collectors.toList()));
    }

    public void removeChildByParentId(long parentId) {
        this.setChilds(this.getChilds().stream().filter(entity-> entity.getData().getParentId() != parentId).collect(Collectors.toList()));
    }

    public E getChildByID(long id) {
        List<E> result = childs.stream()
                .filter((child) -> child.getData().getId() == id)
                .collect(Collectors.toList());
        
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
