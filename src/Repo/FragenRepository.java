package Repo;
import Model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FragenRepository {
    List<Frage> l; //Liste von Fragen

    /* Constructor */
    public FragenRepository() {
        this.l = new ArrayList<>();
    }

    /* Getters */
    public List<Frage> getList() {
        return l;
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
                    f.setId(number);
                } else if(line.contains("?")) {
                    assert f != null;
                    f.setQuestion(line);
                } else if(line.charAt(0) == '!') {
                    assert f != null;
                    f.getAnswers().add(new Answer(line.substring(1), true));
                } else if(line.contains(".jpg")) {
                    assert f != null;
                    f.setImageLocation("images/" + line);
                } else {
                    assert f != null;
                    f.getAnswers().add(new Answer(line, false));
                }
                line = in.readLine();
            }
            this.l.add(f);
        } catch(Exception e) {
            System.out.println("There was a problem reading the file");
        }
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
                return;
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

    public Frage getFrageById(int id) {
        for(Frage f: this.l)
            if(f.getId() == id)
                return f;
        return null;
    }
}
