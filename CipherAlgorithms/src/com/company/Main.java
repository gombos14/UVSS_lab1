package com.company;
import com.company.RSA.RSA;


public class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA(11413, 3533, "HOWAREYOU");
        System.out.println(rsa);
    }
}
