package mg.backend;

class Cost extends Entity {

    private double price;
    private double discount;
    private double vat;


    public Cost() {
        super();
        double initPrice = 0;
        double initVat = 23;
        double initDiscount = 0;
        this.init(initPrice, initVat, initDiscount);
    }

    public Cost(long id, String name, double price, 
        double vat, double discount, String description) {

        super(id, name, description);
        this.init(price, vat, discount);
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