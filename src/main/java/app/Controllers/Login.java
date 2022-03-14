package Controllers;

import UI.UI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasse fur Einloggen
 */
public class Login implements Initializable {
    @FXML
    private Button login; //Login-Knopf

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            //setup
    }

    /**
     * - Login wird nur dann durchgefuhrt, wenn Name sowohl auch Vorname eingegben wurden
     * - Wenn Name und Vorname verschieden von "" sind,
     *      dann wird ein Quiz erstellt und das affernte Fenster des Fragebogens wird erscheinen
     */
    public void login() {
        if(firstName.getText().equals("") || lastName.getText().equals("")) {
            message.setText("Login failed");
        } else {
            Stage stage = (Stage)login.getScene().getWindow();
            stage.close();
            try {
                //Quiz erstellen
                UI.createQuiz(stage);
            } catch(Exception E) {
                E.printStackTrace();
            }
        }
    }
}
