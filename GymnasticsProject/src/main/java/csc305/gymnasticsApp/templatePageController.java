package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class templatePageController {
    @FXML
    private Button homeButton;
    @FXML
    void homeButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToHomePage();
    }
}
