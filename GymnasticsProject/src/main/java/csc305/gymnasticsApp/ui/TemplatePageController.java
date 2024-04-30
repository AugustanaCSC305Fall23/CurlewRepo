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
    private Button templateOneButton;
    @FXML
    private Button templateTwoButton;
    @FXML
    private Button templateThreeButton;
    @FXML
    private Button templateFourButton;
    @FXML
    private Button templateFiveButton;

    /**
     * Handles the action event for loading the all floor lesson plan.
     *
     * @param event The action event triggered by the button.
     */

    @FXML
    void handleTemplateOneButton(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/templateOne.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    @FXML
    void handleTemplateTwoButton(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/templateTwo.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    @FXML
    void handleTemplateThreeButton(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/templateThree.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    @FXML
    void handleTemplateFourButton(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/templateFour.GymPlanFile"));
        GymnasticsAppBeta.readFile();
        ArrayList<String> loadedLessonPlan = GymnasticsAppBeta.readFile();
        HomePageController.loadPlan.loadPlanFromFile(loadedLessonPlan);
        GymnasticsAppBeta.setLessonPlan(HomePageController.loadPlan);
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    @FXML
    void handleTemplateFiveButton(ActionEvent event) {
        LessonPlan.resetBoolean();
        HomePageController.loadPlan = new LessonPlan();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/templateFive.GymPlanFile"));
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
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.events.clear();
        MainEditDisplayController.resetButtons();
        GymnasticsAppBeta.setLessonPlan(new LessonPlan());
        GymnasticsAppBeta.switchToHomePage();
    }

    /**
     * Initializes the controller
     */
    public void initialize(){

        Image templateOneImage = new Image("file:src/main/resources/templatePlans/templateOneSS.png");
        initializeImages(templateOneImage, templateOneButton);
        Image templateTwoImage = new Image("file:src/main/resources/templatePlans/templateTwoSS.png");
        initializeImages(templateTwoImage, templateTwoButton);
        Image templateThreeImage = new Image("file:src/main/resources/templatePlans/templateThreeSS.png");
        initializeImages(templateThreeImage, templateThreeButton);
        Image templateFourImage = new Image("file:src/main/resources/templatePlans/templateFourSS.png");
        initializeImages(templateFourImage, templateFourButton);
        Image templateFiveImage = new Image("file:src/main/resources/templatePlans/templateFiveSS.png");
        initializeImages(templateFiveImage, templateFiveButton);
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
