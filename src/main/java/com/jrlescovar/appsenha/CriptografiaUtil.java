package com.jrlescovar.appsenha;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class CriptografiaUtil {

    private static final String CHAVE = "1234567890abcdef"; // exatamente 16 caracteres

    public static String criptografarAES(String texto) {
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] encrypted = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar", e);
        }
    }

    public static String descriptografarAES(String textoCriptografado) {
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, chave);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(textoCriptografado));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar", e);
        }
    }

    public static String hashSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1) hex.append('0');
                hex.append(h);
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
