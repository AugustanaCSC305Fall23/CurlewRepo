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
/*
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
    */
    public void initialize() {
    if(GymnasticsAppBeta.getLoaded()) {
            ArrayList<String> arrayList = GymnasticsAppBeta.setPreviewPage();
            coursePlanTitle.setText(arrayList.remove(0));
            eventOneTitle.setText(arrayList.remove(0));
            eventTwoTitle.setText(arrayList.remove(0));
            ArrayList<String> arrayList1 = new ArrayList<String>();
            ArrayList<String> arrayList2 = new ArrayList<String>();

            for(int i = 0; i < arrayList.size(); i++) {
                if(arrayList.get(i).equals("end")) {
                    arrayList.remove(i);
                    break;
                } else {
                    arrayList1.add(arrayList.remove(i));
                    i--;
                }
            }
            arrayList2 = arrayList;
            displayEventCards(arrayList1, arrayList2);
        } else {
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
    }


    public void displayEventCards() {
        try {
            for (Card card : Course.getEventOneCards()) {
                Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200); // Set the width of the image view
                imageView.setFitHeight(200); // Set the height of the image view
                eventOneCardHBox.getChildren().add(imageView);
            }
            for (Card card : Course.getEventTwoCards()) {
                Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200); // Set the width of the image view
                imageView.setFitHeight(200); // Set the height of the image view
                eventTwoCardHBox1.getChildren().add(imageView);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayEventCards(ArrayList<String> cardList1, ArrayList<String> cardList2) {
        List<Card> cardList = CardDatabase.getAllCards();
        try {
            for(int i = 0; i < cardList.size(); i++) {
                for(int j = 0; j < cardList1.size(); j++) {
                    if(cardList.get(i).getUniqueID().equals(cardList1.get(j))) {
                        Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                                cardList.get(i).getPackFolder().toUpperCase() + "Pack/" +
                                cardList.get(i).getImage()));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(200); // Set the width of the image view
                        imageView.setFitHeight(200); // Set the height of the image view
                        eventOneCardHBox.getChildren().add(imageView);
                    }
                }
            }
            for(int i = 0; i < cardList.size(); i++) {
                for(int j = 0; j < cardList2.size(); j++) {
                    if(cardList.get(i).getUniqueID().equals(cardList2.get(j))) {
                        Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                                cardList.get(i).getPackFolder().toUpperCase() + "Pack/" +
                                cardList.get(i).getImage()));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(200); // Set the width of the image view
                        imageView.setFitHeight(200); // Set the height of the image view
                        eventTwoCardHBox1.getChildren().add(imageView);
                    }
                }
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
        List<Card> cardList1 = new ArrayList<Card>();
        cardList1.addAll(CardDatabase.getEventOneTreeCards());
        List<Card> cardList2 = new ArrayList<Card>();
        cardList2.addAll(CardDatabase.getEventTwoTreeCards());



        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Show the file save dialog and get the selected file.
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Create a FileWriter for the selected file and write the data.
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                fileWriter.write(Course.getCourseTitle() + "\n" + Course.getEventOneName() + "\n" + Course.getEventTwoName() + "\n");
                for (int i = 0; i < cardList1.size(); i++) {
                    fileWriter.write(cardList1.get(i).getUniqueID() + "\n");
                }
                fileWriter.write("end\n");
                for(int i = 0; i < cardList2.size(); i++) {
                    fileWriter.write(cardList2.get(i).getUniqueID() + "\n");
                }
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., show an error message).
                e.printStackTrace();
            }
        }
    }
}