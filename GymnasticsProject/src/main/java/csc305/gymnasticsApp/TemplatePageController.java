package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.File;


public class TemplatePageController {
    @FXML
    private Button homeButton;
    @FXML
    private Button beginnerOnlyButtonOne;
    @FXML
    private Button maleOnlyButtonOne;
    @FXML
    private Button allFloorExercisesButtonOne;
    @FXML
    void handleBeginnerOnlyButtonOne(ActionEvent event) {
        LessonPlan.resetBoolean();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/beginnerFloorAndTrampolinePlan.GymPlanFile"));
        GymnasticsAppBeta.readLessonPlan();
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }

    @FXML
    void handleMaleOnlyButtonOne(ActionEvent event) {
        LessonPlan.resetBoolean();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/maleOnlyLessonPlan.GymPlanFile"));
        GymnasticsAppBeta.readLessonPlan();
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }
    @FXML
    void handleAllFloorExercisesButtonOne(ActionEvent event) {
        LessonPlan.resetBoolean();
        GymnasticsAppBeta.callFileChooser(new File("src/main/resources/templatePlans/allFloorExercisesPlan.GymPlanFile"));
        GymnasticsAppBeta.readLessonPlan();
        if (GymnasticsAppBeta.getLoaded()) {
            GymnasticsAppBeta.switchToPreviewPage();
        }
    }
    @FXML
    void homeButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToHomePage();
    }

    public void initialize(){
        Image beginnerFloorAndTrampolineImage = new Image("file:C:/Desktop/git/CurlewRepo/GymnasticsProject/src/main/resources/templatePlans/beginnerFloorAndTrampolinePlanImage-1.png");
        initializeImages(beginnerFloorAndTrampolineImage, beginnerOnlyButtonOne);
        Image maleOnlyLessonPlanImage = new Image("file:C:/Desktop/git/CurlewRepo/GymnasticsProject/src/main/resources/templatePlans/maleOnlyLessonPlanImage-1.png");
        initializeImages(maleOnlyLessonPlanImage, maleOnlyButtonOne);
        Image allFloorExercisesPlanImage = new Image("file:C:/Desktop/git/CurlewRepo/GymnasticsProject/src/main/resources/templatePlans/allFloorExercisesPlanImage-1.png");
        initializeImages(allFloorExercisesPlanImage, allFloorExercisesButtonOne);
    }

    private void initializeImages(Image image, Button button){
        button.setStyle("-fx-background-image: url('" + image.getUrl() + "'); -fx-background-size: cover; -fx-background-position: center;");
    }
}
