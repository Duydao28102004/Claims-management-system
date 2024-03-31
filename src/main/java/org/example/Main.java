package org.example;

import org.example.System.ClaimProcessManager;
import org.example.System.Claim;
import org.example.System.Customer.Dependent;
import org.example.System.FileManager;
import org.example.System.Customer.DependentManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManager();

        FileManager fileManager = new FileManager();
//        Claim claim = claimProcessManager.addClaim();
//        ArrayList<Claim> claims = new ArrayList<>();
//        claims.add(claim);
//        fileManagement.fileWriter(claims, "claim.json");
        DependentManager dependentManager = new DependentManager();
        ArrayList<Dependent> dependents = fileManager.dependentReader();
        ArrayList<Claim> claims = fileManager.claimReader();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Main menu:");
            System.out.println("1. Claim");
            System.out.println("2. Dependent");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Claim menu:");
                    System.out.println("1. Add claim");
                    System.out.println("2. Delete claim");
                    System.out.println("3. View claims");
                    System.out.println("0. Exit");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (choice) {
                        case 1:
                            Claim claim = claimProcessManager.addClaim();
                            claims.add(claim);
                            break;
                        case 2:
                            claimProcessManager.deleteClaim(claims);
                            break;
                        case 3:
                            claimProcessManager.printClaim(claims);
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Dependent menu:");
                    System.out.println("1. Add dependent");
                    System.out.println("2. Delete dependent");
                    System.out.println("3. View dependents");
                    System.out.println("0. Exit");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (choice) {
                        case 1:
                            Dependent dependent = dependentManager.createDependent();
                            dependents.add(dependent);
                            break;
                        case 2:
                            dependentManager.deleteDependent(dependents);
                            break;
                        case 3:
                            dependentManager.printDependent(dependents);
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
    }
}