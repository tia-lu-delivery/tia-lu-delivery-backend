package br.com.fooddelivery.tialudeliveryback.util;

import java.util.Base64;

public class TokenizerUtils {

    public static String tokenize(String cardNumber) {
        return Base64.getEncoder().encodeToString(cardNumber.getBytes());
    }
}
