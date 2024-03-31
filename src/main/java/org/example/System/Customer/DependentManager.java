package org.example.System.Customer;

import org.example.System.Claim;
import org.example.System.InsuranceCard;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class DependentManager {
    static Scanner scanner = new Scanner(System.in);

    public static void printDependent(ArrayList<Dependent> dependents) {
        int counter = 1;
        for (Dependent dependent : dependents) {
            System.out.println(counter+ ". "+dependent.toString());
            counter++;
        }
    }
    public static Dependent createDependent() {
        System.out.print("Enter dependent full name: ");
        String fullName = scanner.nextLine();
        InsuranceCard insuranceCard = null;
        ArrayList<Claim> claims = new ArrayList<>();
        String id = UUID.randomUUID().toString();
        return new Dependent(id, fullName, insuranceCard, claims);
    }
    public static void deleteDependent(ArrayList<Dependent> dependents) {
        printDependent(dependents);
        System.out.println("Enter the id of the dependent you want to delete: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput) - 1;
        System.out.println(id);
        dependents.remove(id);
    }
}
