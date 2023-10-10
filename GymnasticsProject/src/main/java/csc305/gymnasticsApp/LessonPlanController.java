package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LessonPlanController {

    @FXML
    private Button backButton;
    @FXML
    private Button doneButton;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();
    }

    @FXML
    void doneButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToMainEditDisplay();
    }

}