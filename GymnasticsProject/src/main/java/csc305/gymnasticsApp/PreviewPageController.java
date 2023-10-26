package csc305.gymnasticsApp;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class PreviewPageController {

    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button printButton;

    @FXML
    private Button saveButton;

    @FXML
    void backButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    void homeButtonController(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Caution");
        alert.setHeaderText("Are you sure you want to exit to the home page? Any unsaved lessons will be lost.");
        alert.setContentText("Please select an option.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesButton) {
            GymnasticsAppBeta.switchToHomePage();
        }

    }

    @FXML
    void printButtonController(ActionEvent event) {
        AnchorPane previewPane = new AnchorPane();
        PrinterJob job = PrinterJob.createPrinterJob();
        //previewPane.getChildren().addAll();
        if(job != null /*&& job.showPrintDialog(previewPane.getScene().getWindow())*/) {
            boolean success = job.showPrintDialog(null);
            if(success) {
                job.endJob();
            }
        }
    }
/*
    @FXML
    void saveController(ActionEvent event) {
        java.io.File file = GymnasticsAppBeta.getFile();
        if(file != null) {
            try{


        }
    }
    */
}