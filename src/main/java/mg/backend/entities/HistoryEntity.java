package mg.backend.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryEntity extends Entity {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date acceptanceDate;
    private Date dueDate;

    public HistoryEntity() {
        super();
        try {
            Date emptyAcceptanceDate = dateFormat.parse("1970-01-01");
            Date emptyDueDate = dateFormat.parse("1970-01-01");
            this.init(emptyAcceptanceDate, emptyDueDate);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
     
    }

    private HistoryEntity(long id, long parentId, String name, Date acceptanceDate, 
        Date dueDate, String description) {

        super(id, parentId, name, description);
        this.init(acceptanceDate, dueDate);
    }

    private void init(Date acceptanceDate, Date dueDate) {
        this.acceptanceDate = acceptanceDate;
        this.dueDate = dueDate;
    }

    public static Builder builder() {
        return new HistoryEntity.Builder();
    }

    public Date getAcceptanceDate() {
        return this.acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public List<String> show() {
        List<String> result = new ArrayList<>();
        
        result.add(String.valueOf(this.id)); 
        result.add(this.name); 
        result.add(acceptanceDate.toString());
        result.add(dueDate.toString()); 
        result.add(description); 

        return result;
    }

    public static class Builder {
        private long id; 
        private long parentId; 
        private String name; 
        private Date acceptanceDate;
        private Date dueDate; 
        private String description; 

        public Builder setId(long id) {
            this.id = id;
            return this;
        }
        
        public Builder setParentId(long parentId) {
            this.parentId = parentId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this; 
        }

        public Builder setAcceptanceDate(Date acceptanceDate) {
            this.acceptanceDate = acceptanceDate;
            return this; 
        }

        public Builder setDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this; 
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this; 
        }

        public HistoryEntity build() {
            return new HistoryEntity(this.id, this.parentId, this.name, this.acceptanceDate, 
                this.dueDate, this.description);
        }

    }
}