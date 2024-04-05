package org.example.System;

/**
 * @author <Dao Bao Duy - s3978826>
 *     Adapted from: chatGPT, w3schools
 */

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
        // Print all dependents and policy holders
        System.out.println("Dependents:");
        for (int i = 0; i < dependents.size(); i++) {
            System.out.println((i + 1) + ". " + dependents.get(i).getFullName() + " - "+ dependents.get(i).getId() + " - Policy Holder: {" + dependents.get(i).getPolicyHolder() + "} - Current insurance card: " + dependents.get(i).getInsuranceCard());
        }
        System.out.println("Policyholders:");
        for (int i = 0; i < policyHolders.size(); i++) {
            System.out.println((i + 1 + dependents.size()) + ". " + policyHolders.get(i).getFullName() + " - "+ policyHolders.get(i).getId() + " - Current insurance card: " + policyHolders.get(i).getInsuranceCard());
        }

        // Get the id of the person to create insurance card
        System.out.print("Enter the id of person that you want to create insurance card: ");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        // Check if the input is valid
        Customer cardHolder;
        if (selectedIndex >= 1 && selectedIndex <= dependents.size()) {
            cardHolder = dependents.get(selectedIndex - 1);
        } else if (selectedIndex > dependents.size() && selectedIndex <= dependents.size() + policyHolders.size()) {
            cardHolder = policyHolders.get(selectedIndex - 1 - dependents.size());
        } else {
            System.out.println("Invalid selection. Insurance card creation failed.");
            return null;
        }
        // Check if the person already has an insurance card
        if (cardHolder.getInsuranceCard() != null) {
            System.out.println("Person already has an insurance card. Delete it first to create a new one");
            return null;

        }

        // Get the policy owner name
        String policyOwner = null;
        // If the card holder is a dependent, get the policy holder name from the policy holder
        if (cardHolder instanceof Dependent) {
            if (Objects.equals(((Dependent) cardHolder).getPolicyHolder(), "null")) {
                System.out.println("Dependent does not have a policy holder. Add this dependent to policy holder first.");
                return null;
            }
            String policyHolderId = ((Dependent) cardHolder).getPolicyHolder().split(" - ")[0];
            for (PolicyHolder policyHolder : policyHolders) {
                if (Objects.equals(policyHolder.getId(), policyHolderId)) {
                    if (policyHolder.getInsuranceCard() == null) {
                        System.out.println("Create insurance card for the policy holder (" + policyHolder.getId()+ " - " +policyHolder.getFullName()+ ") first.");
                        return null;
                    }
                    policyOwner = policyHolder.getInsuranceCard().getPolicyOwner();
                    break;
                }
            }
        } else {
            System.out.print("Enter policy owner name: ");
            policyOwner = scanner.nextLine();
        }
        // Generate a random card number
        String cardNumber = IdManager.generateId(10);
        System.out.print("Enter expiration date(yyyy-MM-dd): ");
        String expirationDateInput = scanner.nextLine();
        Date expirationDate;
        // Parse the input string to Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            expirationDate = dateFormat.parse(expirationDateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Returning to main menu.");
            return null;
        }
        // Create the insurance card
        InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder.getFullName(), policyOwner, expirationDate);
        cardHolder.setInsuranceCard(insuranceCard);
        System.out.println("Insurance card created successfully.");
        return insuranceCard;
    }
    public void printInsuranceCard(ArrayList<InsuranceCard> insuranceCards) {
        int counter = 1;
        // Check if there are no insurance cards
        if (insuranceCards.size() == 0) {
            System.out.println("No insurance card found.");
            return;
        }
        // Print all insurance cards
        for (InsuranceCard insuranceCard : insuranceCards) {
            System.out.println(counter + ". " + insuranceCard.toString());
            counter++;
        }
    }
    public void deleteInsuranceCard(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders, ArrayList<InsuranceCard> insuranceCards, ArrayList<Claim> claims) {
        printInsuranceCard(insuranceCards);
        // Check if there are no insurance cards
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

        // remove the insurance card from the insurance card list
        insuranceCards.remove(id);
        System.out.println("Insurance card deleted successfully.");
    }

}
