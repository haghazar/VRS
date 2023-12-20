package vrs;

import vrs.model.Customer;
import vrs.model.Rental;
import vrs.model.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable {
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Rental> rentals;

    public DataContainer() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
