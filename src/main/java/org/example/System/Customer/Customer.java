package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;
import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private InsuranceCard insuranceCard;
    private ArrayList<Claim> claims;

    // Constructors
    public Customer() {
        this.id = "default";
        this.fullName = "default";
        this.email = "default";
        this.phoneNumber = "default";
        this.address = "default";
        this.insuranceCard = null;
        this.claims = new ArrayList<>();
    }

    public Customer(String id, String fullName, String email, String phoneNumber, String address, InsuranceCard insuranceCard, ArrayList<Claim> claims) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public ArrayList<Claim> getClaims() {
        return claims;
    }

    public void setClaims(ArrayList<Claim> claims) {
        this.claims = claims;
    }
}
