package com.company.Utils;

import java.math.BigInteger;

public class Utils {
    public static BigInteger Big(int value) {
        return BigInteger.valueOf(value);
    }

    public static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    public static int phi(int n) {
        if(n < 1)
            return -1;
        int ct = 0;
        for(int i = 1; i < n; i++)
            if(gcd(i, n) == 1)
                ct++;
        return ct;
    }
}
