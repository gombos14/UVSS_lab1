package Controllers;

import UI.UI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import Repo.*;
import Model.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Klasse fur Controller
 */
public class Controller implements Initializable{

    @FXML
    private CheckBox answer1; //für das erste Antwortfeld

    @FXML
    private CheckBox answer2; //für das zweite Antwortfeld

    @FXML
    private CheckBox answer3; //für das dritte Antwortfeld

    @FXML
    private Label question; //die Frage

    @FXML
    private Label imageLabel; //das Bild der angehörigen Frage

    @FXML
    private GridPane bottom;

    private final FragenRepository repo = new FragenRepository();
    private Fragebogen f;
    private static int nr = 0 , id = 0;
    private int questionNo = 0;
    private int answersNo = 0;
    private final int[] a = new int[26];

    private Timeline timer;

    /**
     * Initialisierung der App/Anwendung
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //StartUp
        for(int i = 0; i <26; i++) {
            a[i] = 0;
        }
        //liest die Fragen
        repo.readQuestions();
        //generiert den Fragebogen (random 26 Fragen aus allen)
        f = generateFragebogen(id, nr);
        id = id + 1;
        nr = nr + 1;
        //Frage ausgeben
        loadQuestion();
        //Bild anzeigen
        loadImage();
        //Timer erstellen
        createTimer();
    }

    /**
     * Frage und ihre Antwortoptionen anzeigen
     *    -wenn die Frage nur 2 Antwortoptionen hat, dann wird dat dritte Antwortfeld-answer3 nicht sichtbar
     */
    public void loadQuestion() {
        question.setText(f.getFrage().get(questionNo).getQuestion());
        answer1.setText(f.getFrage().get(questionNo).getAnswers().get(0).getKey());
        answer2.setText(f.getFrage().get(questionNo).getAnswers().get(1).getKey());
        try {
            answer3.setText(f.getFrage().get(questionNo).getAnswers().get(2).getKey());
            this.answer3.setVisible(true);
        } catch(Exception e) {
            this.answer3.setVisible(false);
        }
    }

    /**
     * Erstellt eine Liste mit 26 zufallige ganze Zahlen aus einem gegebene Intervall
     * @param min - UnterKante des Intervalls - in dieser Anwendung 1
     * @param max - OberKante des Intervalls - totale Anzahl von Fragen (40)
     * @return - eine sortierte Liste mit 26 Zahlen aus den Intervall [min,max] = [1,40]
     */
    public static List<Integer> generateRandomQuestions(int min, int max) {
        if(min < 1 || max > 40 || max - min + 1 < 26) {
            return new ArrayList<>();
        }
        return generateRandomNumbers(26, min, max);
    }


    public static List<Integer> generateRandomNumbers(int ct, int min, int max) {
        List<Integer> l = new ArrayList<>();
        if(min > max || max - min + 1 < ct)
            return l;

        Random rand = new Random();
        int bound = max - min + 1;
        for(int i = 0; i < ct; i++) {
            int questionNum =  rand.nextInt(bound) + min;
            while(l.contains(questionNum))
                questionNum = rand.nextInt(bound) + min;
            l.add(questionNum);
        }
        Collections.sort(l);
        return l;
    }

    /**
     * Generiert einen Fragebogen mit Hilfe der Methode generateRandomNumbers
     * @param id - id Fragebogen
     * @param nummer - Nummer Fragebogen
     * @return - einen Fragebogen mit 26 zufalligen Fragen
     */
    public Fragebogen generateFragebogen(int id, int nummer) {
        List<Integer> l = generateRandomQuestions(1, 40);
        Fragebogen f = new Fragebogen(id, nummer);
        for(int x: l) {
            f.getFrage().add(this.repo.getList().get(x - 1));
        }
        return f;
    }

    /**
     * alle Antwortkästchen abwahlen
     */
    public void deselectAnswers() {
        this.answer1.setSelected(false);
        this.answer2.setSelected(false);
        this.answer3.setSelected(false);
    }

