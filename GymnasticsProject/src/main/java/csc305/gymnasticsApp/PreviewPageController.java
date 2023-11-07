package csc305.gymnasticsApp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PreviewPageController {

    @FXML
    private VBox eventPreviewVBox;
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
        Node lessonPlanNode = eventPreviewVBox;
        PrintLessonPlan.printPlan(lessonPlanNode, eventPreviewVBox);
    }

    @FXML
    void saveController(ActionEvent event) throws IOException {
        List<Card> cardList = new ArrayList<Card>();
        cardList.addAll(CardDatabase.getEventOneTreeCards());
        cardList.addAll(CardDatabase.getEventTwoTreeCards());



        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Show the file save dialog and get the selected file.
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Create a FileWriter for the selected file and write the data.
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                for (int i = 0; i < cardList.size(); i++) {
                    fileWriter.write(cardList.get(i).getUniqueID() + " end\n");
                }
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., show an error message).
                e.printStackTrace();
            }
        }
    }
}