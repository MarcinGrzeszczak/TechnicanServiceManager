package mg.backend.tables;

import java.util.ArrayList;
import java.util.List;

import mg.backend.database.DatabaseContract;
import mg.backend.entities.PersonEntity;

public class PersonTable implements DatabaseContract {

    private List<PersonEntity> personList;

    public PersonTable() {
        this.personList = new ArrayList<>();
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize() {
        personList.add(PersonEntity.builder()
                .setId(0)
                .setName("aaa")
                .setDescription("test")
                .setAddress("Address")
                .build());
    }
    
}