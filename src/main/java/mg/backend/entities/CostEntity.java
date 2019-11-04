package mg.backend.entities;

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
    public String show() {
        // TODO Auto-generated method stub
        return null;
    }

    public static class Builder {
        private long id;
        private long parentId;  
        private String name; 
        private double price; 
        private double vat;
        private double discount; 
        private String description;

        public void setId(long id) {
            this.id = id;
        }

        public void setParentId(long pid) {
            this.parentId = pid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setVat(double vat) {
            this.vat = vat;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public CostEntity build() {
            return new CostEntity(this.id, this.parentId, this.name,  this.price, 
                 this.vat, this.discount, this.description);
        }
    }
}