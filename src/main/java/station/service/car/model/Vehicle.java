package station.service.car.model;

import java.util.Date;

public class Vehicle {
    private int id;
    private String brand;
    private String engine;
    private String color;
    private Date productionYear;
    private String gearBox;
    private String registrationNumber;
    private String model;
    private Date review;

    public Vehicle() {
    }

    public Vehicle(String brand, String engine, String color, Date productionYear, String gearBox, String registrationNumber, String model, Date review) {
        this.brand = brand;
        this.engine = engine;
        this.color = color;
        this.productionYear = productionYear;
        this.gearBox = gearBox;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.review = review;
    }

    public Vehicle(int id, String brand, String engine, String color, Date productionYear, String gearBox, String registrationNumber, String model, Date review) {
        this.id = id;
        this.brand = brand;
        this.engine = engine;
        this.color = color;
        this.productionYear = productionYear;
        this.gearBox = gearBox;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.review = review;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", engine='" + engine + '\'' +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                ", gearBox='" + gearBox + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", model='" + model + '\'' +
                ", review=" + review +
                '}';
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Date productionYear) {
        this.productionYear = productionYear;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getReview() {
        return review;
    }

    public void setReview(Date review) {
        this.review = review;
    }
}
