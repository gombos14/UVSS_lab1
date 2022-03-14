package Repo;
import Model.*;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FragenRepository {
    List<Frage> l; //Liste von Fragen

    /* Constructor */
    public FragenRepository() {
        this.l = new ArrayList<Frage>();
    }

    /* Getters */
    public List<Frage> getList() {
        return l;
    }

    /* Setters */
    public void setList(List<Frage> l) {
        this.l = l;
    }

    /**
     * Liest jede Frage aus "fragen.txt" und fugt sie in der Liste von Fragen f ein
     */
    public void readQuestions() {
        Frage f = null;
        int number = 0;
        boolean ok;
        try {
            String filePath = new File("").getAbsolutePath();
            BufferedReader in = new BufferedReader(new FileReader(filePath + "/src/sample/fragen.txt"));
            String line = in.readLine();
            while(line != null) {
                try {
                    number =  Integer.parseInt(line);
                    ok = true;
                } catch(NumberFormatException e) {
                    ok = false;
                }
                if(ok) {
                    if(f != null)
                        this.l.add(f);
                    f = new Frage();
                    f.setNumber(number);
                } else if(line.contains("?")) {
                    assert f != null;
                    f.setQuestion(line);
                } else if(line.charAt(0) == '!') {
                    assert f != null;
                    f.getAnswers().add(new Pair<String, Integer>(line.substring(1), 1));
                } else if(line.contains(".jpg")) {
                    assert f != null;
                    f.setImageLocation("images/" + line);
                } else {
                    assert f != null;
                    f.getAnswers().add(new Pair<String, Integer>(line, 0));
                }
                line = in.readLine();
            }
            this.l.add(f);
        } catch(Exception e) {
            System.out.println("There was a problem reading the file");
        }
    }
}
