package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static Controllers.Controller.generateRandomNumbers;

/**
 * Die Funktion "generateRandomNumbers" erstellt eine Liste mit 26 zufällige ganze Zahlen aus einem gegebenen Intervall.
 * min - Unterkante des Intervalls >=  1
 * max - Oberkante des Intervalls <= 40
 * Gibt eine sortierte Liste mit 26 verschiedenen Zahlen aus dem Intervall [min,max] zurück.
 */
class RandomNumbersTests {
    List<Integer> list1;

    @BeforeEach
    void init() {
        this.list1  = generateRandomNumbers(1, 40);
    }

    // ECP
    @Test
    void generateRandomNumbersECP() {
        // VALID
        // EC1 - Intervals margins 1 and 40 should return an unempty list
        assertNotEquals(0, list1.size());

        // EC2 - Returned list contains only different values
        Set<Integer> list2 = new HashSet<>(list1);
        assertEquals(list1.size(), list2.size());

        // EC3 - Returned list contains exactly 26 different values
        assertEquals(26, list1.size());

        // NON-VALID
        // EC4 - Interval boundaries outside the interval [1, 40]
        assertEquals(0, generateRandomNumbers(-10, 55).size());

        // EC5 - Interval length is lower than 26
        assertEquals(0, generateRandomNumbers(5, 10).size());
    }

    // BVA
    @Test
    void generateRandomNumbersBVA() {

    }
}