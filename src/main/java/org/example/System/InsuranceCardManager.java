package org.example.System;

import org.example.System.Customer.Customer;
import org.example.System.Customer.Dependent;
import org.example.System.Customer.PolicyHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;


public class InsuranceCardManager {
    Scanner scanner = new Scanner(System.in);
    public InsuranceCard createInsuranceCard(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        System.out.println("Dependents:");
        for (int i = 0; i < dependents.size(); i++) {
            System.out.println((i + 1) + ". " + dependents.get(i).toString() + " - Current insurance card:" + dependents.get(i).getInsuranceCard());
        }
        System.out.println("Policyholders:");
        for (int i = 0; i < policyHolders.size(); i++) {
            System.out.println((i + 1 + dependents.size()) + ". " + policyHolders.get(i).toString() + " - Current insurance card:" + policyHolders.get(i).getInsuranceCard());
        }
        System.out.print("Enter the id of person that you want to create insurance card: ");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        Customer cardHolder;
        if (selectedIndex >= 1 && selectedIndex <= dependents.size()) {
            cardHolder = dependents.get(selectedIndex - 1);
        } else if (selectedIndex > dependents.size() && selectedIndex <= dependents.size() + policyHolders.size()) {
            cardHolder = policyHolders.get(selectedIndex - 1 - dependents.size());
        } else {
            System.out.println("Invalid selection. Insurance card creation failed.");
            return null;
        }
        if (cardHolder.getInsuranceCard() != null) {
            System.out.println("Person already has an insurance card. Delete it first to create a new one");
            return null;

        }
        String cardNumber = IdManager.generateId(10);
        System.out.print("Enter policy owner name: ");
        String policyOwner = scanner.nextLine();
        System.out.print("Enter expiration date(yyyy-MM-dd): ");
        String expirationDateInput = scanner.nextLine();
        Date expirationDate;
        // Parse the input string to Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            expirationDate = dateFormat.parse(expirationDateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Current date is set as expiration date.");
            expirationDate = new Date(); // Set current date as default
        }

        InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder.getFullName(), policyOwner, expirationDate);
        cardHolder.setInsuranceCard(insuranceCard);
        return insuranceCard;
    }
    public void printInsuranceCard(ArrayList<InsuranceCard> insuranceCards) {
        int counter = 1;
        if (insuranceCards.size() == 0) {
            System.out.println("No insurance card found.");
            return;
        }
        for (InsuranceCard insuranceCard : insuranceCards) {
            System.out.println(counter + ". " + insuranceCard.toString());
            counter++;
        }
    }
    public void deleteInsuranceCard(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders, ArrayList<InsuranceCard> insuranceCards, ArrayList<Claim> claims) {
        printInsuranceCard(insuranceCards);
        System.out.print("Enter the index of insurance card to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;

        // check if the insurance card are in use or not
        for (Claim claim : claims) {
            System.out.println(claim.getCardNumber());
            System.out.println(insuranceCards.get(id).getCardNumber());
            if (Objects.equals(claim.getCardNumber(), insuranceCards.get(id).getCardNumber())) {
                System.out.println("Insurance card is used in a claim with the id " + claim.getId() + ". Cannot delete.");
                return;
            }
        }

        // remove the insurance card from the dependents and policy holders
        for (Dependent dependent : dependents) {
            if (dependent.getInsuranceCard() != null && Objects.equals(dependent.getInsuranceCard().getCardNumber(), insuranceCards.get(id).getCardNumber())) {
                dependent.setInsuranceCard(null);
            }
        }
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getInsuranceCard() != null && Objects.equals(policyHolder.getInsuranceCard().getCardNumber(), insuranceCards.get(id).getCardNumber())) {
                policyHolder.setInsuranceCard(null);
            }
        }

        insuranceCards.remove(id);
        System.out.println("Insurance card deleted successfully.");
    }

}
