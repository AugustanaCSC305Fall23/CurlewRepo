package csc305.gymnasticsApp;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
    private TextField coursePlanTitle;

    @FXML
    private TextField eventOneTitle;

    @FXML
    private TextField eventTwoTitle;

    @FXML
    private HBox eventOneCardHBox;

    @FXML
    private HBox eventTwoCardHBox1;

    public void initialize() {
        if (Course.getCourseTitle() != null) {
            coursePlanTitle.setText(Course.getCourseTitle());
        }
        if (Course.getEventOneName() != null) {
            eventOneTitle.setText(Course.getEventOneName());
        }
        if (Course.getEventTwoName() != null) {
            eventTwoTitle.setText(Course.getEventTwoName());
        }
        displayEventCards();
    }

    public void displayEventCards() {
        try {
            for (Card card : Course.getEventOneCards()) {
                Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250); // Set the width of the image view
                imageView.setFitHeight(200); // Set the height of the image view
                eventOneCardHBox.getChildren().add(imageView);
            }
            for (Card card : Course.getEventTwoCards()) {
                Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250); // Set the width of the image view
                imageView.setFitHeight(200); // Set the height of the image view
                eventTwoCardHBox1.getChildren().add(imageView);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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

    }


    @FXML
    void saveController(ActionEvent event) {
        List<Card> cardList = new ArrayList<Card>();
        cardList.addAll(CardDatabase.getEventOneTreeCards());
        cardList.addAll(CardDatabase.getEventTwoTreeCards());

    }

}