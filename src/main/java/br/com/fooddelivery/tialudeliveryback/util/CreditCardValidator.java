package br.com.fooddelivery.tialudeliveryback.util;

import java.time.YearMonth;
import java.util.Locale;

public class CreditCardValidator {

    public static boolean luhnCheck(String cardNumber) {
        if (cardNumber == null) return false;
        String s = cardNumber.replaceAll("\\s+", "");
        if (!s.matches("\\d+")) return false;
        int sum = 0;
        boolean alternate = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(s.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) n = (n % 10) + 1;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10) == 0;
    }
public static boolean expiryValid(int month, int year) {
        try {
            YearMonth card = YearMonth.of(year, month);
            YearMonth now = YearMonth.now();
            return card.isAfter(now) || card.equals(now);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean cvvValid(String cvv) {
        if (cvv == null) return false;
        return cvv.matches("\\d{3,4}");
    }

    public static boolean tipoCartaoValido(String tipo) {
        if (tipo == null) return false;
        String t = tipo.trim().toUpperCase(Locale.ROOT);
        return "CREDITO".equals(t) || "DEBITO".equals(t);
    }

    public static String detectBrand(String cardNumber) {
        if (cardNumber == null) return "Unknown";
        String s = cardNumber.replaceAll("\\s+", "");
        if (s.startsWith("4")) return "Visa";
        if (s.matches("^5[1-5].*") || s.startsWith("222") || s.startsWith("23"))
            return "Mastercard";
        if (s.startsWith("34") || s.startsWith("37")) return "Amex";
        return "Unknown";
    }

    public static String last4(String cardNumber) {
        if (cardNumber == null) return "";
        String s = cardNumber.replaceAll("\\s+", "");
        if (s.length() >= 4) return s.substring(s.length() - 4);
        return s;
    }

    public static boolean isValid(String cardNumber) {
        return luhnCheck(cardNumber);
    }

    public static boolean isValidCvv(String cvv) {
        return cvvValid(cvv);
    }

    public static String last4Digits(String cardNumber) {
        return last4(cardNumber);
    }

}
