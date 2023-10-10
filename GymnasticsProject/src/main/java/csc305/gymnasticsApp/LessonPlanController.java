package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class LessonPlanController {


    @FXML
    private Button backButton;
    @FXML
    private Button doneButton;
    @FXML private CheckBox threeCPLCheckbox;
    @FXML private CheckBox fourCPLCheckbox;
    @FXML private CheckBox fiveCPLCheckbox;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();
    }

    @FXML
    void doneButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    void handleCheckboxChanged(ActionEvent event) {
        CheckBox selectedCheckbox = (CheckBox) event.getSource();

        if (selectedCheckbox.isSelected()) {
            threeCPLCheckbox.setSelected(false);
            fourCPLCheckbox.setSelected(false);
            fiveCPLCheckbox.setSelected(false);
            selectedCheckbox.setSelected(true);
        }
    }

}