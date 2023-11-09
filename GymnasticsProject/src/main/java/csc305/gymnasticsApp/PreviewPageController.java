package csc305.gymnasticsApp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

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
    private TextField lessonPlanTitle;

    @FXML
    private TextField eventOneTitle;

    @FXML
    private TextField eventTwoTitle;

    @FXML
    private HBox eventOneCardHBox;

    @FXML
    private HBox eventTwoCardHBox1;

    public void initialize() {
        LessonPlan.loadPlanFromFile();
        if (LessonPlan.getLessonPlanTitle() != null) {
            lessonPlanTitle.setText(LessonPlan.getLessonPlanTitle());
        }
        if (LessonPlan.getEventOneName() != null) {
            eventOneTitle.setText(LessonPlan.getEventOneName());
        }
        if (LessonPlan.getEventTwoName() != null) {
            eventTwoTitle.setText(LessonPlan.getEventTwoName());
        }
        displayEventCards();
    }


    public void displayEventCards() {
        try {
            for (Card card : LessonPlan.getEventOneCards()) {
                Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200); // Set the width of the image view
                imageView.setFitHeight(200); // Set the height of the image view
                eventOneCardHBox.getChildren().add(imageView);
            }
            for (Card card : LessonPlan.getEventTwoCards()) {
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
    /*
    public void displayEventCards(ArrayList<String> cardList1, ArrayList<String> cardList2) {
        List<Card> cardList = CardDatabase.getAllCards();
        TreeItem<String> eventOneItems = new TreeItem<>("Event 1");
        TreeItem<String> eventTwoItems = new TreeItem<>("Event 2");
        try {
            for(int i = 0; i < cardList.size(); i++) {
                for(int j = 0; j < cardList1.size(); j++) {
                    if(cardList.get(i).getUniqueID().equals(cardList1.get(j))) {
                        TreeItem<String> newCard = new TreeItem<String>(cardList.get(i).getTitle());
                        Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                                cardList.get(i).getPackFolder().toUpperCase() + "Pack/" +
                                cardList.get(i).getImage()));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(200); // Set the width of the image view
                        imageView.setFitHeight(200); // Set the height of the image view
                        eventOneCardHBox.getChildren().add(imageView);
                        LessonPlan.addToEventOne(cardList.get(i));
                        eventOneItems.getChildren().add(newCard);
                    }
                }
            }
            for(int i = 0; i < cardList.size(); i++) {
                for(int j = 0; j < cardList2.size(); j++) {
                    if(cardList.get(i).getUniqueID().equals(cardList2.get(j))) {
                        TreeItem<String> newCard = new TreeItem<String>(cardList.get(i).getTitle());
                        Image image = new Image(new FileInputStream("src/main/resources/GymSoftwarePics" + "/" +
                                cardList.get(i).getPackFolder().toUpperCase() + "Pack/" +
                                cardList.get(i).getImage()));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(200); // Set the width of the image view
                        imageView.setFitHeight(200); // Set the height of the image view
                        eventTwoCardHBox1.getChildren().add(imageView);
                        LessonPlan.addToEventTwo(cardList.get(i));
                        eventTwoItems.getChildren().add(newCard);
                    }
                }
            }
            //MainEditDisplayController.addTreeCardItems(eventOneItems, eventTwoItems);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

     */
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
            LessonPlan.resetCourse();
            MainEditDisplayController.clearTreeCardItems();
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
                fileWriter.write(lessonPlanTitle.getText() + "\n" + eventOneTitle.getText() + "\n" + eventTwoTitle.getText() + "\n");
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