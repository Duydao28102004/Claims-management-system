package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.IdManager;
import org.example.System.InsuranceCard;

import java.util.ArrayList;
import java.util.Scanner;

public class DependentManager {
    static Scanner scanner = new Scanner(System.in);

    public static void printDependent(ArrayList<Dependent> dependents) {
        int counter = 1;
        if (dependents.size() == 0) {
            System.out.println("No dependents found.");
            return;
        }
        for (Dependent dependent : dependents) {
            System.out.println(counter+ ". "+dependent.toString());
            counter++;
        }
    }
    public static Dependent createDependent() {
        System.out.print("Enter dependent full name: ");
        String fullName = scanner.nextLine();
        InsuranceCard insuranceCard = null;
        ArrayList<Claim> claims = new ArrayList<>();
        String id = "c-" + IdManager.generateId(7);
        return new Dependent(id, fullName, insuranceCard, claims, null);
    }
    public static void deleteDependent(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        printDependent(dependents);
        if (dependents.size() == 0) {
            return;
        }
        System.out.println("Enter the number of the dependent you want to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;
        if (userInput.isEmpty() || id < 0 || id >= dependents.size()) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        for (Dependent dependent : dependents) {
            if (dependent.getClaims().size() != 0) {
                System.out.println("Dependent is in claims. Delete claim first.");
                System.out.print("Claim list: ");
                for (Claim claim : dependent.getClaims()) {
                    System.out.print(claim.getId() + ", ");
                }
                System.out.println();
                return;
            }
        }
        if (dependents.get(id).getInsuranceCard() != null) {
            System.out.println("Dependent has an insurance card. Delete it first to delete the dependent.");
            System.out.println("Insurance card: " + dependents.get(id).getInsuranceCard().getCardNumber());
            return;
        }
        String policyHolderId = dependents.get(id).getPolicyHolder().split(" - ")[0];
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getId().equals(policyHolderId)) {
                for (String dependent : policyHolder.getDependents()) {
                    if (dependent.contains(dependents.get(id).getId())) {
                        policyHolder.getDependents().remove(dependent);
                        break;
                    }
                }
            }
        }
        System.out.println("Dependent deleted.");
        dependents.remove(id);
    }
}
