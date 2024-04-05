package org.example.System;

/**
 * @author <Dao Bao Duy - s3978826>
 *     Adapted from: chatGPT, w3schools
 */

import org.example.System.Customer.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.example.System.IdManager.isNumeric;


public class ClaimProcessManager {
    Scanner scanner = new Scanner(System.in);


    public void printClaim(ArrayList<Claim> claims) {
        int index = 1;
        // Check if there are no claims
        if (claims.isEmpty()) {
            System.out.println("No claims found.");
            return;
        }
        // Print all claims
        for (Claim claim : claims) {
            System.out.println(index + ". " + claim.toString());
            index++;
        }
    }

    public Claim addClaim(ArrayList<Dependent> dependents, ArrayList<PolicyHolder> policyHolders) {
        // Check if there are no dependents or policy holders
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

        // Check if there are no customers
        if (dependents.isEmpty() && policyHolders.isEmpty()) {
            System.out.println("No customers found. Claim creation failed.");
            return null;
        }

        // Get the insured person from the list
        System.out.print("Enter the number of insured person: ");
        String input = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(input)) {
            System.out.println("Invalid selection. Claim creation failed.");
            return null;
        }
        int selectedIndex = Integer.parseInt(input);
         // Consume newline left-over
        Customer insuredPerson;
        // Check if the selected index is valid
        if (selectedIndex >= 1 && selectedIndex <= dependents.size()) {
            insuredPerson = dependents.get(selectedIndex - 1);
        } else if (selectedIndex > dependents.size() && selectedIndex <= dependents.size() + policyHolders.size()) {
            insuredPerson = policyHolders.get(selectedIndex - 1 - dependents.size());
        } else {
            System.out.println("Invalid selection. Claim creation failed.");
            return null;
        }

        // Check if the insured person has an insurance card
        String cardNumber;
        if (insuredPerson.getInsuranceCard() == null) {
            System.out.println("Insured person does not have an insurance card yet. Create one first.");
            return null;
        } else {
            System.out.println("Insured person has an insurance card. Autofill it in the claim.");
            cardNumber = insuredPerson.getInsuranceCard().getCardNumber();
        }

        // Get claim details
        Date claimDate = new Date();;
        // Remove time component from claimDate
        claimDate = removeTimeComponent(claimDate);
        System.out.print("Enter exam date(Press enter to get current date)(yyyy-MM-dd): ");
        String examDateInput = scanner.nextLine();

        // Parse the input string to Date object
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

        // Get claim amount
        System.out.print("Enter claim amount: ");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        // Get documents
        ArrayList<String> documents = new ArrayList<>();
        System.out.print("Do you want to add documents?(y/n): ");
        String choice = scanner.nextLine();
        while (choice.equals("y")) {
            System.out.print("Enter document name (pdf file): ");
            String document = scanner.nextLine();
            if(document.contains(".pdf")) {
                documents.add(document);
            } else {
                System.out.println("Invalid file format. Only pdf files are allowed.");
            }
            System.out.print("Do you want to add more documents?(y/n): ");
            choice = scanner.nextLine();
        }

        // Get banking info
        System.out.print("Enter receiving bank name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter receiving account name: ");
        String accountName = scanner.nextLine();
        System.out.print("Enter receiving account number: ");
        String accountNumber = scanner.nextLine();
        String bankingInfo = bankName + "-" + accountName + "-" + accountNumber;

