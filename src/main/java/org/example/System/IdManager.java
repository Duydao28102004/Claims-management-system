package org.example.System;
import java.util.Random;
public class IdManager {
    public static String generateId(int lenght) {
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
