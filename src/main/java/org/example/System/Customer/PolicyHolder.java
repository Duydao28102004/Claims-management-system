package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.util.ArrayList;


public class PolicyHolder extends Customer {
    private ArrayList<Dependent> dependents;
    public PolicyHolder() {
        super();
        dependents = null;
    }

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard, ArrayList<Claim> claims, ArrayList<Dependent> dependents) {
        super(id, fullName, insuranceCard, claims);
        this.dependents = dependents;
    }

    public ArrayList<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(ArrayList<Dependent> dependents) {
        this.dependents = dependents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("PolicyHolder{")
                .append("id='").append(getId()).append('\'')
                .append(", fullName='").append(getFullName()).append('\'')
                .append(", insuranceCard=").append(getInsuranceCard());

        // Append IDs of claims
        stringBuilder.append(", claims=[");
        for (Claim claim : getClaims()) {
            stringBuilder.append(claim.getId()).append(" - ").append(claim.getStatus()).append(", ");
        }
        // Remove the trailing comma and space if there are claims
        if (!getClaims().isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(']');

        // Append IDs of dependent claims
        stringBuilder.append(", dependentClaims=[");
        for (Dependent dependent : dependents) {
            for (Claim claim : dependent.getClaims()) {
                stringBuilder.append(claim.getId()).append(", ");
            }
        }
        // Remove the trailing comma and space if there are dependent claims
        if (!dependents.isEmpty() && !dependents.get(0).getClaims().isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(']');

        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
