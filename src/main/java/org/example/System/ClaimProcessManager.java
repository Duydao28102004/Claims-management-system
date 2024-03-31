package org.example.System;

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

    public Claim addClaim() {
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

        System.out.print("Enter insured person: ");
        String insuredPerson = scanner.nextLine();
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
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

        String id = UUID.randomUUID().toString();
        return new Claim(id, claimDate, insuredPerson, cardNumber, examDate, null, claimAmount, "Pending", bankingInfo);
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