    /**
     * Die Antworten/Antwortfelder auf eine Frage markieren und abgeben
     */
    public void markieren(){
        //Anzahl beanworteten Zahlen mit 1 erhohen
        answersNo++;
        Button btn = (Button)bottom.getChildren().get(questionNo);
        //Wenn die Antwort/Antworten richting sind, dann erhoht man die Anzahl der Richtigen Antworten mit 1
        if(assertFirstAnswer() && assertSecondAnswer() && assertThirdAnswer()) {
            f.setCorrectAnswers(f.getCorrectAnswers() + 1);
            btn.getStyleClass().add("correctAnswer");
        }
        //Wenn die Antwort/Antworten falsch sind, dann erhoht man die Anzahl der Falschen Antworten mit 1
        else {
            f.setWrongAnswers(f.getWrongAnswers() + 1);
                // Wenn es bei 5 Fragen falsch beantwortet wurde, dann wird der Fragebogen beendet
                if(f.getWrongAnswers() >= 5)
                    abgeben();
            btn.getStyleClass().add("incorrectAnswer");

        }
        a[questionNo] = 1;
        //Wenn Anzahl von totalen Antworten streng kleiner als 26 is, dann wird die nachste Frage augezeigt
        if(answersNo <= 25) {
            deselectAnswers();
            fetchNext();
            loadQuestion();
            loadImage();
        }
        //Wenn man schon bei 26 Fragen beantwortet hat, dann ist der Quiz fertig,
        // und man hat bestanden (da man nicht aus dem Fragebogen herausgeworfen wurde wegen der 5 falschen Antworten)
        else {
            System.out.println("Finish");
        }
    }

    /**
     * Das zugehorige Bild einer Frage wird ausgeben, wenn der Frage kein Bild zugwiesen wurde,
     * dann wird kein Bild erscheinen
     */
    public void loadImage() {
        String imageLocation = f.getFrage().get(questionNo).getImageLocation();
        if(!imageLocation.equals("")) {
            Image image = new Image(imageLocation);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            //damit alle Bilder die selben Dimensionen bzw Hohen haben
            imageView.setFitHeight(380);
            imageView.setPreserveRatio(true);
            this.imageLabel.setGraphic(imageView);
        } else {
            this.imageLabel.setGraphic(null);
        }
    }

    /**
        Merges two sorted arrays with the same length in one single array.
        arr1 - first array
        arr2 - second array
        n - length of arrays
        Returns the sorted
     */
    public static int[] mergeArrays(int[] arr1, int[] arr2, int n)
    {
        int i = 0, j = 0, k = 0;
        int[] arr3 = new int[2 * n];
        while (i < n && j < n) {
            if (arr1[i] < arr2[j])
                arr3[k] = arr1[i++];
            else
                arr3[k] = arr2[j++];
            k++;
        }
        while (i < n)
            arr3[k++] = arr1[i++];
        while (j < n)
            arr3[k++] = arr2[j++];
        return arr3;
    }

    /**
     * Überprüfent die Richtigkeit des ersten Antwortfelds (CheckBox)
     * @return -true - wenn richtig gewahlt
     *         -false - wenn falsch gewahlt
     */
    private boolean assertFirstAnswer() {
        boolean selected = answer1.isSelected();
        int key = f.getFrage().get(questionNo).getAnswers().get(0).getValue();
        return ((selected && key == 1) || (!selected && key == 0));
    }

    /**
     * Überprüfent die Richtigkeit des zweitens Antwortfelds (Checkbox)
     * @return -true - wenn richtig gewahlt
               -false - wenn falsch gewahlt
     */
    private boolean assertSecondAnswer() {
        boolean selected = answer2.isSelected();
        int key = f.getFrage().get(questionNo).getAnswers().get(1).getValue();
        return ((selected && key == 1) || (!selected && key == 0));
    }

    /**
     * Überprüfent die Richtigkeit des dritten Antwortfelds (Checkbox)
     * @return -true - wenn richtig gewahlt
     *         -false - wenn falsch gewahlt
     */
    private boolean assertThirdAnswer() {
        if(!answer3.isVisible())
            return true;
        boolean selected = answer3.isSelected();
        int key = f.getFrage().get(questionNo).getAnswers().get(2).getValue();
        return ((selected && key == 1) || (!selected && key == 0));
    }

