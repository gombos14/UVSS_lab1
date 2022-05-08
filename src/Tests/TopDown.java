package Tests;

import Model.Frage;
import Repo.FragenRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TopDown {
    private FragenRepository repo;
    private List<Frage> fragen;

    @BeforeEach
    public void init() {
        repo = new FragenRepository();
        repo.readQuestions();
        this.fragen = repo.getList();
    }
}