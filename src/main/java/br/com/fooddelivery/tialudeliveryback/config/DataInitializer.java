package br.com.fooddelivery.tialudeliveryback.util;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenizerUtils {

    private static final byte[] KEY = "0123456789ABCDEF0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);

    private static final String ALGORITHM = "AES/GCM/NoPadding";

    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            byte[] iv = new byte[12];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            GCMParameterSpec gcm = new GCMParameterSpec(128, iv);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcm);

            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encrypted.length);
            byteBuffer.put(iv);
            byteBuffer.put(encrypted);

            return Base64.getEncoder().encodeToString(byteBuffer.array());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao tokenizar dados sensíveis", e);
        }
    }
}
