package org.example;

import org.example.System.ClaimProcessManager;
import org.example.System.Claim;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManager();
        Claim claim = claimProcessManager.addClaim();
        System.out.print(claim.toString());
    }
}