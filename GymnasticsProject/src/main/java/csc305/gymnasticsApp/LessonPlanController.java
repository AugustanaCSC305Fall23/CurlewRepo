package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;

public class LessonPlanController {


    @FXML private Button backButton;
    @FXML private Button doneButton;
    @FXML private CheckBox threeCPLCheckbox;
    @FXML private CheckBox fourCPLCheckbox;
    @FXML private CheckBox fiveCPLCheckbox;
    private CheckBox eventAmountCheckBox;

    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();
    }

    @FXML
    void doneButtonHandle(ActionEvent event) {

        if ((!threeCPLCheckbox.isSelected()) && (!fourCPLCheckbox.isSelected()) && (!fiveCPLCheckbox.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Caution");
            alert.setHeaderText("Please select a checkbox before proceeding.");
            alert.setContentText("Please select an option.");
            ButtonType okButton = new ButtonType("Ok");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        } else {
            GymnasticsAppBeta.switchToMainEditDisplay();
        }
    }

    @FXML
    public void handleCheckboxChanged(ActionEvent event) {
        CheckBox selectedCheckbox = (CheckBox) event.getSource();
        eventAmountCheckBox = selectedCheckbox;

        if (selectedCheckbox.isSelected()) {
            threeCPLCheckbox.setSelected(false);
            fourCPLCheckbox.setSelected(false);
            fiveCPLCheckbox.setSelected(false);
            selectedCheckbox.setSelected(true);
        }
    }

    public CheckBox getEventAmount() {
        return eventAmountCheckBox;
    }

}