package com.example.NoCodePlatform.helper;

public class StringHelper {

    public static String fileNameCompatible(String word) {
        if (word == null || word.trim().isEmpty()) {
            return "";
        }
        String[] parts = word.trim().toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    result.append(part.substring(1));
                }
            }
        }
        return result.toString();
    }


    
    public static String camelCase(String word) {
        if (word == null || word.trim().isEmpty()) {
            return "";
        }
        String[] parts = word.trim().toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                if (i == 0) {
                    result.append(part); // first word in lowercase
                } else {
                    result.append(Character.toUpperCase(part.charAt(0)));
                    if (part.length() > 1) {
                        result.append(part.substring(1));
                    }
                }
            }
        }

        return result.toString();

    }
}
