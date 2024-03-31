package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.util.ArrayList;

public class Dependent extends Customer {
    public Dependent() {
        super();
    }

    public Dependent(String id, String fullName, InsuranceCard insuranceCard, ArrayList<Claim> claims) {
        super(id, fullName, insuranceCard, claims);
    }


    @Override
    public String toString() {
        return "Dependent{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", claims=" + getClaims() +
                '}';
    }
}
