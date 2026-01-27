package com.github.sentinel.pay.domain.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeySecurity {
    private static final SecureRandom secureRandom = new SecureRandom();


    public static String hash(String raw){
      try {
          MessageDigest digest = MessageDigest.getInstance("SHA-256");
          byte[] hashedBytes = digest.digest(raw.getBytes());
          return Base64.getEncoder().encodeToString(hashedBytes);
        }catch(NoSuchAlgorithmException e){
          throw new RuntimeException(e.getMessage());
      }
    }

    public static String generate() {
        byte[] keyBytes = new byte[32]; // 256 bits
        secureRandom.nextBytes(keyBytes);
        String rawApiKey= Base64.getEncoder().encodeToString(keyBytes);
        return "sk_"+rawApiKey;
    }
}
