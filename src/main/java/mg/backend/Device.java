package mg.backend;

import java.util.ArrayList;
import java.util.List;

class Device extends Entity {

    private String serialNumber;
    private List<HistoryEvent> history;

    Device() {
        super();
        String emptySerialNumber = "Empty serialnumber";
        this.init(emptySerialNumber);
    }

    Device(long id, String name, String serialNumber) {
        super(id, name);
        this.init(serialNumber);
    }

    private void init(String serialNumber) {
        this.serialNumber = serialNumber;
        this.history = new ArrayList<>();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<HistoryEvent> getHistory() {
        return history;
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