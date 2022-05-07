package com.company.Shift;

public class Shift {
    private final String text;
    private final int key;

    public Shift(String text, int key) {
        this.text = text;
        this.key = key;
    }

    public String decrypt() throws Exception {
        StringBuilder plaintext = new StringBuilder();
        for (char c : this.text.toCharArray()) {
            if ('A' <= c && c <= 'Z') { // capital letter
                plaintext.append((char) ('A' + (26 + (c - 'A' - this.key)) % 26));
            } else if ('a' <= c && c <= 'z') {
                plaintext.append((char) ('a' + (26 + (c - 'a' - this.key)) % 26));
            } else throw new Exception("Unknown character " + c);
        }
        return plaintext.toString();
    }
}
