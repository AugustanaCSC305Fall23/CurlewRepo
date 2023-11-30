package csc305.gymnasticsApp.ui;


import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrintLessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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
    private TextField equipmentBox;
    @FXML
    private VBox eventPreviewVBox;
    @FXML
    private Button editButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button printButton;

    @FXML
    private Button saveButton;
    @FXML
    private Button templateButton;

    @FXML
    private TextField lessonPlanTextField;

    private List<TextField> eventTitles = new ArrayList<>();

    @FXML
    private FlowPane eventFlowPane;

    @FXML
    private HBox eventTwoCardHBox1;

    @FXML
    private Button hideEquipment;

    private LessonPlan lessonPlan;

    public void initialize() {
        lessonPlan = GymnasticsAppBeta.getLessonPlan();
//        lessonPlan.loadPlanFromFile();
        setLessonPlanTitles();
        displayEventCards();
        fillEquipmentBox();
    }


    public void displayEventCards() {
        try {
            for(List<Card> eventCards : lessonPlan.getEventList()){
                for(Card card : eventCards){
                    Image image = new Image(new FileInputStream("GymSoftwarePics/" +
                            card.getPackFolder().toUpperCase() + "Pack/" +
                            card.getImage()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(200); // Set the width of the image view
                    imageView.setFitHeight(200); // Set the height of the image view
                    eventFlowPane.getChildren().add(imageView);
                }
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void editButtonController(ActionEvent event) {
        GymnasticsAppBeta.switchToMainEditDisplay();
    }

    @FXML
    void handleEquipmentBar(ActionEvent event){
        if(equipmentBox.isVisible()){
            equipmentBox.setVisible(false);
        } else{
            equipmentBox.setVisible(true);
        }
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
            lessonPlan.resetLessonPlan();
            MainEditDisplayController.clearTreeCardItems();
            MainEditDisplayController.events.clear();
            MainEditDisplayController.cardParentEvents.clear();
            GymnasticsAppBeta.switchToHomePage();
        }

    }


    @FXML
    void printButtonController(ActionEvent event) {
        Node lessonPlanNode = eventPreviewVBox;
        PrintLessonPlan.printPlan(lessonPlanNode, eventPreviewVBox);
    }

    @FXML
    void handleTemplateButton(ActionEvent event){
        GymnasticsAppBeta.switchToTemplatePage();
    }
    @FXML
    void saveController(ActionEvent event) throws IOException {
        List<List<Card>> eventCardList = new ArrayList<>();
        for(int i =0; i < lessonPlan.getEventList().size(); i++){
            List<Card> cardList = new ArrayList<>();
            cardList.addAll(lessonPlan.getEventCards(i));
            eventCardList.add(cardList);
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Plan Files (*.GymPlanFile)", "*.GymPlanFile");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Show the file save dialog and get the selected file.
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Create a FileWriter for the selected file and write the data.
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                fileWriter.write(lessonPlanTextField.getText() + "\n");

                //NEED A FOR LOOP FOR EVENT TITLES

                for(List<Card> cardList : eventCardList) {
                    for (int i = 0; i < cardList.size(); i++) {
                        fileWriter.write(cardList.get(i).getUniqueID() + "\n");
                    }
                    fileWriter.write("end\n");
                }
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., show an error message).
                e.printStackTrace();
            }
        }
    }

    private void setLessonPlanTitles() {
        lessonPlanTextField.setText(lessonPlan.getLessonPlanTitle());
        for(int i = 0; i < LessonPlan.getEventNames().size(); i++ ) {
            eventTitles.get(i).setText(LessonPlan.getEventNames().get(i));
        }
    }

    private void fillEquipmentBox(){
        equipmentBox.setText("Equipment: ");
        for(List<Card> eventCards : lessonPlan.getEventList()){
            for(Card card : eventCards){
                if(!card.getEquipment().equals("None")) {
                    if (!(equipmentBox.getText().contains(card.getEquipment()))) {
                        equipmentBox.setText(equipmentBox.getText() + ", " + card.getEquipment());
                    }
                }
            }
        }
    }
}