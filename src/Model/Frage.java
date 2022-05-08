package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse fur Frage
 */
public class Frage {
    private String question;
    private final List<Answer> answers;
    private String imageLocation;
    private int id;

    /* Constructor */
    public Frage() {
        this.question = "";
        this.imageLocation = "";
        this.answers = new ArrayList<>();
        this.id = 0;
    }

    public Frage(int id, String question) {
        this.imageLocation = "";
        this.answers = new ArrayList<>();
        this.id = id;
        this.question = question;
    }

    public void addAnswer(Answer ans) {
        this.answers.add(ans);
    }

    public void setId(int newId)  {
        this.id = newId;
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
