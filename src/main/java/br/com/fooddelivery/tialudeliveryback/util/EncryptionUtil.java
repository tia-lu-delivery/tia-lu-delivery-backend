package br.com.fooddelivery.tialudeliveryback.util;

import java.util.Base64;

public class EncryptionUtil {
    public static String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String decrypt(String encrypted) {
        return new String(Base64.getDecoder().decode(encrypted));
    }
}
