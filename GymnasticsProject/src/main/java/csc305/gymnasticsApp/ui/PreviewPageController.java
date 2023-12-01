package csc305.gymnasticsApp.ui;


import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.LessonPlan;
import csc305.gymnasticsApp.data.PrintLessonPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

public class PreviewPageController {

    @FXML
    private TextField equipmentBox;
    @FXML
    private VBox eventPreviewVBox;

    @FXML
    private TextField lessonPlanTextField;

    private List<TextField> eventTitles = new ArrayList<>();
    @FXML
    private FlowPane mainFlowPane;
    @FXML
    private List<VBox> VBoxes = new ArrayList<>();
    private VBox titleVBox;
    @FXML
    private ScrollPane mainScrollPane;

    //@FXML
    //private FlowPane eventFlowPane;

    private LessonPlan lessonPlan;

    public void initialize() throws FileNotFoundException {
        System.out.println("Initializing preview page");
        lessonPlan = GymnasticsAppBeta.getLessonPlan();
        lessonPlan.printEverything();
        System.out.println("^^^^should have printed everything");
        //clear and reset panes
        mainFlowPane.getChildren().clear();
        VBoxes.clear();
        boolean isFirst = true;
        boolean createdTitle = false;

        //iterates through each event and adds subsequent cards
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++){
            //Checks if this is first event
            VBox currentVBox;
            if(!createdTitle) {
                VBox nVBox = getNewVBox();
                addTitleToVBox(nVBox, lessonPlan.getLessonPlanTitle());
                createdTitle = true;
                titleVBox = nVBox;
                nVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
                currentVBox = nVBox;
            } else{
                VBox nVBox = getNewVBox();
                nVBox.setPadding(new javafx.geometry.Insets(30, 0, 0, 0));
                currentVBox = nVBox;
            }
/*  IT AUTOMATICALLY MOVES THEM DOWN

            //fill VBoxes with cards
            //if first box add a title and move the other attributes down
            if(isFirst){
                fillTitleVBox(currentVBox, i);
                isFirst = false;
            }//Box doesn't contain title, so implement cards normally
            else{}

*/
            fillVBox(currentVBox, i);

            mainFlowPane.getChildren().add(currentVBox);
            VBoxes.add(currentVBox);

        }
        mainFlowPane.setAlignment(Pos.CENTER);
    }

    private VBox getNewVBox(){
        VBox returnVBox = new VBox();
        returnVBox.setStyle("-fx-background-color: grey;");
        returnVBox.setPrefSize(770.0, 595.0);
        returnVBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        returnVBox.setAlignment(Pos.TOP_CENTER);
        returnVBox.setFillWidth(false);
        returnVBox.setSpacing(10.0);
        return returnVBox;
    }


    private void fillVBox(VBox vbox, int eventIndex){
        HBox eventTitleHBox = createEventTitleHBox();
        TextField eventTitleTextField = createEventTitleTextField(eventIndex);
        FlowPane eventCards = createEventCardFlowPane();
        eventCards.getChildren().addAll(addCards(eventIndex));

        eventTitleHBox.getChildren().add(eventTitleTextField);
        vbox.getChildren().addAll(eventTitleHBox, eventCards);

    }

    private List<ImageView> addCards(int eventIndex){
        List<Card> eventCards = lessonPlan.getEventCards(eventIndex);
        List<ImageView> cardView = new ArrayList<>();
        for(Card card : eventCards){
            Image image = null;
            try {
                image = new Image(new FileInputStream("GymSoftwarePics/" +
                        card.getPackFolder().toUpperCase() + "Pack/" +
                        card.getImage()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200); // Set the width of the image view
            imageView.setFitHeight(200); // Set the height of the image view
            cardView.add(imageView);
        }
        return cardView;
    }


    private TextField createEventTitleTextField(int eventIndex){
        TextField eventTitleTextField = new TextField();
        eventTitleTextField.setAlignment(Pos.CENTER);
        eventTitleTextField.setPrefSize(250.0, 40.0);
        eventTitleTextField.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10px;");
        eventTitleTextField.setFont(Font.font("System Bold", 16.0));
        eventTitleTextField.setText(lessonPlan.getEventNames().get(eventIndex));

        return eventTitleTextField;
    }


    private HBox createEventTitleHBox(){
        HBox eventTitleHBox = new HBox();
        eventTitleHBox.setAlignment(Pos.CENTER_LEFT);
        eventTitleHBox.setPrefSize(760.0, 40.0);
        eventTitleHBox.setSpacing(50.0);
        return eventTitleHBox;
    }
    private FlowPane createEventCardFlowPane(){
        FlowPane eventFlowPane = new FlowPane();
        eventFlowPane.setPrefHeight(200.0);
        eventFlowPane.setPrefWidth(749.0);
        eventFlowPane.setHgap(40.0);
        eventFlowPane.setVgap(20.0);
        eventFlowPane.setAlignment(Pos.CENTER);
        eventFlowPane.setColumnHalignment(HPos.CENTER);
        return eventFlowPane;
    }




    /*
    private void addTopCardHBox(VBox nVBox){

        HBox eventTitleHBox = new HBox();
        eventTitleHBox.setAlignment(Pos.CENTER_LEFT);
        eventTitleHBox.setPrefSize(760.0, 40.0);
        eventTitleHBox.setSpacing(50.0);

        TextField eventTitleTextField = new TextField();
        eventTitleTextField.setAlignment(Pos.CENTER);
        eventTitleTextField.setPrefSize(250.0, 40.0);
        eventTitleTextField.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10px;");
        eventTitleTextField.setFont(Font.font("System Bold", 16.0));
        eventTitleTextField.setText();

        eventTitleHBox.getChildren().add(eventTitleTextField);
    }*/

    private void addTitleToVBox(VBox nVBox, String title) {
        TextField lessonPlanTextField = new TextField();
        lessonPlanTextField.setAlignment(Pos.CENTER);
        lessonPlanTextField.setPrefSize(402.0, 50.0);
        lessonPlanTextField.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10px;");
        lessonPlanTextField.setFont(Font.font("System Bold", 24.0));
        lessonPlanTextField.setText(title);
        nVBox.getChildren().add(lessonPlanTextField);
    }


    private TextField createEquipmentBox() {
        TextField equipmentBox = new TextField();
        equipmentBox.setPrefSize(770.0, 20.0);
        equipmentBox.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10px;");
        equipmentBox.setFont(Font.font("System Bold", 10.0));
        equipmentBox.setText("Equipment:");
        return equipmentBox;
    }

/*
    public void displayEventCards() {
        try {
            if(lessonPlan.getEventList().size() == 0){
                System.out.println("nothing in da list");
            }
            int count = 1;
            for(List<Card> eventCards : lessonPlan.getEventList()){
                //VBox newVBox = getNewVBoxParams();
                //VBoxes.add(newVBox);
                System.out.println(eventCards);
                for(Card card : eventCards){
                    Image image = new Image(new FileInputStream("GymSoftwarePics/" +
                            card.getPackFolder().toUpperCase() + "Pack/" +
                            card.getImage()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(200); // Set the width of the image view
                    imageView.setFitHeight(200); // Set the height of the image view
                    eventFlowPane.getChildren().add(imageView);
                }
                count = count + 1;
            }
            mainFlowPane.getChildren().clear();
            for(VBox vBoxToAdd : VBoxes){
                mainFlowPane.getChildren().add(vBoxToAdd);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    */

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
            MainEditDisplayController.resetButtons();
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
        System.out.println("size of event names" + lessonPlan.getEventNames().size());
        for(int i = 0; i < lessonPlan.getEventNames().size(); i++ ) {
            eventTitles.add(new TextField());
            eventTitles.get(i).setText(lessonPlan.getEventNames().get(i));
            System.out.println("event title: " + eventTitles.get(i).getText());
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