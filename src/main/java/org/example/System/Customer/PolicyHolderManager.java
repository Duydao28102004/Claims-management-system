package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.IdManager;
import org.example.System.InsuranceCard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class PolicyHolderManager {
    static Scanner scanner = new Scanner(System.in);
    public static void printPolicyHolder(ArrayList<PolicyHolder> policyHolders) {
        int counter = 1;
        if (policyHolders.size() == 0) {
            System.out.println("No policy holders found.");
            return;
        }
        for (PolicyHolder policyHolder : policyHolders) {
            System.out.println(counter+ ". "+policyHolder.toString());
            counter++;
        }
    }
    public static PolicyHolder createPolicyHolder() {
        System.out.println("Enter policy holder full name: ");
        String fullName = scanner.nextLine();
        InsuranceCard insuranceCard = null;
        ArrayList<Claim> claims = new ArrayList<>();
        ArrayList<Dependent> dependents = new ArrayList<>();
        String id = "c-" + IdManager.generateId(7);
        return new PolicyHolder(id, fullName, insuranceCard, claims, dependents);
    }
    public static void deletePolicyHolder(ArrayList<PolicyHolder> policyHolders) {
        printPolicyHolder(policyHolders);
        if (policyHolders.size() == 0) {
            return;
        }
        System.out.println("Enter the number of the policy holder you want to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;
        if (userInput.isEmpty() || id < 0 || id >= policyHolders.size()) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getClaims().size() != 0) {
                System.out.println("Policy holder is in claims. Delete claim first.");
                System.out.print("Claim list: ");
                for (Claim claim : policyHolder.getClaims()) {
                    System.out.print(claim.getId() + ", ");
                }
                System.out.println();
                return;
            }
        }
        if (policyHolders.get(id).getInsuranceCard() != null) {
            System.out.println("Policy holder has an insurance card. Delete it first to delete the policy holder.");
            System.out.println("Insurance card: " + policyHolders.get(id).getInsuranceCard().getCardNumber());
            return;
        }
        System.out.println("Policy holder deleted.");
        policyHolders.remove(id);
    }
}
