package vrs;

import vrs.service.VehicleRentalService;

import java.util.Scanner;

public class Main {
    // Main method for console-based UI
    public static void main(String[] args) {

        // Create a DataContainer instance with your existing data
        DataContainer existingDataContainer = new DataContainer(/* initialize with your data */);

        // Create a VehicleRentalService instance with the existing DataContainer
        VehicleRentalService rentalService = new VehicleRentalService(
                existingDataContainer.getVehicles(),
                existingDataContainer.getCustomers(),
                existingDataContainer.getRentals());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Vehicle");
            System.out.println("2. Update Vehicle Details");
            System.out.println("3. Delete Vehicle");
            System.out.println("4. Register Customer");
            System.out.println("5. Update Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. Rent a vehicle");
            System.out.println("8. Return a vehicle");
            System.out.println("9. Display inventory");
            System.out.println("10. Display customers");
            System.out.println("11. Display rentals");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            }catch (Exception ex){
                rentalService.displayInventory();
            }
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle Type (car, bike, van): ");
                    String addType = scanner.nextLine();
                    System.out.print("Enter Vehicle Brand: ");
                    String addBrand = scanner.nextLine();
                    System.out.print("Enter Vehicle Model: ");
                    String addModel = scanner.nextLine();
                    rentalService.addVehicle(addType, addBrand, addModel);
                    break;
                case 2:
                    System.out.print("Enter Vehicle ID to update details: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter new Brand: ");
                    String updateBrand = scanner.nextLine();
                    System.out.print("Enter new Model: ");
                    String updateModel = scanner.nextLine();
                    rentalService.updateVehicleDetails(updateId, updateBrand, updateModel);
                    break;
                case 3:
                    System.out.print("Enter Vehicle ID to delete: ");
                    String deleteId = scanner.nextLine();
                    rentalService.deleteVehicle(deleteId);
                    break;
                case 4:
                    System.out.print("Enter Customer Name (length > 2): ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Information (length >= 8): ");
                    String contactInfo = scanner.nextLine();
                    System.out.print("Enter License Number (length >= 4): ");
                    String licenseNumber = scanner.nextLine();
                    rentalService.addCustomer(name, contactInfo, licenseNumber);
                    break;
                case 5:
                    System.out.print("Enter Customer License Number to update details: ");
                    String licenseNumber2 = scanner.nextLine();
                    System.out.print("Enter Customer Contact Information to update details: ");
                    String contactInfo2 = scanner.nextLine();
                    rentalService.updateCustomerDetails(licenseNumber2, contactInfo2);
                    break;
                case 6:
                    System.out.print("Enter Customer License Number to delete: ");
                    String licenseNumber3 = scanner.nextLine();
                    rentalService.deleteCustomer(licenseNumber3);
                    break;
                case 7:
                    rentalService.displayInventory();
                    System.out.print("Enter vehicle ID to rent: ");
                    String rentVehicleId = scanner.nextLine();
                    System.out.print("Enter customer license number: ");
                    String rentLicenseNumber = scanner.nextLine();
                    System.out.print("Enter rental period (in days): ");
                    String rentalPeriod = scanner.nextLine();
                    rentalService.rentVehicle(rentVehicleId, rentLicenseNumber, rentalPeriod);
                    rentalService.saveDataToFile(existingDataContainer);
                    break;
                case 8:
                    System.out.print("Enter vehicle ID to return: ");
                    String returnVehicleId = scanner.nextLine();
                    rentalService.returnVehicle(returnVehicleId);
                    break;
                case 9:
                    rentalService.displayInventory();
                    break;
                case 10:
                    rentalService.displayCustomers();
                    break;
                case 11:
                    rentalService.displayRentals();
                    break;

                case 12:
                    rentalService.saveDataToFile(existingDataContainer);
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
