package com.company.Analog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnalogCipher {
    private final List<String> signals = new ArrayList<>();
    private final HashMap<Integer, String> binaryValues = new HashMap<>();

    AnalogCipher(int k) {
        this.generateBinaryValues(k);
        this.generatePositiveSignals(k, 0);
        this.generateNegativeSignals(k, 1 << (k-1));
    }

    /*
        Generates a map with all the values from 0 to 2^k - 1 and
        their corresponding binary representation using k bits
    */
    private void generateBinaryValues(int k) {
        StringBuilder prefix = new StringBuilder("0");
        prefix.append("0".repeat(Math.max(0, k)));

        for(int i = 0; i < (1 << k); i++) {
            String value = prefix + Integer.toBinaryString(i);
            int len = value.length();
            this.binaryValues.put(i, value.substring(len - k, len));
        }
    }

    /*
        Generates positive signals (above the Ox Axis) using a recursive function
        k - number of bits
        i - current signal
        Positive signals are in range [0, 2^(k-1) - 1]
    */
    private void generatePositiveSignals(int k, int i) {
        if(i < (1 << (k - 1))) {
            signals.add(this.binaryValues.get(i));
            this.generatePositiveSignals(k, i + 1);
            signals.add(this.binaryValues.get(i));
        }
    }

    /*
        Generates negative signals (below the Ox axis) using a recursive function
        k - number of bits
        i - current signal
        Negative signals are in range [2^(k-1), 2^k - 1]
    */
    private void generateNegativeSignals(int k, int i) { // generate signals below Ox
        if(i < (1 << k)) { // negative values are in range [2^(k-1), 2^k - 1]
            signals.add(this.binaryValues.get(i));
            this.generateNegativeSignals(k, i + 1);
            signals.add(this.binaryValues.get(i));
        }
    }

    /*
        Encrypts the signals using Caesar Cipher.
        Returns a list with the encrypted signals.
    */
    private List<String> encrypt() { // encrypt the signals using Caesar Cipher
        int size = this.binaryValues.size();
        List<String> encSignals = new ArrayList<>();
        for(String s: this.signals) {
            int value = Integer.parseInt(s, 2);
            encSignals.add(this.binaryValues.get((value + 3) % size));
        }
        return encSignals;
    }

    @Override
    public String toString() {
        return String.join(" ", this.encrypt());
    }
}
