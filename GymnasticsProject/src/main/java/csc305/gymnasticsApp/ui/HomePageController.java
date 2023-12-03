package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Course;
import csc305.gymnasticsApp.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.util.ArrayList;

/**
 * The HomePageController class is responsible for handling user interactions and events on the home page of the application.
 */
public class HomePageController {
    @FXML private Button NewLessonButton;
    @FXML private Button loadLessonPlanButton;

    @FXML private Button aboutButton;
    @FXML
    private Button loadCourseButton;
    public static LessonPlan loadPlan;

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

    @FXML
    private void handleAboutButton(ActionEvent event) {GymnasticsAppBeta.switchToAboutPage(); }
    /**
     * Handles the action when the "Load Lesson" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Load Lesson" button.
     */
    @FXML
    public void loadLessonPlanButtonHandle(ActionEvent event) {
        //DOESNT CURRENTLY WORK CORRECTLY, THIS IMPORTS A LESSONPLAN, WE WANT TO IMPORT A WHOLE COURSE
        loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readLessonPlan();
        loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(loadPlan);
        GymnasticsAppBeta.getLessonPlan().printEverything();
        if(GymnasticsAppBeta.getLoaded() == true) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    @FXML
    public void loadCourseButtonHandle(ActionEvent event){
        GymnasticsAppBeta.callFileChooser();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readLessonPlan();
        Course.loadEverythingFromFile(loadedLessonPlan);
        GymnasticsAppBeta.switchToCourseEditPage();
    }


    public static LessonPlan getloadPlan() {
        return loadPlan;
    }

}
