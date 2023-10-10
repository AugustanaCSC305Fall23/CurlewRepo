package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LessonPlanController {

    @FXML
    private Button backButton;
    @FXML
    private Button doneButton;
    @FXML private Button threeCardConfigButton;
    @FXML private Button fourCardConfigButton;
    @FXML private Button fiveCardConfigButton;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();
    }

    @FXML
    void doneButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    void handleThreeButtonClick(ActionEvent event) {
        threeCardConfigButton.setStyle("-fx-background-color: green;");
        fourCardConfigButton.setStyle("-fx-background-color: red;");
        fiveCardConfigButton.setStyle("-fx-background-color: red;");
    }
    @FXML
    void handleFourButtonClick(ActionEvent event) {
        threeCardConfigButton.setStyle("-fx-background-color: red;");
        fourCardConfigButton.setStyle("-fx-background-color: green;");
        fiveCardConfigButton.setStyle("-fx-background-color: red;");
    }
    @FXML
    void handleFiveButtonClick(ActionEvent event) {
        threeCardConfigButton.setStyle("-fx-background-color: red;");
        fourCardConfigButton.setStyle("-fx-background-color: red;");
        fiveCardConfigButton.setStyle("-fx-background-color: green;");
    }
}