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

    public boolean addFrage(Frage frage) {
        for(Frage f: this.l)
            if(f.getId() == frage.getId()) // die frage existiert schon
                return false;
        this.l.add(frage);
        return true;
    }

    public void updateFrage(int id, Frage newFrage) {
        for(int i = 0; i < this.l.size(); i++)
            if(this.l.get(i).getId() == id) {
                this.l.set(i, newFrage);
                break;
            }

        this.l.add(newFrage);
    }

    public boolean removeFrage(int id) {
        for(int i = 0; i < this.l.size(); i++)
            if(this.l.get(i).getId() == id) {
                this.l.remove(i);
                return true;
            }
        return false;
    }
}
