package org.example.System;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.System.Customer.Dependent;
import org.example.System.Customer.PolicyHolder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    public void fileWriter(Object object, String fileName) {
        String folderPath = "./data/";
        createNewFile(folderPath, fileName);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        try (FileWriter writer = new FileWriter(folderPath + fileName, false)) {
            // Write JSON string to file
            writer.write(json);
            System.out.println("Object written to " + fileName + " successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createNewFile(String folderPath, String fileName) {
        File file = new File(folderPath + fileName);
        if (file.exists()) {
            System.out.println( fileName + " exists in the folder.");
        } else {
            System.out.println("The file does not exist in the folder.");
            System.out.println("Creating a new file...");
            try {
                file.createNewFile();
                // Add square brackets to the new JSON file
                FileWriter writer = new FileWriter(file);
                writer.write("[]"); // Write square brackets to the file to store array list
                writer.close();
                System.out.println("The file is created.");
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Claim> claimReader() {
        Gson gson = new Gson();
        String folderPath = "./data/";
        String fileName = "claim.json";
        createNewFile(folderPath, fileName);
        try (FileReader reader = new FileReader(folderPath + fileName)) {
            // Read JSON file
            TypeToken<ArrayList<Claim>> collectionType = new TypeToken<ArrayList<Claim>>(){};
            ArrayList<Claim> claims = gson.fromJson(reader, collectionType);
            System.out.println("Object read from file successfully!");
            return claims;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Dependent> dependentReader() {
        Gson gson = new Gson();
        String folderPath = "./data/";
        String fileName = "dependent.json";
        createNewFile(folderPath, fileName);
        try (FileReader reader = new FileReader(folderPath + fileName)) {
            // Read JSON file
            TypeToken<ArrayList<Dependent>> collectionType = new TypeToken<ArrayList<Dependent>>(){};
            ArrayList<Dependent> dependents = gson.fromJson(reader, collectionType);
            System.out.println("Object read from file successfully!");
            return dependents;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<PolicyHolder> policyHolderReader() {
        Gson gson = new Gson();
        String folderPath = "./data/";
        String fileName = "policyHolder.json";
        createNewFile(folderPath, fileName);
        try (FileReader reader = new FileReader(folderPath + fileName)) {
            // Read JSON file
            TypeToken<ArrayList<PolicyHolder>> collectionType = new TypeToken<ArrayList<PolicyHolder>>(){};
            ArrayList<PolicyHolder> policyHolders = gson.fromJson(reader, collectionType);
            System.out.println("Object read from file successfully!");
            return policyHolders;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<InsuranceCard> insuranceCardReader() {
        Gson gson = new Gson();
        String folderPath = "./data/";
        String fileName = "insuranceCard.json";
        createNewFile(folderPath, fileName);
        try (FileReader reader = new FileReader(folderPath + fileName)) {
            // Read JSON file
            TypeToken<ArrayList<InsuranceCard>> collectionType = new TypeToken<ArrayList<InsuranceCard>>(){};
            ArrayList<InsuranceCard> insuranceCards = gson.fromJson(reader, collectionType);
            System.out.println("Object read from file successfully!");
            return insuranceCards;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
