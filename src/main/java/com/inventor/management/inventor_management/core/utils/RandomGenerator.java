package com.inventor.management.inventor_management.core.utils;

import java.util.Random;

public class RandomGenerator {
    public static String generateRandomCode(int length) {
        var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        var random = new Random();
        var code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            code.append(
                    characters.charAt(
                            random.nextInt(characters.length())
                    )
            );
        }

        return code.toString();
    }
}
