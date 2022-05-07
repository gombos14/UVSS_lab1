package com.company.Affine;

public class AffineDecrypt {
    private final int a;
    private final int b;
    private final String cipherText;

    public AffineDecrypt(int a, int b, String cipherText) {
        this.a = a;
        this.b = b;
        this.cipherText = cipherText;
    }

    private int getInverse(int number) {
        int inverse;
        for(inverse = 3; (number * inverse) % 26 != 1; inverse += 2);
        return inverse;
    }

    public String decrypt() throws Exception {
        StringBuilder plaintext = new StringBuilder();
        for(char c: this.cipherText.toCharArray()) {
            if('A' <= c && c <= 'Z') {
                plaintext.append((char) ('A' + ((c - 'A' - this.b + 26) * getInverse(this.a)) % 26));
            } else if('a' <= c && c <= 'z') {
                plaintext.append((char) ('a' + ((c - 'a' - this.b + 26) * getInverse(this.a)) % 26));
            } else {
                throw new Exception("Invalid character " + c);
            }
        }
        return plaintext.toString();
    }
}
