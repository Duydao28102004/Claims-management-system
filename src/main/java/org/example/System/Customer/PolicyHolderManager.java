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
        System.out.println("Enter the id of the policy holder you want to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;
        System.out.println(id);
        policyHolders.remove(id);
    }
}
