package org.example.System.Customer;

/**
 * @author <Dao Bao Duy - s3978826>
 *     Adapted from: chatGPT, w3schools
 */

import org.example.System.Claim;
import org.example.System.IdManager;
import org.example.System.InsuranceCard;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.example.System.IdManager.isNumeric;

public class PolicyHolderManager {
    static Scanner scanner = new Scanner(System.in);
    public static void printPolicyHolder(ArrayList<PolicyHolder> policyHolders) {
        int counter = 1;
        // Check if there are no policy holders
        if (policyHolders.size() == 0) {
            System.out.println("No policy holders found.");
            return;
        }
        // Print all policy holders
        for (PolicyHolder policyHolder : policyHolders) {
            System.out.println(counter+ ". "+policyHolder.toString());
            counter++;
        }
    }
    public static PolicyHolder createPolicyHolder() {
        // Get policy holder full name
        System.out.print("Enter policy holder full name: ");
        String fullName = scanner.nextLine();

        // Create a new policy holder
        InsuranceCard insuranceCard = null;
        ArrayList<Claim> claims = new ArrayList<>();
        ArrayList<String> dependents = new ArrayList<>();
        String id = "c-" + IdManager.generateId(7);
        return new PolicyHolder(id, fullName, insuranceCard, claims, dependents);
    }
    public static void deletePolicyHolder(ArrayList<PolicyHolder> policyHolders) {
        printPolicyHolder(policyHolders);
        // Check if there are no policy holders
        if (policyHolders.size() == 0) {
            return;
        }
        // Get the policy holder to delete
        System.out.println("Enter the number of the policy holder you want to delete: ");
        String userInput = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        int id = Integer.parseInt(userInput) - 1;
        // Check if the input is valid
        if (userInput.isEmpty() || id < 0 || id >= policyHolders.size()) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        // Check if the policy holder is in claims
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

        // Check if the policy holder has an insurance card
        if (policyHolders.get(id).getInsuranceCard() != null) {
            System.out.println("Policy holder has an insurance card. Delete it first to delete the policy holder.");
            System.out.println("Insurance card: " + policyHolders.get(id).getInsuranceCard().getCardNumber());
            return;
        }
        System.out.println("Policy holder deleted.");
        policyHolders.remove(id);
    }

    public static void addDependent(ArrayList<PolicyHolder> policyHolders, ArrayList<Dependent> dependents) {
        printPolicyHolder(policyHolders);
        // Check if there are no policy holders
        if (policyHolders.size() == 0) {
            System.out.println("No policy holders found.");
            return;
        }
        // Get the policy holder to add dependent to
        System.out.println("Enter the number of the policy holder you want to add dependent to: ");
        String userInput = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        int id = Integer.parseInt(userInput) - 1;
        if (userInput.isEmpty() || id < 0 || id >= policyHolders.size()) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        // Check if the policy holder has an insurance card
        int counter = 1;
        for (Dependent dependent: dependents) {
            System.out.print(counter + ". " + dependent.getFullName() + " - Policy Holder: ");
            if (dependent.getPolicyHolder() == null) {
                System.out.println("None");
            } else {
                System.out.println(dependent.getPolicyHolder());
            }
            counter++;
        }

        if (dependents.size() == 0) {
            System.out.println("No dependents found.");
            return;
        }
        // Get the dependent to add
        System.out.println("Enter the number of the dependent you want to add: ");
        userInput = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        int dependentId = Integer.parseInt(userInput) - 1;
        // Check if the input is valid
        if (userInput.isEmpty() || dependentId < 0 || dependentId >= dependents.size()) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        } else if (!Objects.equals(dependents.get(dependentId).getPolicyHolder(), "null")) {
            System.out.println("Dependent already has a policy holder. Returning to main menu.");
            return;
        }
        // Add the dependent to the policy holder
        policyHolders.get(id).getDependents().add(dependents.get(dependentId).getId() + " - " + dependents.get(dependentId).getFullName());
        // Update the dependent's policy holder
        dependents.get(dependentId).setPolicyHolder(policyHolders.get(id).getId() + " - " + policyHolders.get(id).getFullName());
        System.out.println("Dependent added to policy holder.");
    }
    public static void deleteDependent(ArrayList<PolicyHolder> policyHolders, ArrayList<Dependent> dependents) {
        printPolicyHolder(policyHolders);
        // Check if there are no policy holders
        if (policyHolders.size() == 0) {
            System.out.println("No policy holders found.");
            return;
        }
        // Get the policy holder to delete dependent from
        System.out.println("Enter the number of the policy holder you want to delete dependent from: ");
        String userInput = scanner.nextLine();
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        // Check if the input is valid
        if (userInput.equals("")) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        int id = Integer.parseInt(userInput) - 1;
        // Check if the input is valid
        if (id < 0 || id >= policyHolders.size()) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        // Check if the policy holder has dependents
        if (policyHolders.get(id).getDependents().size() == 0) {
            System.out.println("No dependents found.");
            return;
        }
        // Get the dependent to delete
        int counter = 1;
        for (String dependent: policyHolders.get(id).getDependents()) {
            System.out.println(counter + ". " + dependent);
            counter++;
        }
        System.out.println("Enter the number of the dependent you want to delete: ");
        userInput = scanner.nextLine();
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        // Check if the input is valid
        if (userInput.equals("")) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        int dependentId = Integer.parseInt(userInput) - 1;
        if (dependentId < 0 || dependentId >= policyHolders.get(id).getDependents().size()) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }

        // Take the id of dependent from the string and remove it from the dependent list
        String dependentIdString = policyHolders.get(id).getDependents().get(dependentId).split(" - ")[0];
        for (Dependent dependent: dependents) {
            if (Objects.equals(dependent.getId(), dependentIdString)) {
                dependent.setPolicyHolder("null");
                break;
            }
        }
        policyHolders.get(id).getDependents().remove(dependentId);
        System.out.println("Dependent deleted from policy holder.");
    }
}
