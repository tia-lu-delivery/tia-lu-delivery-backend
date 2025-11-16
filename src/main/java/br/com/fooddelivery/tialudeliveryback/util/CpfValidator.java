package br.com.fooddelivery.tialudeliveryback.util;

public class CpfValidator {
    public static boolean isValid(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;
        return true; 
    }
}
