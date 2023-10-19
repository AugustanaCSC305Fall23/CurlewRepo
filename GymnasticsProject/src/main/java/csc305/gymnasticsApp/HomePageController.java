package csc305.gymnasticsApp;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
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
        GymnasticsAppBeta.switchToPreviewPage();
    }

}
