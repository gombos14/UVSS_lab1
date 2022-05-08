package Tests;

import Model.Frage;
import Repo.FragenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BigBang {
    private FragenRepository repo;
    private List<Frage> fragen;

    @BeforeEach
    public void init() {
        repo = new FragenRepository();
        repo.readQuestions();
        this.fragen = repo.getList();
    }

}
