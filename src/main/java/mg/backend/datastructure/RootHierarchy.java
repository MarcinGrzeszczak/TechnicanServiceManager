package mg.backend.datastructure;

import java.util.List;

import mg.backend.entities.Entity;


public class RootHierarchy extends Hierarchy<Entity, ClientHierarchy> {
    public RootHierarchy(Entity data, List<ClientHierarchy> childs) {
        super(data, childs);
    }
}
