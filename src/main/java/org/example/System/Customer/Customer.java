package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;
    private ArrayList<Claim> claims;
    public Customer() {
        id = "default";
        fullName = "default";
        insuranceCard = null;
        claims = null;
    }

    public Customer(String id, String fullName, InsuranceCard insuranceCard, ArrayList<Claim> claims) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = claims;
    }

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
