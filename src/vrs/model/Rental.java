package vrs.model;

import java.io.Serializable;

public class Rental implements Serializable {
    private Vehicle vehicle;
    private Customer customer;
    private String rentalPeriod;
    private double totalCost;

    public Rental(Vehicle vehicle, Customer customer, String rentalPeriod, double totalCost) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalPeriod = rentalPeriod;
        this.totalCost = totalCost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "vehicle=" + vehicle +
                ", customer=" + customer +
                ", rentalPeriod='" + rentalPeriod + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }

    public String getInfo() {
        return String.format("Vehicle: %s, Customer: %s, Period: %s, Total Cost: $%.2f",
                vehicle.getInfo(), customer.getInfo(), rentalPeriod, totalCost);
    }
}
