package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.LessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * Controller class for the template page, responsible for handling template selections.
 */
public class TemplatePageController {
    @FXML
    private Button homeButton;
    @FXML
    private Button beginnerOnlyButtonOne;
    @FXML
    private Button maleOnlyButtonOne;
    @FXML
    private Button allFloorButton;
    @FXML
    private Button floorAndStrength;
    @FXML
    private Button varietyBeginner;
    @FXML
    private Button maleUpper;

    /**
     * Handles the action event for loading the all floor lesson plan.
     *
     * @param event The action event triggered by the button.
     */
    @FXML
    void handleAllFloorButton(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/allFloorTemplatePlan.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    /**
     * Handles the action event for loading the floor and strength lesson plan.
     *
     * @param event The action event triggered by the button.
     */
    @FXML
    void handleFloorAndStrength(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/floorAndStrengthTemplatePlan.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    /**
     * Handles the action event for loading the beginner-only lesson plan
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void handleBeginnerOnlyButtonOne(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/beginnerFloorAndTrampolinePlan.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    /**
     * Handles the action event for loading the variety beginner lesson plan.
     *
     * @param event The action event triggered by the button.
     */
    @FXML
    void handleVarietyBeginner(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/varietyBeginnerTemplatePlan.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    /**
     * Handles the action event for loading the male-only lesson plan
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void handleMaleOnlyButtonOne(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/maleOnlyLessonPlan.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    /**
     * Handles the action event for loading the male upper floor lesson plan.
     *
     * @param event The action event triggered by the button.
     */
    @FXML
    void handleMaleUpper(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/maleUpperFloorTemplatePlan.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    /**
     * Handles the action event for switching to the home page
     *
     * @param event The action event triggered by the button
     */
    @FXML
    void homeButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToHomePage();
    }

    /**
     * Initializes the controller
     */
    public void initialize(){
        Image allFloorImage = new Image("file:src/main/resources/templatePlans/allFloorPDF-1.png");
        initializeImages(allFloorImage, allFloorButton);
        Image floorAndStrengthImage = new Image("file:src/main/resources/templatePlans/beginnerFloorPDF-1.png");
        initializeImages(floorAndStrengthImage, floorAndStrength);
        Image beginnerFloorAndTrampolineImage = new Image("file:src/main/resources/templatePlans/beginnerFloorPDF-1.png");
        initializeImages(beginnerFloorAndTrampolineImage, beginnerOnlyButtonOne);
        Image varietyBeginnerImage = new Image("file:src/main/resources/templatePlans/varietyBeginnerPDF-1.png");
        initializeImages(varietyBeginnerImage, varietyBeginner);
        Image maleOnlyLessonPlanImage = new Image("file:src/main/resources/templatePlans/maleOnlyPDF-1.png");
        initializeImages(maleOnlyLessonPlanImage, maleOnlyButtonOne);
        Image maleUpperImage = new Image("file:src/main/resources/templatePlans/maleUpperPDF-1.png");
        initializeImages(maleUpperImage, maleUpper);
    }

    /**
     * Initializes the background images for buttons
     *
     * @param image The image to be set as the background
     * @param button The button for which the background is set
     */
    private void initializeImages(Image image, Button button){
        button.setStyle("-fx-background-image: url('" + image.getUrl() + "'); -fx-background-size: cover; -fx-background-position: center;");
    }
}
