package mg.backend.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

class HistoryEntity extends Entity {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Date acceptanceDate;
    private Date dueDate;
    private String issue;
   
    public HistoryEntity() throws Exception {
        super();
        Date emptyAcceptanceDate = dateFormat.parse("1970-01-01");
        Date emptyDueDate = dateFormat.parse("1970-01-01"); 
        String emptyIssue = "empty issue";
        this.init(emptyAcceptanceDate, emptyDueDate, emptyIssue);
    }

    public HistoryEntity(long id, String name, Date acceptanceDate, 
        Date dueDate, String description, String issue) {

        super(id, name, description);
        this.init(acceptanceDate, dueDate, issue);
    }

    private void init(Date acceptanceDate, Date dueDate, String issue) {
        this.acceptanceDate = acceptanceDate;
        this.dueDate = dueDate;
        this.issue = issue;
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

    public String getIssue() {
        return this.issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    String show() {
        // TODO Auto-generated method stub
        return null;
    }

}