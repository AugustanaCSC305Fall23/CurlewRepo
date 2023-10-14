package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private Button drillSearchButton;

    @FXML
    private ScrollPane cardScrollPane;

    @FXML
    private VBox cardVBox;

    @FXML
    private HBox cardHBox1;

    @FXML
    private HBox cardHBox2;

    @FXML
    private HBox cardHBox3;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    void previewButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

    @FXML
    void goButtonHandle(ActionEvent event) {filterCards(drillSearchBar.getText().toLowerCase());
    }


    private void filterCards (String inputText){
        for (Node hBoxNode : cardVBox.getChildren()) {
            if (hBoxNode instanceof HBox) {
                HBox hBox = (HBox) hBoxNode;
                for (Node buttonNode : hBox.getChildren()) {
                    if (buttonNode instanceof Button) {
                        Button button = (Button) buttonNode;
                        String buttonText = button.getId().toLowerCase();
                        if (buttonText.contains(inputText)) {
                            button.setVisible(true);
                        } else {
                            button.setVisible(false);
                        }
                    }
                }
            }
        }
    }


}
