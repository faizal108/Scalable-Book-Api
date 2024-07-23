package com.faizal108.userService.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHashing {
    public static String hashPassword(String plainTextPassword) throws NoSuchAlgorithmException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(plainTextPassword.toCharArray(), "thiissecretkey".getBytes(), 65536, 256);
        SecretKey key = null;
        try {
            key = factory.generateSecret(spec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        byte[] encoded = key.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }
}
