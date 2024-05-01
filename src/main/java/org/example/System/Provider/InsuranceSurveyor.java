package org.example.System.Provider;

import org.example.System.ClaimStatusUpdater;

public class InsuranceSurveyor extends Provider{

    private String insuranceManager;

    public InsuranceSurveyor() {
        super();
        insuranceManager = "null";
    }

    public InsuranceSurveyor(String id, String fullName, Number phone, String address, String email, String password, String insuranceManager) {
        super(id, fullName, phone, address, email, password);
        this.insuranceManager = insuranceManager;
    }

    public String getManager() {
        return insuranceManager;
    }

    public void setManager(String insuranceManager) {
        this.insuranceManager = insuranceManager;
    }

    public void proposeClaimToManager(ClaimStatusUpdater claim, InsuranceManager manager) {
        claim.proposeClaim();
        manager.receiveClaimProposal(claim);
    }
}
