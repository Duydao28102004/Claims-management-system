package org.example.System;

import org.example.System.Customer.*;

import java.text.SimpleDateFormat;
import java.util.*;


public class ClaimProcessManager {
    Scanner scanner = new Scanner(System.in);

    public void printClaim(ArrayList<Claim> claims) {
        int index = 1;
        if (claims.isEmpty()) {
            System.out.println("No claims found.");
            return;
        }
        for (Claim claim : claims) {
            System.out.println(index + ". " + claim.toString());
            index++;
        }
    }

    public Claim addClaim(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        System.out.println("Dependents:");
        if (dependents.isEmpty()) {
            System.out.println("No dependents found.");
        } else {
            for (int i = 0; i < dependents.size(); i++) {
                System.out.println((i + 1) + ". " + dependents.get(i).toString());
            }
        }
        System.out.println("Policyholders:");
        if (policyHolders.isEmpty()) {
            System.out.println("No policyholders found.");
        } else {
            for (int i = 0; i < policyHolders.size(); i++) {
                System.out.println((i + 1 + dependents.size()) + ". " + policyHolders.get(i).toString());
            }
        }
        if (dependents.isEmpty() && policyHolders.isEmpty()) {
            System.out.println("No customers found. Claim creation failed.");
            return null;
        }
        System.out.print("Enter the number of insured person: ");
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
            System.out.println("Insured person does not have an insurance card yet. Create one first.");
            return null;
        } else {
            System.out.println("Insured person has an insurance card. Autofill it in the claim.");
            cardNumber = insuredPerson.getInsuranceCard().getCardNumber();
        }
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
        System.out.print("Enter receiving bank name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter receiving account name: ");
        String accountName = scanner.nextLine();
        System.out.print("Enter receiving account number: ");
        String accountNumber = scanner.nextLine();
        String bankingInfo = bankName + "-" + accountName + "-" + accountNumber;

        String id = "f-" + IdManager.generateId(10);
        Claim claim = new Claim(id, claimDate, insuredPerson.getFullName(), cardNumber, examDate, null, claimAmount, "new", bankingInfo);
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
    public void updateClaim(ArrayList<Claim> claims, ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        printClaim(claims);
        System.out.print("Enter the index of the claim you want to update: ");
        String userChoice = scanner.nextLine();
        if (userChoice.isEmpty() || Integer.parseInt(userChoice) > claims.size() || Integer.parseInt(userChoice) < 1) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        int index = Integer.parseInt(userChoice) - 1;
        Claim claim = claims.get(index);

        System.out.println("Current claim amount: " + claim.getClaimAmount() + ".");
        System.out.print("Enter new claim amount(Enter to skip): ");
        String claimAmountInput = scanner.nextLine();
        if (!claimAmountInput.isEmpty()) {
            claim.setClaimAmount(Double.parseDouble(claimAmountInput));
        }
        System.out.println("Current exam date: " + claim.getExamDate() + ".");
        System.out.print("Enter new exam date(yyyy-MM-dd)(Enter to skip): ");
        String claimDateInput = scanner.nextLine();
        if (!claimDateInput.isEmpty()) {
            // Parse the input string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date claimDate = dateFormat.parse(claimDateInput);
                removeTimeComponent(claimDate);
                claim.setClaimDate(claimDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Claim exam date not updated.");
            }
        }
        System.out.println("Current exam date: " + claim.getExamDate() + ".");
        System.out.print("Enter new exam date(yyyy-MM-dd)(Enter to skip): ");
        String examDateInput = scanner.nextLine();
        if (!examDateInput.isEmpty()) {
            // Parse the input string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date examDate = dateFormat.parse(examDateInput);
                removeTimeComponent(examDate);
                claim.setExamDate(examDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Claim exam date not updated.");
            }
        }
        System.out.print("Current claim status: " + claim.getStatus() + ".");
        System.out.print("Enter new claim status(new-processing-done)(Enter to skip): ");
        String statusInput = scanner.nextLine();
        if (!statusInput.isEmpty()) {
            if (statusInput.equals("new") || statusInput.equals("processing") || statusInput.equals("done")) {
                claim.setStatus(statusInput);
            } else {
                System.out.println("Invalid status. Claim status not updated.");
            }
        }
        System.out.print("Current banking info: " + claim.getBankingInfo() + ".");
        System.out.print("Do you want to update banking info?(y/n): ");
        String choice = scanner.nextLine();

        if (choice.equals("y")) {
            System.out.print("Enter receiving bank name: ");
            String bankName = scanner.nextLine();
            System.out.print("Enter receiving account name: ");
            String accountName = scanner.nextLine();
            System.out.print("Enter receiving account number: ");
            String accountNumber = scanner.nextLine();
            String bankingInfo = bankName + "-" + accountName + "-" + accountNumber;
            claim.setBankingInfo(bankingInfo);
        } else if (choice.equals("n")) {
            System.out.println("Banking info not updated.");
        } else {
            System.out.println("Invalid choice. Banking info not updated.");
        }
        // Update the claim in dependents
        for (Dependent dependent : dependents) {
            for (Claim dependentClaim : dependent.getClaims()) {
                if (dependentClaim.getId().equals(claim.getId())) {
                    dependentClaim.setClaimAmount(claim.getClaimAmount());
                    dependentClaim.setClaimDate(claim.getClaimDate());
                    dependentClaim.setExamDate(claim.getExamDate());
                    dependentClaim.setStatus(claim.getStatus());
                    dependentClaim.setBankingInfo(claim.getBankingInfo());
                    break;
                }
            }
        }

        // Update the claim in policyHolders
        for (PolicyHolder policyHolder : policyHolders) {
            for (Claim policyHolderClaim : policyHolder.getClaims()) {
                if (policyHolderClaim.getId().equals(claim.getId())) {
                    policyHolderClaim.setClaimAmount(claim.getClaimAmount());
                    policyHolderClaim.setClaimDate(claim.getClaimDate());
                    policyHolderClaim.setExamDate(claim.getExamDate());
                    policyHolderClaim.setStatus(claim.getStatus());
                    policyHolderClaim.setBankingInfo(claim.getBankingInfo());
                    break;
                }
            }
        }
        System.out.println("Claim updated successfully!");
    }

    public void deleteClaim(ArrayList<Claim> claims, ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        printClaim(claims);
        System.out.println("Enter the index of the claim you want to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;
        for (Dependent dependent : dependents) {
            for (Claim claim : dependent.getClaims()) {
                if (claim.getId().equals(claims.get(id).getId())) {
                    dependent.getClaims().remove(claim);
                    break;
                }
            }
        }
        for (PolicyHolder policyHolder : policyHolders) {
            for (Claim claim : policyHolder.getClaims()) {
                if (claim.getId().equals(claims.get(id).getId())) {
                    policyHolder.getClaims().remove(claim);
                    break;
                }
            }
        }
        claims.remove(id);
        System.out.println("Claim deleted successfully!");
    }
}
