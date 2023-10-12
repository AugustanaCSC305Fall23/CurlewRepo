package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class MainEditDisplayController {
    @FXML
    private Button backButton;

    @FXML
    private Button previewButton;

    @FXML
    private TextField drillSearchBar;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    void previewButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

}
