package mg.backend.tables;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mg.backend.database.DatabaseContract;
import mg.backend.entities.PersonEntity;


public class PersonTable<T> implements DatabaseContract {
    
    private T entity;

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) {
        
    }

}
/*
public class PersonTable implements DatabaseContract {

    private List<PersonEntity> personList;

    public PersonTable() {
        this.personList = new ArrayList<>();
    }

    public List<PersonEntity> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonEntity> personList) {
        this.personList = personList;
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) {
        personList.add(PersonEntity.builder()
                .setId(0)
                .setName("aaa")
                .setDescription("test")
                .setAddress("Address")
                .build());
    }
    
}
*/