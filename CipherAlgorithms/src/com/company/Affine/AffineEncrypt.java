package com.company.Affine;

public class AffineEncrypt {
    public static String encrypt(int a, int b, String plaintext) throws Exception {
        String ciphertext = "";
        for(char c: plaintext.toCharArray()) {
            if('A' <= c && c <= 'Z') {
                ciphertext += (char)('A' + (a * (c - 'A') + b) % 26);
            } else if('a' <= c && c <= 'z') {
                ciphertext += (char)('a' + (a * (c - 'a') + b) % 26);
            } else {
                throw new Exception("Invalid character " + c);
            }
        }
        return ciphertext;
    }
}
