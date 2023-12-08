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
    private Course course;

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
    private void recentButtonHandle(ActionEvent event){GymnasticsAppBeta.switchToRecentPlans();}

    /**
     * Handles the action when "Template" button is clicked
     *
     * @param event The ActionEvent triggered by clicking the "Template" button
     */
    @FXML
    private void handleTemplateButton(ActionEvent event) {
        GymnasticsAppBeta.switchToTemplatePage();
    }

    /**
     * Handles the action when the "Template" button is clicked
     * @param event
     */
    @FXML
    private void handleAboutButton(ActionEvent event) {GymnasticsAppBeta.switchToAboutPage(); }
    /**
     * Handles the action when the "Load Lesson" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Load Lesson" button.
     */
    @FXML
    public void loadLessonPlanButtonHandle(ActionEvent event) {
        loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser();
        if(GymnasticsAppBeta.getUserClickedCancel() == false) {
            ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readLessonPlan();
            loadPlan.loadPlanFromFile(loadedLessonPlan);
            GymnasticsAppBeta.setLessonPlan(loadPlan);
            GymnasticsAppBeta.getLessonPlan().printEverything();
            if (GymnasticsAppBeta.getLoaded() == true) {
                GymnasticsAppBeta.switchToPreviewPage();
            }
        }
        GymnasticsAppBeta.setUserClickedCancel(false);
    }

    /**
     * Handles the action when the "Load Course" button is clicked
     *
     * @param event The ActionEvent triggered by clicking the "Load Course" button
     */
    @FXML
    public void loadCourseButtonHandle(ActionEvent event){
        course = new Course();
        GymnasticsAppBeta.callFileChooser();
        if(GymnasticsAppBeta.getUserClickedCancel() == false) {
            ArrayList<String> loadedCoursePlan = GymnasticsAppBeta.readLessonPlan();
            course.loadEverythingFromFile(loadedCoursePlan);
            GymnasticsAppBeta.setCourse(course);
            GymnasticsAppBeta.switchToCourseEditPage();
        }
        GymnasticsAppBeta.setUserClickedCancel(false);
    }

    /**
     * Retrieves the loaded lesson plan
     *
     * @return The loaded lesson plan
     */
    public static LessonPlan getloadPlan() {
        return loadPlan;
    }

}
