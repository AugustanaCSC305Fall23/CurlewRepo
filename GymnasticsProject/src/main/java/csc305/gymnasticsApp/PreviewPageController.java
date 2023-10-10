package csc305.gymnasticsApp;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;

public class PreviewPageController {

    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button printButton;

    @FXML
    private Button saveButton;

    @FXML
    void backButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    void homeButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToHomePage();
    }

    @FXML
    void printButtonController(ActionEvent event) {

    }

    @FXML
    void saveController(ActionEvent event) {

    }

}