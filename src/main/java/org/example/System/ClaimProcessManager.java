package org.example.System;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


public class ClaimProcessManager {
    Scanner scanner = new Scanner(System.in);
    public Claim addClaim() {
        System.out.print("Enter claim date(Press enter to get current date)(yyyy-MM-dd): ");
        String claimDateInput = scanner.nextLine();
        Date claimDate;

        if (claimDateInput.isEmpty()) {
            claimDate = new Date(); // Get current date
            System.out.println("Current date is set as claim date.");
        } else {
            // Parse the input string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                claimDate = dateFormat.parse(claimDateInput);
                System.out.println("Claim date is set as " + claimDateInput);
            } catch (Exception e) {
                System.out.println("Invalid date format. Current date is set as claim date.");
                claimDate = new Date(); // Set current date as default
            }
        }
        System.out.print("Enter insured person: ");
        String insuredPerson = scanner.nextLine();
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter exam date(Press enter to get current date)(yyyy-MM-dd): ");
        String examDateInput = scanner.nextLine();
        Date examDate;
        if (examDateInput.isEmpty()) {
            examDate = new Date(); // Get current date
            System.out.println("Current date is set as exam date.");
        } else {
            // Parse the input string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                examDate = dateFormat.parse(examDateInput);
                System.out.println("Exam date is set as " + examDateInput);
            } catch (Exception e) {
                System.out.println("Invalid date format. Current date is set as exam date.");
                examDate = new Date(); // Set current date as default
            }
        }
        System.out.print("Enter claim amount: ");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter banking information(Bank–Name–Number): ");
        String bankingInfo = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String id = UUID.randomUUID().toString();
        return new Claim(id, claimDate, insuredPerson, cardNumber, examDate, null, claimAmount, "Pending", bankingInfo);
    }
}
