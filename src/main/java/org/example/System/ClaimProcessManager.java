package org.example.System;

import org.example.System.Customer.*;

import java.text.SimpleDateFormat;
import java.util.*;


public class ClaimProcessManager {
    Scanner scanner = new Scanner(System.in);

    public void printClaim(ArrayList<Claim> claims) {
        int index = 1;
        for (Claim claim : claims) {
            System.out.println(index + ". " + claim.toString());
            index++;
        }
    }

    public Claim addClaim(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        System.out.print("Enter claim date(Press enter to get current date)(yyyy-MM-dd): ");
        String claimDateInput = scanner.nextLine();
        Date claimDate;

        if (claimDateInput.isEmpty()) {
            claimDate = new Date(); // Get current date
        } else {
            // Parse the input string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                claimDate = dateFormat.parse(claimDateInput);
            } catch (Exception e) {
                System.out.println("Invalid date format. Current date is set as claim date.");
                claimDate = new Date(); // Set current date as default
            }
        }
        // Remove time component from claimDate
        claimDate = removeTimeComponent(claimDate);
        System.out.println("Dependents:");
        for (int i = 0; i < dependents.size(); i++) {
            System.out.println((i + 1) + ". " + dependents.get(i).toString());
        }
        System.out.println("Policyholders:");
        for (int i = 0; i < policyHolders.size(); i++) {
            System.out.println((i + 1 + dependents.size()) + ". " + policyHolders.get(i).toString());
        }
        System.out.print("Enter the id of insured person: ");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        Customer insuredPerson;
        if (selectedIndex >= 1 && selectedIndex <= dependents.size()) {
            insuredPerson = dependents.get(selectedIndex - 1);
        } else if (selectedIndex > dependents.size() && selectedIndex <= dependents.size() + policyHolders.size()) {
            insuredPerson = policyHolders.get(selectedIndex - 1 - dependents.size());
        } else {
            System.out.println("Invalid selection. Claim creation failed.");
            return null;
        }
        String cardNumber;
        if (insuredPerson.getInsuranceCard() == null) {
            System.out.println("Insured person does not have an insurance card.");
            System.out.print("Enter card number: ");
            cardNumber = scanner.nextLine();
        } else {
            System.out.println("Insured person has an insurance card.");
            cardNumber = insuredPerson.getInsuranceCard().getCardNumber();
        }
        System.out.print("Enter exam date(Press enter to get current date)(yyyy-MM-dd): ");
        String examDateInput = scanner.nextLine();
        Date examDate;
        if (examDateInput.isEmpty()) {
            examDate = new Date(); // Get current date
        } else {
            // Parse the input string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                examDate = dateFormat.parse(examDateInput);
            } catch (Exception e) {
                System.out.println("Invalid date format. Current date is set as exam date.");
                examDate = new Date(); // Set current date as default
            }
        }
        // Remove time component from examDate
        examDate = removeTimeComponent(examDate);

        System.out.print("Enter claim amount: ");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter banking information(Bank–Name–Number): ");
        String bankingInfo = scanner.nextLine();

        String id = "f-" + IdManager.generateId(10);
        Claim claim = new Claim(id, claimDate, insuredPerson.getFullName(), cardNumber, examDate, null, claimAmount, "Pending", bankingInfo);
        insuredPerson.getClaims().add(claim);
        return claim;
    }

    private Date removeTimeComponent(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public void deleteClaim(ArrayList<Claim> claims) {
        printClaim(claims);
        System.out.println("Enter the index of the claim you want to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;
        claims.remove(id);
        System.out.println("Claim deleted successfully!");
    }
}
