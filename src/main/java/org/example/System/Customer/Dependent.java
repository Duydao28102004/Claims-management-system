package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.util.List;

public class Dependent extends Customer {
    private PolicyHolder policyHolder;
    public Dependent() {
        super();
        policyHolder = null;
    }

    public Dependent(String id, String fullName, InsuranceCard insuranceCard, List<Claim> claims, PolicyHolder policyHolder) {
        super(id, fullName, insuranceCard, claims);
        this.policyHolder = policyHolder;
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", claims=" + getClaims() +
                ", policyHolder=" + policyHolder +
                '}';
    }
}
