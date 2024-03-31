package org.example.System;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.System.Customer.Dependent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    public void fileWriter(Object object, String fileName) {
        String folderPath = "./data/";
        File file = new File(folderPath, fileName);
        if (file.exists()) {
            System.out.println("The file exists in the folder.");
        } else {
            System.out.println("The file does not exist in the folder.");
            System.out.println("Creating a new file...");
            try {
                file.createNewFile();
                System.out.println("The file is created.");
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(object);
        try (FileWriter writer = new FileWriter(folderPath + fileName, false)) {
            // Write JSON string to file
            writer.write(json);
            System.out.println("Object written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Claim> claimReader() {
        Gson gson = new Gson();
        String folderPath = "./data/";
        String fileName = "claim.json";
        File file = new File(folderPath + fileName);
        if (file.exists()) {
            System.out.println("The file exists in the folder.");
        } else {
            System.out.println("The file does not exist in the folder.");
            System.out.println("Creating a new file...");
            try {
                file.createNewFile();
                System.out.println("The file is created.");
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
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
        File file = new File(folderPath + fileName);
        if (file.exists()) {
            System.out.println("The file exists in the folder.");
        } else {
            System.out.println("The file does not exist in the folder.");
            System.out.println("Creating a new file...");
            try {
                file.createNewFile();
                System.out.println("The file is created.");
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
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
}
