package UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Klasse fur User Interface
 */
public class UI {
    /**
     * Erstellt und zeigt das Anmeldefenster an
     *      -mit Hilfe von login.fxml aus FXMLFiles
     * @param stage - die Stage aus der Main Klasse (primaryStage)
     * @throws Exception - wenn etwas schief gegangen ist
     */
    public static void createLoginForm(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(UI.class.getResource("F:\\Krisztian\\uvss\\lab1\\src\\FXMLFiles\\login.fxml"));
        //Titel des Anmeldefeldes setieren
        stage.setTitle("Login");
        //Scene von root sichtbar machen mit setScene
        stage.setScene(new Scene(root));
        //stellt den Icon/Symbol ein mit Hilfe vom Bild logo.png
        stage.getIcons().add(new Image(UI.class.getResourceAsStream("/images/logo.png")));
        //zeigt den erstellten Fenster/Feld
        stage.show();
    }

    /**
     * Erstellt und zeigt das Fenster fur den Quiz an
     *       -mit Hilfe von sample.fxml aus FXMLFiles
     * @param stage - die Stage
     * @throws Exception - wenn etwas schief gegangen ist
     */
    public static void createQuiz(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(UI.class.getResource("F:\\Krisztian\\uvss\\lab1\\src\\FXMLFiles\\sample.fxml"));
        root.getStylesheets().add("/stylesheets/application.css");
        stage.setTitle("Quiz");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(UI.class.getResourceAsStream("/images/logo.png")));
        //zeigt den erstellten Fenster
        stage.show();
    }

    /**
     * Erstellt und zeigt das Ergebnisfenster an
     *       -mit Hilfe von results.fxml aus FXMLFiles
     * @param stage - die Stage
     * @throws Exception - wenn etwas schief gegangen ist
     */
    public static void createResults(Stage stage, int correct, int wrong, int unans, String time) throws Exception {
        Parent root = FXMLLoader.load(UI.class.getResource("F:\\Krisztian\\uvss\\lab1\\src\\FXMLFiles\\results.fxml"));
        stage.setTitle("Quiz");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(UI.class.getResourceAsStream("/images/logo.png")));
        //zeigt den erstellten Fenster
        stage.show();
        for(Node n: root.getChildrenUnmodifiable()) {
            try {
                GridPane grid = (GridPane) n;
                for (Node node : grid.getChildren()) {
                    try {
                        Label label = (Label) node;
                        switch (label.getId()) {
                            case "correctAnswers":
                                //Legt die Anzahl der richtigen Antworten und das entsprechende Feld fest
                                label.setText("Correct answers: " + correct);
                                break;
                            case "incorrectAnswers":
                                //Legt die Anzahl der falschen Antworten und das entsprechende Feld fest
                                label.setText("Wrong answers: " + wrong);
                                break;
                            case "unans":
                                //Legt die Anzahl der unbeantworteten Antworten und das entsprechende Feld fest
                                label.setText("Unanswered questions: " + unans);
                                break;
                            case "time":
                                //Legt die Gesamtausführungszeit fest
                                label.setText("Time passed: " + time);
                                break;
                        }
                    }catch(Exception ignored) {

                    }
                }
            } catch (Exception ignored) {

            }
        }
    }
}

