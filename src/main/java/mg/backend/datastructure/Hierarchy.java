package mg.backend.datastructure;

import mg.backend.entities.Entity;

import java.util.List;
import java.util.stream.Collectors;

public class Hierarchy<T extends Entity,E extends Hierarchy> {
    private T data;
    private List<E> childs;

    public Hierarchy(T data, List<E> childs) {
        this.data = data;
        this.childs = childs;
    }

    public T getData() {
        return data;
    }

    public List<E> getChilds() {
        return childs;
    }

    public void setChilds(List<E> childs) {
        this.childs = childs;
    }

    public E getChildByID(long id) {
        List<E> result = childs.stream()
                .filter((child) -> child.getData().getId() == id)
                .collect(Collectors.toList());
        if(result.isEmpty())
            return null;

        return result.get(0);
    }
}
