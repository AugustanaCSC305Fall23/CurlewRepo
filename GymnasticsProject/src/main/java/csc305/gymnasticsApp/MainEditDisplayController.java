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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

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
    private FlowPane cardFlowPane;


    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToLessonPlan();
    }

    @FXML
    void previewButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToPreviewPage();
    }

    @FXML
    void goButtonHandle(ActionEvent event) {filterCards(drillSearchBar.getText().toLowerCase());
    }



    private void filterCards(String inputText) {
        List<Button> cardButtons = getAllCardButtons();
        List<Button> visibleButtons = new ArrayList<>();
        List<Button> hiddenButtons = new ArrayList<>();

        for (Button cardButton : cardButtons) {
            String buttonText = cardButton.getId().toLowerCase();
            if (buttonText.contains(inputText)) {
                visibleButtons.add(cardButton);
                cardButton.setVisible(true);
            } else {
                hiddenButtons.add(cardButton);
                cardButton.setVisible(false);
            }
        }

        cardFlowPane.getChildren().clear();
        cardFlowPane.getChildren().addAll(visibleButtons);
        cardFlowPane.getChildren().addAll(hiddenButtons);
    }


    private List<Button> getAllCardButtons() {
        List<Button> cardButtons = new ArrayList<>();
        for (int i = 0; i < cardFlowPane.getChildren().size(); i++) {
            if (cardFlowPane.getChildren().get(i) instanceof Button) {
                cardButtons.add((Button) cardFlowPane.getChildren().get(i));
            }
        }
        return cardButtons;
    }

}
