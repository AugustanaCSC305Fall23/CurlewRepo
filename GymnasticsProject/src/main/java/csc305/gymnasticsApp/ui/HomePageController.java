package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * The HomePageController class is responsible for handling user interactions and events on the home page of the application.
 */
public class HomePageController {
    @FXML private Button NewLessonButton;
    @FXML private Button LoadLessonButton;

    static LessonPlan loadPlan;

    /**
     * Handles the action when the "New Lesson" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "New Lesson" button.
     */
    @FXML
    private void handleNewLessonButton(ActionEvent event){
        GymnasticsAppBeta.switchToMainEditDisplay();
    }
    @FXML
    private void handleTemplateButton(ActionEvent event) {
        GymnasticsAppBeta.switchToTemplatePage();
    }

    /**
     * Handles the action when the "Load Lesson" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Load Lesson" button.
     */
    @FXML
    private void handleLoadLessonButton(ActionEvent event) {
        loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readLessonPlan();
        loadPlan.loadPlanFromFile(loadedLessonPlan);
        if(GymnasticsAppBeta.getLoaded() == true) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    public static LessonPlan getloadPlan() {
        return loadPlan;
    }

}
