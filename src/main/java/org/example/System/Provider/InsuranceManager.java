package org.example.System.Provider;

import org.example.System.ClaimStatusUpdater;

import java.util.ArrayList;

public class InsuranceManager extends Provider{

    private ArrayList<String> insuranceSurveyors;

    public InsuranceManager() {
        super();
        insuranceSurveyors = null;
    }

    public InsuranceManager(String id, String fullName, Number phone, String address, String email, String password, ArrayList<String> insuranceSurveyors) {
        super(id, fullName, phone, address, email, password);
        this.insuranceSurveyors = insuranceSurveyors;
    }

    public ArrayList<String> getInsuranceSurveyors() {
        return insuranceSurveyors;
    }

    public void setInsuranceSurveyors(ArrayList<String> insuranceSurveyors) {
        this.insuranceSurveyors = insuranceSurveyors;
    }

    public void receiveClaimProposal(ClaimStatusUpdater claim) {
        // Implementation to receive claim proposal
    }

    public void approveClaim(ClaimStatusUpdater claim) {
        claim.approveClaim();
    }

    public void rejectClaim(ClaimStatusUpdater claim) {
        claim.rejectClaim();
    }
}
