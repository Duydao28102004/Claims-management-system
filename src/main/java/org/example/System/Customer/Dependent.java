package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.util.ArrayList;

public class Dependent extends Customer {
    private String policyHolder;
    public Dependent() {
        super();
        policyHolder = "null";
    }

    public Dependent(String id, String fullName, InsuranceCard insuranceCard, ArrayList<Claim> claims, String policyHolder) {
        super(id, fullName, insuranceCard, claims);
        this.policyHolder = policyHolder;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Dependent{")
                .append("id='").append(getId()).append('\'')
                .append(", fullName='").append(getFullName()).append('\'')
                .append(", insuranceCard=").append(
                        getInsuranceCard() == null ? "null" : getInsuranceCard().getCardNumber()
                );
        if (policyHolder != null) {
            stringBuilder.append(", policyHolder=").append(policyHolder);
        } else {
            stringBuilder.append(", policyHolder=null");
        }
        // Append IDs of claims
        stringBuilder.append(", claims=[");
        for (Claim claim : getClaims()) {
            stringBuilder.append(claim.getId()).append(" - ").append(claim.getStatus()).append(", ");
        }
        // Remove the trailing comma and space if there are claims
        if (!getClaims().isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append("]");

        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
