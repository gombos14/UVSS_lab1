package Controllers;

import UI.UI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasse fur Resultat
 */
public class Results implements Initializable {
    @FXML
    private Label correct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setup
    }

    /**
     * Erstellt einen neuen Quiz durch Aufrufen der Methode createQuiz aus UI
     */
    public void startNewQuiz() {
        try {
            Stage stage = (Stage)correct.getScene().getWindow();
            UI.createQuiz(stage);
        } catch(Exception ignored) {

        }
    }
}
