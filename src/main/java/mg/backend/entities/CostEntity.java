package mg.backend.entities;

import java.sql.ResultSet;

public class CostEntity extends Entity {
    private static final String TABLE_NAME = "costs";

    private double price;
    private double discount;
    private double vat;

    public CostEntity() {
        super(TABLE_NAME);
        double initPrice = 0;
        double initVat = 23;
        double initDiscount = 0;
        this.init(initPrice, initVat, initDiscount);
    }

    public CostEntity(long id, String name, double price, 
        double vat, double discount, String description) {
             
        super(TABLE_NAME, id, name, description);
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
    String show() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void serialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deserialize(ResultSet data) {
        // TODO Auto-generated method stub

    }

}