package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DependentManager {
    Scanner scanner = new Scanner(System.in);
    public Dependent createDependent() {
        System.out.print("Enter dependent full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Set the insurance card(Enter to skip): ");
        String userInput = scanner.nextLine();
        InsuranceCard insuranceCard = null;
        if (!userInput.isEmpty()) {

        };
        System.out.print("Set the policy holder(Enter to skip): ");
        userInput = scanner.nextLine();
        PolicyHolder policyHolder = null;
        if (!userInput.isEmpty()) {

        };
        List<Claim> claims = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String id = dateFormat.format(new Date());
        return new Dependent(id, fullName, insuranceCard, claims, policyHolder);
    }
}
