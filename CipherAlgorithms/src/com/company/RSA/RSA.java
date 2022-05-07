package com.company.RSA;

import com.company.Utils.Utils;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class RSA {
    private int n;
    private int b;
    private String plainText;

    public RSA(int n, int b, String plaintText) {
        this.n = n;
        this.b = b;
        this.plainText = plaintText;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public boolean checkConditions() {
        if (this.b <= 1 || this.b >= Utils.phi(n))
            return false;

        return Utils.gcd(this.b, Utils.phi(this.n)) == 1;
    }

    public List<BigInteger> Encrypt() {
        List<BigInteger> values = new ArrayList<>();
        if(!this.checkConditions())
            return null;

        for (char c : this.plainText.toCharArray()) {
            if (c < 'A' || c > 'Z')
                return null;
            BigInteger remainder = Utils.Big(c - 'A').pow(b).remainder(Utils.Big(n));
            values.add(remainder);
        }
        return values;
    }

    @Override
    public String toString() {
        List<BigInteger> values = this.Encrypt();
        StringBuilder message = new StringBuilder();
        if(values == null)
            return "Message couldn't be encrypted";

        for(BigInteger b: values)
            message.append(b.toString()).append(" ");
        return message.toString();
    }
}
