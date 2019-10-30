package mg.backend;

import java.text.SimpleDateFormat;
import java.util.Date;

class HistoryEvent extends Entity {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Date acceptanceDate;
    private Date dueDate;
    private String description;
    private String issue;

   
    public HistoryEvent() throws Exception {
        super();
        Date emptyAcceptanceDate = dateFormat.parse("1970-01-01");
        Date emptyDueDate = dateFormat.parse("1970-01-01"); 
        String emptyDescription = "empty description"; 
        String emptyIssue = "empty issue";
        this.init(emptyAcceptanceDate, emptyDueDate, emptyDescription, emptyIssue);
    }

    public HistoryEvent(long id, String name, Date acceptanceDate, 
        Date dueDate, String description, String issue) {

        super(id, name);
        this.init(acceptanceDate, dueDate, description, issue);
    }

    private void init(Date acceptanceDate, Date dueDate, String description, String issue) {
        this.acceptanceDate = acceptanceDate;
        this.dueDate = dueDate;
        this.description = description;
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

    public HistoryEvent acceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
        return this;
    }

    public HistoryEvent dueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    @Override
    public void load() {
        // TODO Auto-generated method stub

    }

    @Override
    String show() {
        // TODO Auto-generated method stub
        return null;
    }

}