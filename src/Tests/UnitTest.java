package Tests;

import Model.Frage;
import Repo.FragenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTest {
    private FragenRepository repo;

    @BeforeEach
    public void init() {
        this.repo = new FragenRepository();
    }

    @Test
    public void TestAdd() {
        // Adding a new question to the repository should return true
        assertTrue(this.repo.addFrage(new Frage(1, "2 + 2 = ?")));

        // Add an existing question to the repository should return false
        assertFalse(this.repo.addFrage(new Frage(1, "2 + 2 = ?")));
    }


}
