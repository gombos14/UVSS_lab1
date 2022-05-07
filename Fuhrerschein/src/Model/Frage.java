package Model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse fur Frage
 */
public class Frage {
    private String question;
    private List<Pair<String, Integer>> answers;
    private String imageLocation;
    private int number;

    /* Constructor */
    public Frage() {
        this.imageLocation = "";
        this.answers = new ArrayList<Pair<String, Integer>>();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Pair<String, Integer>> getAnswers() {
        return this.answers;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
