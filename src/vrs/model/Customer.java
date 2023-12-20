package vrs.model;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String contactInfo;
    private String licenseNumber;

    public Customer(String name, String contactInfo, String licenseNumber) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String toString() {
        return "Name: " + name + ", Contact Info: " + contactInfo + ", License Number: " + licenseNumber;
    }

    public String getInfo() {
        return String.format("Name: %s, Contact: %s, License: %s", name, contactInfo, licenseNumber);
    }

    public void updateDetails(String contactInfo, String licenseNumber) {
        this.contactInfo = contactInfo;
        this.licenseNumber = licenseNumber;
    }
}
