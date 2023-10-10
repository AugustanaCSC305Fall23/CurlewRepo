package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainEditDisplayController {
    @FXML
    private Button backButton;

    @FXML
    private Button previewButton;

    @FXML
    private Button saveButton;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    void previewButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

    @FXML
    void saveButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

}
