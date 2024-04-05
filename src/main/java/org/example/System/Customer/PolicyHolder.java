package org.example.System.Customer;

/**
 * @author <Dao Bao Duy - s3978826>
 *     Adapted from: chatGPT, w3schools
 */

import org.example.System.Claim;
import org.example.System.InsuranceCard;
import java.util.ArrayList;


public class PolicyHolder extends Customer {
    private ArrayList<String> dependents;
    public PolicyHolder() {
        super();
        dependents = null;
    }

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard, ArrayList<Claim> claims, ArrayList<String> dependents) {
        super(id, fullName, insuranceCard, claims);
        this.dependents = dependents;
    }

    public ArrayList<String> getDependents() {
        return dependents;
    }

    public void setDependents(ArrayList<String> dependents) {
        this.dependents = dependents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("PolicyHolder{")
                .append("id='").append(getId()).append('\'')
                .append(", fullName='").append(getFullName()).append('\'')
                .append(", insuranceCard=").append(
                        getInsuranceCard() == null ? "null" : getInsuranceCard().getCardNumber()
                );

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

        // Remove the trailing comma and space if there are dependent claims
        if (dependents.isEmpty()) {
            stringBuilder.append(", dependents list=null");
        } else {
            stringBuilder.append("\n   dependents list=[");
            for (String dependent : dependents) {
                stringBuilder.append(dependent).append(", ");
            }
            stringBuilder.append(']');
        }


        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
