package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse fur Fragebogen
 */
public class Fragebogen {
    private int id;
    private int wrongAnswers;
    private int correctAnswers;
    private final List<Frage> l;

    /* Consturcter */
    public Fragebogen(int id) {
        this.id = id;
        this.l = new ArrayList<>();
        this.wrongAnswers = 0;
        this.correctAnswers = 0;
    }


    /* Getters */
    public int getId() {
        return id;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public List<Frage> getFrage() {
        return l;
    }

    /* Setters */
    public void setId(int id) {
        this.id = id;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
