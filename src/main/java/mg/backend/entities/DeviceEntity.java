package mg.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class DeviceEntity extends Entity {

    private String serialNumber;

    public DeviceEntity() {
        super();
        String emptySerialNumber = "Empty serialnumber";
        this.init(emptySerialNumber);
    }

    private DeviceEntity(long id, long parentId, 
        String name, String serialNumber, String description) {

        super(id, parentId, name, description);
        this.init(serialNumber);
    }

    private void init(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public static Builder builder() {
        return new DeviceEntity.Builder();
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public List<String> show() {
        
        List<String> result = new ArrayList<>();

        result.add(String.valueOf(this.id)); 
        result.add(name); 
        result.add(serialNumber);
        result.add(description);

        return result;
    }

    public static class Builder {
        private long id; 
        private long parentId;
        private String name; 
        private String serialNumber;
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

        public Builder setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public DeviceEntity build() {
            return new DeviceEntity(this.id, this.parentId, 
                this.name, this.serialNumber, this.description);
        }
    }

}