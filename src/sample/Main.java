package sample;

import UI.UI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        UI.createLoginForm(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
