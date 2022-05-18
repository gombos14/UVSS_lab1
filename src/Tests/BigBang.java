package Tests;

import Model.Answer;
import Model.Frage;
import Repo.FragenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BigBang {
    private FragenRepository repo;

    @BeforeEach
    public void init() {
        // Init question repository
        this.repo = new FragenRepository();
        this.repo.addFrage(new Frage(1000, "1 + 2 + 3 ="));
        this.repo.addFrage(new Frage(1001, "1 * 2 * 3 ="));
    }

    // Unit Testing
    @Test
    public void testAddFrage() {
        Frage f = new Frage(0, "1 + 1 = ");
        f.addAnswer(new Answer("3" , false));
        f.addAnswer(new Answer("2", true));

        // Check actual number of question in the repository
        assertEquals(this.repo.getList().size(), 2);

        // Assert a new question was added to the repo
        assertTrue(this.repo.addFrage(f));

        // Assert the total number of questions was incremented
        assertEquals(this.repo.getList().size(), 3);
    }

    @Test
    public void testUpdateFrage() {
        Frage newFrage = new Frage(1, "3 * 4 =");
        newFrage.addAnswer(new Answer("12", true));
        newFrage.addAnswer(new Answer("13", false));

        // Update an existing question
        repo.updateFrage(1000, newFrage);

        // Assert the question before updating doesn't exist anymore
        assertNull(repo.getFrageById(1000));

        // Assert the updated question exists in the repo
        assertNotNull(repo.getFrageById(1));
    }

    @Test
    public void testRemoveFrage() {
        assertTrue(repo.removeFrage(1000));
        assertNull(repo.getFrageById(1000));
        assertEquals(repo.getList().size(), 1);
    }

    @Test
    public void testPABC() {
        Frage newFrage = new Frage(1, "2 ^ 2");
        newFrage.addAnswer(new Answer("4", true));
        newFrage.addAnswer(new Answer("8", false));

        // Add a new question to the repository
        this.repo.addFrage(newFrage);

        // Update the question
        Frage updatedFrage = new Frage(1, "2 * 2");
        this.repo.updateFrage(1, updatedFrage);

        // Remove the question from repository
        this.repo.removeFrage(1);

        // Assert the question cannot be deleted again
        assertFalse(this.repo.removeFrage(1));

        // Assert the question is no longer in repository
        assertNull(this.repo.getFrageById(1));
    }
}
