package csc305.gymnasticsApp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AboutPageController {
    @FXML
    private Button homeButton;
    @FXML
    private void handleHomeButton(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();}
}
