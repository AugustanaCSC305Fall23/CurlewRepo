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
    void threeCPLCheckboxChanged(ActionEvent event) {
        if (threeCPLCheckbox.isSelected()) {
            fourCPLCheckbox.setSelected(false);
            fiveCPLCheckbox.setSelected(false);
        }
    }

    @FXML
    void fourCPLCheckboxChanged(ActionEvent event) {
        if (fourCPLCheckbox.isSelected()) {
            threeCPLCheckbox.setSelected(false);
            fiveCPLCheckbox.setSelected(false);
        }
    }

    @FXML
    void fiveCPLCheckboxChanged(ActionEvent event) {
        if (fiveCPLCheckbox.isSelected()) {
            threeCPLCheckbox.setSelected(false);
            fourCPLCheckbox.setSelected(false);
        }
    }
}