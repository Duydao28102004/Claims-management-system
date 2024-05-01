package org.example.System.Provider;

import org.example.System.Claim;
import org.example.System.ClaimProcessManager;
import org.example.System.Customer.Dependent;


import org.example.System.Customer.PolicyHolder;
import org.example.System.Customer.DependentManager;
import org.example.System.Customer.PolicyHolderManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Provider {

    private String id;
    private String fullName;
    private Number phone;
    private String address;
    private String email;
    private String password;

    public Provider() {
        this.id = "default";
        this.fullName = "default";
        this.phone = null;
        this.address = null;
        this.email = null;
        this.password = null;
    }

    public Provider(String id, String fullName, Number phone, String address, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Number getPhone() {
        return phone;
    }

    public void setPhone(Number phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void printAllClaims(ArrayList<Claim> claims) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManager();
        claimProcessManager.printClaim(claims);
    }

    public void printAllCustomers(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        DependentManager dependentManager = new DependentManager();
        PolicyHolderManager policyHolderManager = new PolicyHolderManager();

        System.out.println("Dependents:");
        dependentManager.printDependent(dependents);

        System.out.println("Policy Holders:");
        policyHolderManager.printPolicyHolder(policyHolders);
    }

    public void printSpecificClaim(ArrayList<Claim> claims) {
        System.out.print("Enter the ID of the claim: ");
        Scanner scanner = new Scanner(System.in);
        String claimId = scanner.nextLine();

        boolean found = false;
        for (Claim claim : claims) {
            if (claim.getId().equalsIgnoreCase(claimId)) {
                System.out.println("Claim found:");
                System.out.println(claim.toString());
                found = true;
                break; // Exit the loop after finding the claim
            }
        }

        if (!found) {
            System.out.println("Claim with ID '" + claimId + "' not found.");
        }
    }

    public void printSpecificCustomer(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        System.out.print("Enter the full name of the customer: ");

        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();

        boolean found = false;
        for (Dependent dependent : dependents) {
            if (dependent.getFullName().equalsIgnoreCase(fullName)) {
                System.out.println("Dependent found:");
                System.out.println(dependent.toString());
                found = true;
                break; // Exit the loop after finding the dependent
            }
        }

        if (!found) {
            for (PolicyHolder policyHolder : policyHolders) {
                if (policyHolder.getFullName().equalsIgnoreCase(fullName)) {
                    System.out.println("Policy holder found:");
                    System.out.println(policyHolder.toString());
                    found = true;
                    break; // Exit the loop after finding the policy holder
                }
            }
        }

        if (!found) {
            System.out.println("Customer with the name '" + fullName + "' not found.");
        }
    }

    public void sortClaimEarliestCreated(ArrayList<Claim> claims) {
        // Sort claims based on claimDate (earliest to latest)
        claims.sort(Comparator.comparing(Claim::getClaimDate).reversed());

        // Print sorted claims
        System.out.println("Claims sorted from earliest created to latest:");
        for (Claim claim : claims) {
            System.out.println(claim.toString());
        }
    }

    public void sortClaimLatestCreated(ArrayList<Claim> claims) {
        // Sort claims based on claimDate (latest to earliest)
        claims.sort(Comparator.comparing(Claim::getClaimDate));

        // Print sorted claims
        System.out.println("Claims sorted from latest created to earliest:");
        for (Claim claim : claims) {
            System.out.println(claim.toString());
        }
    }

}
