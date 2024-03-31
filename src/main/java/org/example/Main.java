package org.example;

import org.example.System.ClaimProcessManager;
import org.example.System.Claim;
import org.example.System.FileManagement;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManager();
        FileManagement fileManagement = new FileManagement();
//        Claim claim = claimProcessManager.addClaim();
//        ArrayList<Claim> claims = new ArrayList<>();
//        claims.add(claim);
//        fileManagement.fileWriter(claims, "claim.json");
        ArrayList<Claim> claims = fileManagement.claimReader();
        for (Claim c : claims) {
            System.out.println(c.toString());
        }
    }
}