package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class HomePageController {
    @FXML private Button NewLessonButton;
    @FXML private Button LoadLessonButton;


    @FXML
    private void handleNewLessonButton(ActionEvent event){
        GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    private void handleLoadLessonButton(ActionEvent event) {
        GymnasticsAppBeta.callFileChooser();
    }

}
