package mg.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class CostEntity extends Entity {
    
    private double price;
    private double discount;
    private double vat;

    public CostEntity() {
        super();
        double initPrice = 0;
        double initVat = 23;
        double initDiscount = 0;
        this.init(initPrice, initVat, initDiscount);
    }

    private CostEntity(long id, long parentId, String name, double price, 
        double vat, double discount, String description) {
             
        super(id, parentId, name, description);
        this.init(price, vat, discount);
    }

    public static Builder builder() {
        return new CostEntity.Builder();
    }

    private void init(double price, double vat, double discount) {
        this.price = price;
        this.vat = vat;
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVat() {
        return this.vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double bruttPrice() {
        return 0;
    }

    @Override
    public List<String> show() {
        List<String> result = new ArrayList<>();

        result.add(String.valueOf(this.id));
        result.add(this.name);
        result.add(String.valueOf(this.price));
        result.add(String.valueOf(this.vat));
        result.add(String.valueOf(this.discount));
        result.add(this.description);

        return result;
    }

    public static class Builder {
        private long id;
        private long parentId;  
        private String name; 
        private double price; 
        private double vat;
        private double discount; 
        private String description;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setParentId(long pid) {
            this.parentId = pid;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setVat(double vat) {
            this.vat = vat;
            return this;
        }

        public Builder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CostEntity build() {
            return new CostEntity(this.id, this.parentId, this.name,  this.price, 
                 this.vat, this.discount, this.description);
        }
    }
}