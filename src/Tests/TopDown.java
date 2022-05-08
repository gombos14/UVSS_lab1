package Tests;

import Model.Answer;
import Model.Frage;
import Repo.FragenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TopDown {
    private FragenRepository repo;
    private List<Frage> fragen;

    @BeforeEach
    public void init() {
        this.repo = new FragenRepository();
        this.fragen = repo.getList();
        this.repo.addFrage(new Frage(1000, ""));
        this.repo.addFrage(new Frage(1001, ""));
    }

    // Unit Testing
    @Test
    public void testAddFrage() {
        Frage f = new Frage(0, "1 + 1 = ");
        f.addAnswer(new Answer("3" , false));
        f.addAnswer(new Answer("2", true));

        assertEquals(this.fragen.size(), 2);
        assertTrue(this.repo.addFrage(f));
        assertEquals(this.fragen.size(), 3);
    }

    @Test
    public void testUpdateFrage() {
        Frage newFrage = new Frage(1, "3 * 4 =");
        newFrage.addAnswer(new Answer("12", true));
        newFrage.addAnswer(new Answer("13", false));
        repo.updateFrage(1000, newFrage);
        assertNull(repo.getFrageById(1000));
        assertNotNull(repo.getFrageById(1));
    }

    @Test
    public void testRemoveFrage() {
        assertTrue(repo.removeFrage(1000));
        assertNull(repo.getFrageById(1000));
        assertEquals(repo.getList().size(), 1);
    }

    // Integration testing
    // P->A
    @Test
    public void PA() {
        // Delete One Question
        assertTrue(repo.removeFrage(1000));
        assertNull(repo.getFrageById(1000));
        assertEquals(repo.getList().size(), 1);
        assertFalse(repo.removeFrage(1000));

    }

    // P->A->B
    @Test
    public void PAB() {
        // Delete
        assertTrue(repo.removeFrage(1000));
        assertNull(repo.getFrageById(1000));
        assertEquals(repo.getList().size(), 1);

        // Update
        Frage frage = new Frage(2, "sqrt(2) = ");
        frage.addAnswer(new Answer("3", false));
        frage.addAnswer(new Answer("1.41", true));
        this.repo.updateFrage(1001, frage);
        assertNull(this.repo.getFrageById(1001));
        assertNotNull(this.repo.getFrageById(2));
        assertEquals(this.fragen.size(), 1);
    }

    // P->A->B->C
    @Test
    public void PABC() {
        // Delete
        assertTrue(repo.removeFrage(1000));
        assertNull(repo.getFrageById(1000));
        assertEquals(repo.getList().size(), 1);

        // Update
        Frage frage = new Frage(2, "sqrt(2) = ");
        frage.addAnswer(new Answer("3", false));
        frage.addAnswer(new Answer("1.41", true));
        this.repo.updateFrage(1001, frage);
        assertNull(this.repo.getFrageById(1001));
        assertNotNull(this.repo.getFrageById(2));
        assertEquals(this.fragen.size(), 1);

        // Add
        for(int i = 0; i < 500; i++)
            this.repo.addFrage(new Frage(2 * i + 1, ""));
        assertEquals(this.fragen.size(), 501);
        for(int i = 0; i < 500; i++)
            assertNotNull(this.repo.getFrageById(2 * i + 1));
    }
}
