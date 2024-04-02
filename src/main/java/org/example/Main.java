package org.example;

import org.example.System.*;
import org.example.System.Customer.Dependent;
import org.example.System.Customer.PolicyHolder;
import org.example.System.Customer.PolicyHolderManager;
import org.example.System.Customer.DependentManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManager();
        InsuranceCardManager insuranceCardManager = new InsuranceCardManager();
        FileManager fileManager = new FileManager();
        ArrayList<Dependent> dependents = fileManager.dependentReader();
        ArrayList<Claim> claims = fileManager.claimReader();
        ArrayList<PolicyHolder> policyHolders = fileManager.policyHolderReader();
        ArrayList<InsuranceCard> insuranceCards = fileManager.insuranceCardReader();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Main menu:");
            System.out.println("1. Claim");
            System.out.println("2. Dependent");
            System.out.println("3. Policy Holder");
            System.out.println("4. Insurance Card");
            System.out.println("0. Exit");
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Claim menu:");
                    System.out.println("1. Add claim");
                    System.out.println("2. Update claim");
                    System.out.println("3. Delete claim");
                    System.out.println("4. View claims");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice:");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            Claim claim = claimProcessManager.addClaim(dependents, policyHolders);
                            if (claim != null) {
                                claims.add(claim);
                            }
                            break;
                        case 2:
                            claimProcessManager.updateClaim(claims, dependents, policyHolders);
                            break;
                        case 3:
                            claimProcessManager.printClaim(claims);
                            break;
                        case 4:
                            claimProcessManager.deleteClaim(claims, dependents, policyHolders);
                            break;
                        case 0:
                            System.out.println("Exiting to main menu...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Dependents menu:");
                    System.out.println("1. Add dependent");
                    System.out.println("2. Delete dependent");
                    System.out.println("3. View dependents");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice:");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (choice) {
                        case 1:
                            Dependent dependent = DependentManager.createDependent();
                            dependents.add(dependent);
                            break;
                        case 2:
                            DependentManager.deleteDependent(dependents);
                            break;
                        case 3:
                            DependentManager.printDependent(dependents);
                            break;
                        case 0:
                            System.out.println("Exiting to main menu...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Policy holders menu:");
                    System.out.println("1. Add policy holders");
                    System.out.println("2. Delete policy holders");
                    System.out.println("3. View policy holders");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice:");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (choice) {
                        case 1:
                            PolicyHolder policyHolder = PolicyHolderManager.createPolicyHolder();
                            policyHolders.add(policyHolder);
                            break;
                        case 2:
                            PolicyHolderManager.deletePolicyHolder(policyHolders);
                            break;
                        case 3:
                            PolicyHolderManager.printPolicyHolder(policyHolders);
                            break;
                        case 0:
                            System.out.println("Exiting to main menu...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Insurance cards menu:");
                    System.out.println("1. Add insurance cards");
                    System.out.println("2. Delete insurance cards");
                    System.out.print("Enter your choice:");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (choice) {
                        case 1:
                            InsuranceCard insuranceCard = insuranceCardManager.createInsuranceCard(dependents, policyHolders);
                            if (insuranceCard != null) {
                                insuranceCards.add(insuranceCard);
                            }
                            break;
                        case 2:
                            insuranceCardManager.deleteInsuranceCard(dependents, policyHolders, insuranceCards, claims);
                            break;
                        case 3:
                            break;
                        case 0:
                            System.out.println("Exiting to main menu...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                            break;
                    }
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
        scanner.close();
        fileManager.fileWriter(claims, "claim.json");
        fileManager.fileWriter(dependents, "dependent.json");
        fileManager.fileWriter(policyHolders, "policyHolder.json");
        fileManager.fileWriter(insuranceCards, "insuranceCard.json");
    }
}