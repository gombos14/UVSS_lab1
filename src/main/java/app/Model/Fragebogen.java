package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse fur Fragebogen
 */
public class Fragebogen {
    private int id;
    private int nr;
    private int wrongAnswers;
    private int correctAnswers;
    private List<Frage> l;

    /* Consturcter */
    public Fragebogen(int id, int nr) {
        this.id = id;
        this.nr = nr;
        this.l = new ArrayList<Frage>();
        this.wrongAnswers = 0;
        this.correctAnswers = 0;
    }


    /* Getters */
    public int getId() {
        return id;
    }

    public int getNr() {
        return nr;
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

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setFrage(List<Frage> l) {
        this.l = l;
    }
}
