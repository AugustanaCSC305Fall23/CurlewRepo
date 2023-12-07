package csc305.gymnasticsApp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The AboutPageController class controls the behavior of the About Page UI
 */
public class AboutPageController {
    /**
     * The home button in the About Page UI
     */
    @FXML
    private Button homeButton;

    /**
     * Handles the action when the home button is clicked
     *
     * @param event The ActionEvent triggered by clicking the home button
     */
    @FXML
    private void handleHomeButton(ActionEvent event) {GymnasticsAppBeta.switchToHomePage();}
}
