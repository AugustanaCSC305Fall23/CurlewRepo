package csc305.gymnasticsApp;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
/**
 * The HomePageController class is responsible for handling user interactions and events on the home page of the application.
 */
public class HomePageController {
    @FXML private Button NewLessonButton;
    @FXML private Button LoadLessonButton;

    /**
     * Handles the action when the "New Lesson" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "New Lesson" button.
     */
    @FXML
    private void handleNewLessonButton(ActionEvent event){
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    /**
     * Handles the action when the "Load Lesson" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Load Lesson" button.
     */
    @FXML
    private void handleLoadLessonButton(ActionEvent event) {
        GymnasticsAppBeta.callFileChooser();
        GymnasticsAppBeta.setPreviewPage();
        GymnasticsAppBeta.switchToPreviewPage();
    }

}
