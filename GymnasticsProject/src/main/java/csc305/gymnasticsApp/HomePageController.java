package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomePageController {
    @FXML private Button NewLessonButton;
    @FXML private Button loadLessonButton;


    @FXML
    private void handleNewLessonButton(ActionEvent event){
        GymnasticsAppBeta.switchToMainEdit();
    }



}
