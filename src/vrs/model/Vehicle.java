package vrs.model;

import java.io.Serial;
import java.io.Serializable;

public class Vehicle implements Serializable {
    private String id;
    private String type;
    private String brand;
    private String model;
    private double rentalPrice;
    private String available;

    public Vehicle(String id, String type, String brand, String model,String available) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        if (type.equals("car"))rentalPrice = 50;
        else if (type.equals("bike")) rentalPrice = 10;
        else rentalPrice = 80;
        this.available = available;
    }
    public Vehicle( String type, String brand, String model) {
        this.id = "";
        this.type = type;
        this.brand = brand;
        this.model = model;
        if (type.equals("car"))rentalPrice = 50;
        else if (type.equals("bike")) rentalPrice = 10;
        else rentalPrice = 80;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getInfo() {
        return String.format("ID: %s, Type: %s, Brand: %s, Model: %s, Rental Price: $%.2f, Available: %s",
                id, type, brand, model, rentalPrice, available);
    }
    public void updateDetails(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
}
