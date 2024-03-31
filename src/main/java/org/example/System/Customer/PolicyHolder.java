package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;
    public PolicyHolder() {
        super();
        dependents = null;
    }

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard, List<Claim> claims, List<Dependent> dependents) {
        super(id, fullName, insuranceCard, claims);
        this.dependents = dependents;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", claims=" + getClaims() +
                ", dependents=" + dependents +
                '}';
    }
}
