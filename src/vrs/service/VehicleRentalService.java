package vrs.service;

import vrs.DataContainer;
import vrs.model.Customer;
import vrs.model.Rental;
import vrs.model.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VehicleRentalService implements Serializable {
    private static final long serialVersionUID = 3211400579197454753L;
    protected static final String DATA_FILE = "src/resources/vehicle_rental_data.ser";
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Rental> rentals;


    public VehicleRentalService(List<Vehicle> vehicles,List<Customer> customers, List<Rental> rentals) {
        this.vehicles = vehicles;
        this.customers = customers;
        this.rentals = rentals;
        loadDataFromFile();
    }

    public void addVehicle(String type, String brand, String model) {
        if (isTypeValid(type) && isBrandValid(brand) && isModelValid(model)) {
            String id;
            if (vehicles.isEmpty()) {
                id = "1";
            } else {
                id = String.valueOf(Integer.parseInt(vehicles.get(vehicles.size() - 1).getId()) + 1);

            }
            Vehicle newVehicle = new Vehicle(id, type, brand, model,"Yes");
            vehicles.add(newVehicle);
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Invalid input for adding a vehicle.");
        }
    }

    // Check if type is valid
    public boolean isTypeValid(String type) {
        if (type != null && (type.equals("car") || type.equals("bike") || type.equals("van"))) {
            return true;
        }
        return false;
    }

    // Check if brand is valid
    public boolean isBrandValid(String brand) {
        if (brand != null && brand.length() > 2) {
            return true;
        }
        return false;
    }

    // Check if model is valid
    public boolean isModelValid(String model) {
        if (model != null && model.length() > 2) {
            return true;
        }
        return false;
    }

    public void updateVehicleDetails(String vehicleId, String brand, String model) {
        Vehicle vehicle = getVehicleById(vehicleId);
        if (vehicle != null) {
            if (isBrandValid(brand) && isModelValid(model)) {
                vehicle.updateDetails(brand, model);
                System.out.println("Vehicle details updated successfully.");
            } else {
                System.out.println("Invalid input for updating vehicle details.");
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public void deleteVehicle(String vehicleId) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getId().equals(vehicleId)) {
                iterator.remove();
                System.out.println("Vehicle deleted successfully.");
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }

    //Add customer
    public void addCustomer(String name, String contactInfo, String licenseNumber) {
        if (isNameValid(name) && isContactInfoValid(contactInfo) && isLicenseNumberValid(licenseNumber)) {
            Customer customer = new Customer(name, contactInfo, licenseNumber);
            customers.add(customer);
            System.out.println("Customer register successfully.");
        } else {
            System.out.println("Invalid input for adding a vehicle.");
        }
    }

    // Check if name is valid
    public boolean isNameValid(String name) {
        if (name != null && name.length() > 2) {
            return true;
        }
        return false;
    }

    // Check if contact information is valid
    public boolean isContactInfoValid(String contactInfo) {
        if (contactInfo != null && contactInfo.length() >= 8) {
            return true;
        }
        return false;
    }

    // Check if license number is valid
    public boolean isLicenseNumberValid(String licenseNumber) {
        if (licenseNumber != null && licenseNumber.length() >= 4) {
            for (int i = 0; i < customers.size(); i++) {
                if (licenseNumber.equals(customers.get(i).getLicenseNumber())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //Update Customer
    public void updateCustomerDetails(String licenseNumber, String contactInfo) {
        Customer customer = getCustomerByLicenseNumber(licenseNumber);
        if (customer != null) {
            if (isContactInfoValid(contactInfo) && isLicenseNumberValid(licenseNumber)) {
                customer.updateDetails(contactInfo, licenseNumber);
                System.out.println("Customer details updated successfully.");
            } else {
                System.out.println("Invalid input for updating customer details.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    //Delete Customer
    public void deleteCustomer(String licenseNumber) {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getLicenseNumber().equals(licenseNumber)) {
                iterator.remove();
                System.out.println("Customer deleted successfully.");
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }

    public void rentVehicle(String vehicleId, String licenseNumber, String rentalPeriod) {
        Vehicle vehicle = getVehicleById(vehicleId);
        Customer customer = getCustomerByLicenseNumber(licenseNumber);

        if (vehicle != null && customer != null && vehicle.getAvailable().equals("Yes")) {
            vehicle.setAvailable("No");
            double totalCost = calculateTotalCost(vehicle, rentalPeriod);
            Rental rental = new Rental(vehicle, customer, rentalPeriod, totalCost);

            rentals.add(rental);
            System.out.println("Rental successful. Total cost: $" + totalCost);
        } else {
            System.out.println("Invalid vehicle ID, license number or Vehicle is not available.");
        }
    }

    private double calculateTotalCost(Vehicle vehicle, String rentalPeriod) {
        int duration = Integer.parseInt(rentalPeriod);
        return vehicle.getRentalPrice() * duration;
    }

    public void returnVehicle(String vehicleId) {
        for (Rental rental : rentals) {
            if (rental.getVehicle().getId().equals(vehicleId)) {
                rental.getVehicle().setAvailable("Yes");
                System.out.println("Vehicle returned successfully.");
                rentals.remove(rental);
                return;
            }
        }
        System.out.println("Vehicle not found or not rented.");
    }

    public void displayInventory() {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getInfo());
        }
    }

    public void displayCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer.getInfo());
        }
    }

    public void displayRentals() {
        for (Rental rental : rentals) {
            System.out.println(rental.getInfo());
        }
    }

    public void saveDataToFile(DataContainer dataContainer) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(dataContainer);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    //
    public DataContainer loadDataFromFile() {
        DataContainer dataContainer = new DataContainer();
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                DataContainer loadedData = (DataContainer) ois.readObject();

                // Clear existing data
                vehicles.clear();
                customers.clear();
                rentals.clear();

                // Add loaded data to lists
                for (Vehicle vehicle : loadedData.getVehicles()) {
                    vehicles.add(new Vehicle(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(),vehicle.getAvailable()));
                }

                for (Customer customer : loadedData.getCustomers()) {
                    customers.add(new Customer(customer.getName(), customer.getContactInfo(), customer.getLicenseNumber()));
                }

                for (Rental rental : loadedData.getRentals()) {
                    rentals.add(new Rental(rental.getVehicle(),rental.getCustomer(),rental.getRentalPeriod(),rental.getTotalCost()));
                }

                System.out.println("Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data from file: " + e.getMessage());
            }
        }

        return dataContainer;
    }

        private Vehicle getVehicleById (String vehicleId){
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId().equals(vehicleId)) {
                    return vehicle;
                }
            }
            return null;
        }

        private Customer getCustomerByLicenseNumber (String licenseNumber){
            for (Customer customer : customers) {
                if (customer.getLicenseNumber().equals(licenseNumber)) {
                    return customer;
                }
            }
            return null;
        }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
