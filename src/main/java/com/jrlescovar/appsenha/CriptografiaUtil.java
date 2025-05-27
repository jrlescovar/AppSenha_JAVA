package com.jrlescovar.appsenha;

import java.security.MessageDigest;

public class CriptografiaUtil {

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

    public static boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
        String senhaHash = hashSHA256(senhaDigitada);
        return senhaHash.equals(senhaCriptografada);
    }
}
