package com.example.springbootcrudoperation.securityConfig;


import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@UtilityClass
public class HashingUtils {

    private static final String HASHING_ALGORITHM = "SHA-256";

    @SneakyThrows
    public static String sha256(String plainText) {
        byte[] hash = MessageDigest.getInstance(HASHING_ALGORITHM).digest(plainText.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}