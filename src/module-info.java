module lab1 {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens Controllers to javafx.fxml;
    opens FXMLFiles to javafx.fxml;
    opens Model to javafx.fxml;
    opens Repo to javafx.fxml;
    opens UI to javafx.fxml;

    exports Controllers;
    exports sample;
    exports UI;
}