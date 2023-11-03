package csc305.gymnasticsApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;

/**
 * The LessonPlanController class handles user interactions and events on the lesson plan page of the application.
 */
public class LessonPlanController {


    @FXML private Button backButton;
    @FXML private Button doneButton;
    @FXML private CheckBox threeCPLCheckbox;
    @FXML private CheckBox fourCPLCheckbox;
    @FXML private CheckBox fiveCPLCheckbox;
    private CheckBox eventAmountCheckBox;

    /**
     * Handles the action when the "Back" button is clicked, allowing the user to return to the home page.
     *
     * @param event The ActionEvent triggered by clicking the "Back" button.
     */
    @FXML
    void backButtonHandle(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();
    }

    /**
     * Handles the action when the "Done" button is clicked, verifying checkbox selection before proceeding.
     *
     * @param event The ActionEvent triggered by clicking the "Done" button.
     */
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

    /**
     * Handles the action when a checkbox is changed. Ensures that only one checkbox is selected at a time.
     *
     * @param event The ActionEvent triggered by changing the state of a checkbox.
     */
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

    /**
     * Retrieves the selected event amount checkbox.
     *
     * @return The selected event amount checkbox.
     */
    public CheckBox getEventAmount() {
        return eventAmountCheckBox;
    }

}