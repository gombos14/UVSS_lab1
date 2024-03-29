package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Controllers.Controller.generateRandomQuestions;
import static org.junit.jupiter.api.Assertions.*;

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
        this.list1  = generateRandomQuestions(1, 40);
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
        assertEquals(0, generateRandomQuestions(-10, 55).size());

        // EC5 - Interval length is lower than 26
        assertEquals(0, generateRandomQuestions(5, 10).size());
    }

    // BVA
    @Test
    void generateRandomNumbersBVA() {
        // NON-VALID
        // EC1 - Lower interval boundary is less than the boundary value
        assertEquals(0, generateRandomQuestions(0, 40).size());

        // VALID
        // EC2 - Lower interval boundary is equal to the boundary value
        assertEquals(26, generateRandomQuestions(1, 40).size());

        // VALID
        // EC3 - Lower interval boundary is greater than the boundary value
        assertEquals(26, generateRandomQuestions(2, 40).size());

        // VALID
        // EC4 - Upper interval boundary is less than the boundary value
        assertEquals(26, generateRandomQuestions(1, 39).size());

        // VALID
        // EC5 - Upper interval boundary is equal to the boundary value
        assertEquals(26, generateRandomQuestions(1, 40).size());

        // NON-VALID
        // EC6 - Upper interval boundary is greater than the boundary value
        assertEquals(0, generateRandomQuestions(1, 41).size());
    }
}