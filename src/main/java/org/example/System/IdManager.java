package org.example.System;

/**
 * @author <Dao Bao Duy - s3978826>
 *     Adapted from: chatGPT, w3schools
 */

import java.util.Random;
public class IdManager {
    public static String generateId(int lenght) {
        // Generate a random id
        String digits = "0123456789";
        StringBuilder builder = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < lenght; i++) {
            int index = random.nextInt(digits.length());
            builder.append(digits.charAt(index));
        }

        return builder.toString();
    }
}