        // Create a new claim
        String id = "f-" + IdManager.generateId(10);
        Claim claim = new Claim(id, claimDate, insuredPerson.getFullName(), cardNumber, examDate, documents, claimAmount, "new", bankingInfo);
        insuredPerson.getClaims().add(claim);
        System.out.println("Claim added successfully!");
        return claim;
    }

    private Date removeTimeComponent(Date date) {
        // Remove time component from date
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

        // Get the claim to update
        System.out.print("Enter the index of the claim you want to update: ");
        String userChoice = scanner.nextLine();
        if (!isNumeric(userChoice)) {
            System.out.println("Invalid selection. Creation failed.");
            return;
        }
        // Check if the input is valid
        if (userChoice.isEmpty() || Integer.parseInt(userChoice) > claims.size() || Integer.parseInt(userChoice) < 1) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        int index = Integer.parseInt(userChoice) - 1;
        Claim claim = claims.get(index);

        // Update claim details
        System.out.println("Current claim amount: " + claim.getClaimAmount() + ".");
        System.out.print("Enter new claim amount(Enter to skip): ");
        String claimAmountInput = scanner.nextLine();
        if (!claimAmountInput.isEmpty()) {
            claim.setClaimAmount(Double.parseDouble(claimAmountInput));
        }
        System.out.println("Current exam date: " + claim.getExamDate() + ".");
        System.out.print("Enter new exam date(yyyy-MM-dd)(Enter to skip): ");
        String examDateInput = scanner.nextLine();
        // Parse the input string to Date object
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
        // Update claim status
        System.out.print("Current claim status: " + claim.getStatus() + ".");
        System.out.print("Enter new claim status(new-processing-done)(Enter to skip): ");
        String statusInput = scanner.nextLine();
        // Check if the input is valid
        if (!statusInput.isEmpty()) {
            if (statusInput.equals("new") || statusInput.equals("processing") || statusInput.equals("done")) {
                claim.setStatus(statusInput);
            } else {
                System.out.println("Invalid status. Claim status not updated.");
            }
        }
        // Update banking info
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
        //  Get the claim to delete
        System.out.println("Enter the index of the claim you want to delete: ");
        String userInput = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(userInput)) {
            System.out.println("Invalid selection. Claim creation failed.");
            return;
        }
        int id = Integer.parseInt(userInput) - 1;

        // remove the claim in dependent and policy holder
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
        // remove the claim in the list
        claims.remove(id);
        System.out.println("Claim deleted successfully!");
    }
    public void addDocument(ArrayList<Claim> claims) {
        printClaim(claims);

        // Get the claim to add document
        System.out.println("Select a claim to add document:");
        String index = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(index)) {
            System.out.println("Invalid selection. Claim creation failed.");
            return;
        }
        int id = Integer.parseInt(index) - 1;
        String choice = "y";
        // Add documents
        while (choice.equals("y")) {
            System.out.print("Enter document name (pdf file): ");
            String document = scanner.nextLine();
            // Check if the document is a pdf file
            if(document.contains(".pdf")) {
                claims.get(id).getDocuments().add(document);
            } else {
                System.out.println("Invalid file format. Only pdf files are allowed.");
            }
            // Ask if user wants to add more documents
            System.out.print("Do you want to add more documents?(y/n): ");
            choice = scanner.nextLine();
            if (!Objects.equals(choice, "y") && !Objects.equals(choice, "n")) {
                System.out.println("Invalid choice. Documents not added.");
                break;
            }
        }
        System.out.println("Documents added successfully.");
    }
    public void deleteDocument(ArrayList<Claim> claims) {
        printClaim(claims);

        // Get the claim to delete document
        System.out.println("Select a claim to delete document:");
        String index = scanner.nextLine();
        // check user enter number or not
        if (!isNumeric(index)) {
            System.out.println("Invalid selection. Claim creation failed.");
            return;
        }
        int id = Integer.parseInt(index) - 1;
        // Check if the claim has documents
        if (id < 0 || id >= claims.size()) {
            System.out.println("Invalid choice. Returning to menu.");
            return;
        }
        if (claims.get(id).getDocuments().size() == 0) {
            System.out.println("No documents found.");
            return;
        }
        String choice = "y";
        // Delete documents
        while (choice.equals("y")) {
            // Print all documents
            System.out.println("Current documents: ");
            int counter = 1;
            for (String document : claims.get(id).getDocuments()) {
                System.out.println(counter + document);
                counter++;
            }
            // Get the document to delete
            System.out.print("Enter the index of the document you want to delete: ");
            String userInput = scanner.nextLine();
            int docId = Integer.parseInt(userInput) - 1;
            // Check if the input is valid
            if (docId < 0 || docId >= claims.get(id).getDocuments().size() || !isNumeric(userInput)) {
                System.out.println("Invalid choice. Please try again.");
            } else {
                claims.get(id).getDocuments().remove(docId);
                System.out.println("Document deleted successfully.");
            }
            // Ask if user wants to delete more documents
            System.out.print("Do you want to delete more documents?(y/n): ");
            choice = scanner.nextLine();
            if (!Objects.equals(choice, "y") && !Objects.equals(choice, "n")) {
                System.out.println("Invalid choice. Returning to menu.");
                break;
            }
        }
    }
}
