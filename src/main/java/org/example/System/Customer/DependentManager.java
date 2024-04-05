package org.example.System.Customer;

/**
 * @author <Dao Bao Duy - s3978826>
 *     Adapted from: chatGPT, w3schools
 */

import org.example.System.Claim;
import org.example.System.IdManager;
import org.example.System.InsuranceCard;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.System.IdManager.isNumeric;

public class DependentManager {
    static Scanner scanner = new Scanner(System.in);

    public static void printDependent(ArrayList<Dependent> dependents) {
        int counter = 1;
        // Check if there are no dependents
        if (dependents.size() == 0) {
            System.out.println("No dependents found.");
            return;
        }
        // Print all dependents
        for (Dependent dependent : dependents) {
            System.out.println(counter+ ". "+dependent.toString());
            counter++;
        }
    }
    public static Dependent createDependent() {
        // Get dependent full name
        System.out.print("Enter dependent full name: ");
        String fullName = scanner.nextLine();

        // Create a new dependent
        InsuranceCard insuranceCard = null;
        ArrayList<Claim> claims = new ArrayList<>();
        String id = "c-" + IdManager.generateId(7);
        return new Dependent(id, fullName, insuranceCard, claims, null);
    }
    public static void deleteDependent(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        printDependent(dependents);

        // Check if there are no dependents
        if (dependents.size() == 0) {
            return;
        }

        // Get the dependent to delete
        System.out.println("Enter the number of the dependent you want to delete: ");
        String userInput = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        int id = Integer.parseInt(userInput) - 1;

        // Check if the input is valid
        if (userInput.isEmpty() || id < 0 || id >= dependents.size()) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        // Check if the dependent is in claims
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

        // Check if the dependent has an insurance card
        if (dependents.get(id).getInsuranceCard() != null) {
            System.out.println("Dependent has an insurance card. Delete it first to delete the dependent.");
            System.out.println("Insurance card: " + dependents.get(id).getInsuranceCard().getCardNumber());
            return;
        }

        // Remove the dependent from the policy holder
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
