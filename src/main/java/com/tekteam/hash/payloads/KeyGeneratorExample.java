package com.tekteam.hash.payloads;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorExample {
    public static SecretKey generateAESKey() {
        try {
            // Create a KeyGenerator for AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            // Initialize the KeyGenerator with the key size (128 bits for AES)
            keyGenerator.init(128);

            // Generate the AES key
            SecretKey secretKey = keyGenerator.generateKey();

            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SecretKey aesKey = generateAESKey();

        if (aesKey != null) {
            byte[] encodedKey = aesKey.getEncoded();

            // Print the key as a byte array (for demonstration purposes)
            System.out.println("Generated AES Key (Hex): " + bytesToHex(encodedKey));
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