    /**
     * Eine Neue Frage wird gegeben, wenn Anzahl beantwortete Fragen kleiner als 25 ist
     */
    public void weiter() {
        if(answersNo < 25) {
            if(questionNo < 25)
                questionNo++;
            else
                questionNo = 0;
            //die Antwortsfeleder abwahlen
            deselectAnswers();
            fetchNext();
            loadQuestion();
            loadImage();
        }
    }

    /**
     * Anzahl Fragen wird erhoht, nur wenn es noch Fragen gibt und die Anzahl > = 25 ist
     */
    private void fetchNext() {
        while (a[questionNo] != 0) {
            if (questionNo >= 25)
                questionNo = 0;
            else
                questionNo++;
        }
    }

    /**
     * Wenn der Fragebogen fertig ist, dann wird man durch die Methode createResults aus UI
     * die Resultate anzeigen
     */
    public void abgeben() {
        Stage stage = (Stage)bottom.getScene().getWindow();
        stage.close();
        try {
            //Anzahl Richtige Antworten
            int correct = f.getCorrectAnswers();
            //Anzahl Falsche Antworten
            int wrong = f.getWrongAnswers();
            //Anzahl Unbeantwortet Antworten
            int unans = 26 - f.getWrongAnswers() - f.getCorrectAnswers();
            //Die vergangene Zeit
            Duration timerDuration = timer.getCurrentTime();
            String time = formatTime(timerDuration);

            UI.createResults(stage, correct, wrong, unans, time);
        } catch(Exception E) {
            E.printStackTrace();
        }
    }

    /**
     * Gibt eine Frage aus, auf dessen Nummer man clickt und noch nicht gelost wurde
     * @param E - Action Event : Drucken auf der gewahlten Frage
     */
    public void buttonPressed(ActionEvent E) {
        Button btn = (Button)E.getSource();
        String id = btn.getText();
        try {
            int n = Integer.parseInt(id);
            if(a[n - 1] == 0) {
                this.questionNo = n - 1;
            }
            loadQuestion();
            loadImage();
        } catch (Exception ignored) {

        }
    }

    /**
     * Timer Erstellen
     */
   public void createTimer(){

        //Timer wird auf 30 Minuten gestellt
        timer = new Timeline(new KeyFrame(
               Duration.minutes(30),
               Event -> {
                   try {
                       //Wenn 30 Minuten vergangen sind, dan wird der Fragebogen beendet
                      abgeben();
                   }
                   catch(Exception ignored){}
                   }
               ));
       timer.play();

       //Countdown anzeigen wahrend der Quiz gelost wird
       Timeline countdown = new Timeline(new KeyFrame(
               //für jede vergangene Sekunde wird man die ubrige Zeit angezeigen
               Duration.seconds(1),
               Event -> {
                   try {
                       Stage stage = (Stage)bottom.getScene().getWindow();
                       Duration time = timer.getCurrentTime();
                       //aus der Totalen Zeit die man fur den Quiz hat, also 30 Minuten,
                       //wird die schon verlaufenen Zeit subtrahiert
                       time = Duration.minutes(30).subtract(time);
                       //der Countdown wird Oben im Titel, neben "Quiz" stehen
                       stage.setTitle("Quiz " + formatTime(time));
                   }
                   catch(Exception ignored){}

               }
       ));
       countdown.setCycleCount(Timeline.INDEFINITE);
       countdown.play();
   }

    /**
     * Wandelt eine Duration in Format "mm:ss" um (Minuten:Sekunden)
     * @param time - die Duration die man umwandeln will
     * @return - SimpleDateFormat der Form "mm:ss"
     */
   private String formatTime(Duration time){
       int millis = (int)time.toMillis();
       SimpleDateFormat durationFormat = new SimpleDateFormat("mm:ss");
       return durationFormat.format(millis);
   }
}
